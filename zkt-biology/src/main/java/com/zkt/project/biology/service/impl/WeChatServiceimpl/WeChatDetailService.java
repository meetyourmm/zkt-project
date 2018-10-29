package com.zkt.project.biology.service.impl.WeChatServiceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkt.common.core.util.DateUtils;
import com.zkt.common.core.util.RequestConstance;
import com.zkt.common.core.util.RequestTag;
import com.zkt.common.core.util.ValidateUtil;
import com.zkt.project.biology.constant.Constant;
import com.zkt.project.biology.entity.Cage;
import com.zkt.project.biology.entity.Login;
import com.zkt.project.biology.entity.Order;
import com.zkt.project.biology.entity.Problemrecord;
import com.zkt.project.biology.entity.Running;
import com.zkt.project.biology.entity.Sample;
import com.zkt.project.biology.mapper.CageMapper;
import com.zkt.project.biology.mapper.LoginMapper;
import com.zkt.project.biology.mapper.ModelMapper;
import com.zkt.project.biology.mapper.OperatrecordMapper;
import com.zkt.project.biology.mapper.OrderMapper;
import com.zkt.project.biology.mapper.PicMapper;
import com.zkt.project.biology.mapper.ProblemMapper;
import com.zkt.project.biology.mapper.ProblemrecordMapper;
import com.zkt.project.biology.mapper.RunningMapper;
import com.zkt.project.biology.mapper.SampleMapper;
import com.zkt.project.biology.mapper.WechatusersMapper;
import com.zkt.project.biology.service.CommonService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class WeChatDetailService {

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
	private ProblemrecordMapper problemrecordMapper;
	
	@Autowired
	private WechatusersMapper wechatusersMapper;

	@Autowired
	private RunningMapper runningMapper;

	@Autowired
	private ModelMapper modelMapper;

	@Resource(name = "proper")
	private Map<?, ?> proper;

	@SuppressWarnings("unused")
	
	public String detail(HttpServletRequest request) throws Exception {

		JSONObject result = new JSONObject();

		String userName = request.getParameter("userName");
		String orderNo = request.getParameter("orderno");
		if (ValidateUtil.isEmpty(userName) || ValidateUtil.isEmpty(orderNo)) {
			result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_MISSING_PARAMETER);
			result.put(RequestConstance.RESULT_MSG, "缺少参数");
			return result.toString();
		}

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userName", userName);
		Login login = loginMapper.login(map);
		String userType = login.getUserType();// 锁定权限
		String roleId = String.valueOf(login.getRoleId());
		
		//取得角色对应的单位ID
		String userId = "";
		switch (login.getUserType()) {
		case Constant.USER_TYPE_HOSPITAL:
			userId = String.valueOf(login.getId());
			break;
		case Constant.USER_TYPE_TRANSPORT:
			userId = String.valueOf(login.getId());
			break;
		case Constant.USER_TYPE_EMPLOY:
			userId = roleId;
			break;
		case Constant.USER_TYPE_TRANSPORTEMPLOY:
			userId = roleId;
			break;
		default:
			break;
		}
		
		if (login != null) {			
			Order order = orderMapper.selectByOrderNo(orderNo);			
			// 订单状态
			String orderStatus = "";
			switch (order.getOrderStatus()) {
			case Constant.ORDER_STATUS_NEW:
				orderStatus = "新建";
				order.setOrderStatus(orderStatus);
				break;
			case Constant.ORDER_STATUS_PACK:
				orderStatus = "已装箱";
				order.setOrderStatus(orderStatus);
				break;
			case Constant.ORDER_STATUS_TRANSPORT:
				orderStatus = "运输中";
				order.setOrderStatus(orderStatus);
				break;
			case Constant.ORDER_STATUS_FINISH:
				orderStatus = "已完成";
				order.setOrderStatus(orderStatus);
				break;
			case Constant.ORDER_STATUS_XIETIAO:
				orderStatus = "待仲裁";
				order.setOrderStatus(orderStatus);
				break;
			default:
				break;
			}
			
			// 问题件状态
			String isProblem = "";
			switch (order.getIsProblem()) {
			case Constant.IS_PROBLEM_Y:
				isProblem = "异常";
				order.setIsProblem(isProblem);
				break;
			case Constant.IS_PROBLEM_N:
				isProblem = "正常";
				order.setIsProblem(isProblem);
				break;
			default:
				break;
			}

			// 获取账号类型 1为发货方，2为运输方，3为收货方，4为非关联账号
			String userstate = "";
			String id = String.valueOf(login.getId());
			if (id.equals(order.getSenderid())) {
				userstate = "1";
			} else if (id.equals(order.getReciverid())) {
				userstate = "3";
			} else if (id.equals(order.getTransportid())) {
				userstate = "2";
			} else {
				userstate = "4";
			}

			// 获取箱体信息
			Cage cage = cageMapper.selectByCageno(order.getCageNo());
			// 获取箱体状态
			String state = "";
			switch (cage.getState()) {
			case Constant.CAGESTATE_FREE:
				state = "空闲";
				cage.setState(state);
				break;
			case Constant.CAGESTATE_ORDER:
				state = "已绑定订单";
				cage.setState(state);
				break;
			case Constant.CAGESTATE_TRANSPORT:
				state = "在途";
				cage.setState(state);
				break;
			case Constant.CAGESTATE_FAULT:
				state = "故障";
				cage.setState(state);
				break;
			default:
				break;
			}

			// 单独获取样本编码
			List<Sample> sampleNumber = sampleMapper.selectByOrderNo(orderNo);
			JSONArray sampleNumber1 = new JSONArray();
			for (int i = 0; i < sampleNumber.size(); i++) {
				sampleNumber1.add(sampleNumber.get(i).getSampleNumber());
			}
						
			// 查找异常记录
			Problemrecord problemrecord = problemrecordMapper.selectByOrderNo(orderNo);
			JSONObject res = new JSONObject();
			if(null != problemrecord){
				res.put("senderBy", problemrecord.getSenderBy());
				res.put("senderAt", DateUtils.formatDate(problemrecord.getSenderAt(), DateUtils.YYYY_MM_DD_HH_MM));
				res.put("transportBy", problemrecord.getTransportBy());
				res.put("transportAt", DateUtils.formatDate(problemrecord.getTransportAt(), DateUtils.YYYY_MM_DD_HH_MM));
				res.put("updatedBy", problemrecord.getUpdatedBy());
				res.put("updatedAt", DateUtils.formatDate(problemrecord.getUpdatedAt(), DateUtils.YYYY_MM_DD_HH_MM));
				res.put("comments", problemrecord.getComments());
				res.put("updatedByShi", problemrecord.getUpdatedByShi());
				res.put("updatedAtShi", DateUtils.formatDate(problemrecord.getUpdatedAtShi(), DateUtils.YYYY_MM_DD_HH_MM));
				res.put("commentsShi", problemrecord.getCommentsShi());
			}
			
			// 单独获取订单流水
			HashMap<String, String> maps = new HashMap<String, String>();
			maps.put("orderno", orderNo);			
			List<Running> running = runningMapper.selectByOrderNo(maps);			
			String runState = "";
			JSONArray res1 = new JSONArray();
			JSONObject all1 = new JSONObject();			
			for(int i=0; i<running.size(); i++){
				switch (running.get(i).getState()) {
				case Constant.ORDER_STATUS_NEW:
					runState = "订单已创建，创建人：" + running.get(i).getOperator();
					break;
				case Constant.ORDER_STATUS_PACK:
					runState = "订单已装箱，装箱人：" + running.get(i).getOperator();
					break;
				case Constant.ORDER_STATUS_TRANSPORT:
					runState = "订单已发车，司机：" + running.get(i).getOperator();
					break;
				case Constant.ORDER_STATUS_FINISH:
					runState = "订单接收，接收人：" + running.get(i).getOperator();
					break;
				case Constant.ORDER_STATUS_XIETIAO:
					runState = "订单被拒收，操作人：" + running.get(i).getOperator();
					break;
				default:
					break;
				}				
				all1.put("runningTime", DateUtils.formatDate(running.get(i).getTime(), DateUtils.YYYY_MM_DD_HH_MM));
				all1.put("runningState", runState);
				res1.add(all1);
			}
			
			// 返回数据
			JSONObject restMap = new JSONObject();
			restMap.put("orderno", order.getOrderNo());// 订单编号
			restMap.put("senderid", order.getSenderid());// 发货医院ID
			restMap.put("sender", order.getSender());// 发货医院
			restMap.put("receiverid", order.getReciverid());// 接受医院ID
			restMap.put("receiver", order.getReciver());// 接受医院
			restMap.put("transporterid", order.getTransportid());// 运输公司ID
			restMap.put("transporter", order.getTransport());// 运输公司
			restMap.put("time", DateUtils.formatDate(order.getCreatedAt(), DateUtils.YYYY_MM_DD_HH_MM));// 创建时间
			restMap.put("cageno", order.getCageNo());// 箱体编码
			restMap.put("battery", cage.getBattery());// 电量
			restMap.put("state", state);// 箱体状态
			restMap.put("tlimitup", order.getTlimitup());// 温度上限值
			restMap.put("tlimitdown", order.getTlimitdown());// 温度下限值
			restMap.put("hlimitup", order.getHlimitup());// 湿度上限值
			restMap.put("hlimitdown", order.getHlimitdown());// 湿度下限值
			restMap.put("remarks", order.getRemarks());// 备注
			restMap.put("transportstate", order.getOrderStatus());// 订单状态
			restMap.put("errorstate", order.getIsProblem());// 是否异常
			restMap.put("userstate", userstate);// 账号类型
			restMap.put("sampleNumber", sampleNumber1);// 样本编码
			restMap.put("sampleclassify", order.getSampleclassify());// 样本类型
			restMap.put("num", order.getNum());// 样本总数

			restMap.put("problemrecord", res);//异常审核对象
			restMap.put("running", res1);//流程数组

			if (order.getSenderid().equals(userId) && order.getOrderStatus().equals("新建")) {
				restMap.put("userNameStatus", "装箱");
			} else if (order.getTransportid().equals(userId) && order.getOrderStatus().equals("已装箱")) {
				restMap.put("userNameStatus", "发车");
			} else if (order.getReciverid().equals(userId) && order.getOrderStatus().equals("运输中")) {
				restMap.put("userNameStatus", "收货");
			} else if (userType.equals(Constant.USER_TYPE_HOSPITALWHITE)) {
				restMap.put("userNameStatus", "白卡");
			} else {
				restMap.put("userNameStatus", "其他");
			}

			result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_OK);
			result.put(RequestConstance.RESULT_MSG, RequestConstance.MSG_SUCCESS);
			result.put(RequestConstance.RESULT_DATA, restMap);
			return result.toString();
		} else {
			result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_FAIL);
			result.put(RequestConstance.RESULT_MSG, "账号错误!");
			return result.toString();
		}
	}
	
}
