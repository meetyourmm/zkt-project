package com.zkt.project.biology.service.wechat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.zkt.project.biology.config.PropConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkt.common.core.util.DateUtils;
import com.zkt.common.core.util.RequestConstance;
import com.zkt.common.core.util.RequestTag;
import com.zkt.common.core.util.ValidateUtil;
import com.zkt.project.biology.constant.Constant;
import com.zkt.project.biology.entity.Login;
import com.zkt.project.biology.entity.Order;
import com.zkt.project.biology.mapper.CageMapper;
import com.zkt.project.biology.mapper.LoginMapper;
import com.zkt.project.biology.mapper.ModelMapper;
import com.zkt.project.biology.mapper.OperatrecordMapper;
import com.zkt.project.biology.mapper.OrderMapper;
import com.zkt.project.biology.mapper.PicMapper;
import com.zkt.project.biology.mapper.ProblemMapper;
import com.zkt.project.biology.mapper.RunningMapper;
import com.zkt.project.biology.mapper.SampleMapper;
import com.zkt.project.biology.mapper.WechatusersMapper;
import com.zkt.project.biology.service.CommonService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class WeChatSearchService {

	@Autowired
	private LoginMapper loginMapper;

	@Autowired
	private SampleMapper sampleMapper;

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private CageMapper cageMapper;

	@Autowired
	private OperatrecordMapper operatrecordMapper;

	@Autowired
	private CommonService commonServiceImpl;

	@Autowired
	private PicMapper picMapper;

	@Autowired
	private ProblemMapper problemMapper;

	@Autowired
	private WechatusersMapper wechatusersMapper;

	@Autowired
	private RunningMapper runningMapper;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PropConfiguration proper;

	//微信查看所有订单
	public String search(HttpServletRequest request) throws Exception {

		JSONObject result = new JSONObject();

		String userName = request.getParameter("userName");
		String state = request.getParameter("state");
		String orderNo = request.getParameter("orderno");
		if (ValidateUtil.isEmpty(userName)) {
			result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_MISSING_PARAMETER);
			result.put(RequestConstance.RESULT_MSG, "缺少参数");
			return result.toString();
		}

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userName", userName);
		Login login = loginMapper.login(map);
		if (null == login) {
			result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_FAIL);
			result.put(RequestConstance.RESULT_MSG, "账号错误!");
			return result.toString();
		}

		String userId = login.getId();
		String userType = login.getUserType();

		Map<String, Object> maps = new HashMap<>();
		if (userType.equals(Constant.USER_TYPE_HOSPITAL)) {
			maps.put("senderid", userId);
			maps.put("reciverid", userId);			
		}
		if (userType.equals(Constant.USER_TYPE_TRANSPORT)) {
			maps.put("transportid", userId);		
		}
		
		// 关联到医院员工 运输公司员工
		if (userType.equals(Constant.USER_TYPE_EMPLOY)) {
			maps.put("createdBy", userName);
			maps.put("receiveOperator", userName);
		}
		if (userType.equals(Constant.USER_TYPE_TRANSPORTEMPLOY)) {			
			maps.put("transportOperator", userName);
		}
		
		// 有订单号的按订单号查 不要加状态 没有订单的话 全局查 加入状态条件
		if (!ValidateUtil.isEmpty(orderNo)) {
			maps.put("orderNo", orderNo);
		} else {
			maps.put("orderStatus", state);
		}

		JSONArray restMap = new JSONArray();
		JSONObject all = new JSONObject();

		List<Order> orderList = orderMapper.selectWeChatAll(maps);
		for (int i = 0; i < orderList.size(); i++) {
			// 订单状态
			String orderStatus = "";
			switch (orderList.get(i).getOrderStatus()) {
			case Constant.ORDER_STATUS_NEW:
				orderStatus = "新建";
				break;
			case Constant.ORDER_STATUS_PACK:
				orderStatus = "已装箱";
				break;
			case Constant.ORDER_STATUS_TRANSPORT:
				orderStatus = "运输中";
				break;
			case Constant.ORDER_STATUS_FINISH:
				orderStatus = "已完成";
				break;
			case Constant.ORDER_STATUS_XIETIAO:
				orderStatus = "待仲裁";
				break;
			default:
				break;
			}
			all.put("orderno", orderList.get(i).getOrderNo());
			all.put("sender", orderList.get(i).getSender());
			all.put("transport", orderList.get(i).getTransport());
			all.put("reciver", orderList.get(i).getReciver());
			all.put("orderStatus", orderStatus);
			all.put("ordertime", DateUtils.formatDate(orderList.get(i).getCreatedAt(), DateUtils.YYYY_MM_DD_HH_MM));
			restMap.add(all);
		}
		// 返回数据
		result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_OK);
		result.put(RequestConstance.RESULT_MSG, RequestConstance.MSG_SUCCESS);
		result.put(RequestConstance.RESULT_DATA, restMap);
		return result.toString();

	}

}
