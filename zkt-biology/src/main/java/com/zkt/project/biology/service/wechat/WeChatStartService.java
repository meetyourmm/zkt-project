package com.zkt.project.biology.service.wechat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.zkt.project.biology.config.PropConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkt.common.core.util.DateUtils;
import com.zkt.common.core.util.HttpRequest;
import com.zkt.common.core.util.RequestConstance;
import com.zkt.common.core.util.RequestTag;
import com.zkt.common.core.util.ValidateUtil;
import com.zkt.project.biology.constant.Constant;
import com.zkt.project.biology.entity.Cage;
import com.zkt.project.biology.entity.Login;
import com.zkt.project.biology.entity.Operatrecord;
import com.zkt.project.biology.entity.Order;
import com.zkt.project.biology.entity.Pic;
import com.zkt.project.biology.entity.Running;
import com.zkt.project.biology.entity.Wechatusers;
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
public class WeChatStartService {

	/**
	 * LOG信息输出
	 */
	private final Log log = LogFactory.getLog(getClass());
	
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

	
	public String start(HttpServletRequest request) throws Exception {

		JSONObject result = new JSONObject();

		String userName = request.getParameter("userName");
		String orderno = request.getParameter("orderno");
		String cageno = request.getParameter("cageno");
		String arrivetime = request.getParameter("arrivetime");
		String carNo = request.getParameter("carNo");
		String picturelink = request.getParameter("picturelink");// 图片链接JSON数组
		if (ValidateUtil.isEmpty(userName) || ValidateUtil.isEmpty(orderno) || ValidateUtil.isEmpty(cageno)
			    || ValidateUtil.isEmpty(arrivetime)
				|| ValidateUtil.isEmpty(carNo)) {
			result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_MISSING_PARAMETER);
			result.put(RequestConstance.RESULT_MSG, "缺少参数");
			return result.toString();
		}
		
		log.warn("userName" + userName);
		log.warn("orderno" + orderno);
		log.warn("cageno" + cageno);
		log.warn("arrivetime" + arrivetime);
		log.warn("carNo" + carNo);
		log.warn("picturelink" + picturelink);
		
		Order order = orderMapper.selectByOrderNo(orderno);
		if (null == order) {
			result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_FAIL);
			result.put(RequestConstance.RESULT_MSG, "订单不存在!");
			return result.toString();
		}

		if (order.getOrderStatus().equals(Constant.ORDER_STATUS_TRANSPORT)) {
			result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_FAIL);
			result.put(RequestConstance.RESULT_MSG, "不能重复发车!");
			return result.toString();
		}

		// 保存图片
		if(!picturelink.equals("")&&null!=picturelink){
			JSONArray picturelinks = JSONArray.fromObject(picturelink);
			if (!picturelinks.isEmpty()) {
				for (int i = 0; i < picturelinks.size(); i++) {
					String picturelink1 = picturelinks.getString(i);
					Pic pic = new Pic();
					pic.setOrderId(orderno);
					pic.setPicturelink(picturelink1);
					pic.setClassify(Constant.PICTURE_TYPE_CAR);
					pic.setCreatedBy(userName);
					pic.setCreatedAt(new Date());
					picMapper.insertSelective(pic);
				}
			}
		}
		

		// 更新订单
		order.setTransportOperator(userName);// 写入运输人员账号
		order.setCarNo(carNo);
		order.setArrivetime(DateUtils.str2Date(arrivetime, DateUtils.YYYY_MM_DD_HH_MM));
		order.setOrderStatus(Constant.ORDER_STATUS_TRANSPORT);
		order.setSendtime(new Date());
		orderMapper.updateByPrimaryKeySelective(order);
		
		// 更新箱子
		Cage cage = cageMapper.selectByCageno(cageno);
		cage.setState(Constant.CAGESTATE_TRANSPORT);//在途
		cageMapper.updateByPrimaryKeySelective(cage);
				
		// 关联订单流水记录
		Running running = new Running();
		running.setOrderno(orderno);
		running.setState(Constant.ORDER_STATUS_TRANSPORT);
		running.setOperator(userName);
		running.setTime(new Date());
		runningMapper.insertSelective(running);

		// 更新操作记录
		Operatrecord operatrecord = new Operatrecord();
		operatrecord.setCageno(cageno);
		operatrecord.setOrderNo(orderno);
		operatrecord.setCreatedAt(new Date());
		operatrecord.setStartBy(userName);
		operatrecord.setOperatclassify(Constant.OPERATION_TYPE_SAOMA);		
		operatrecordMapper.insertSelective(operatrecord);

		// 给三方推送启运通知
		String reciverid = order.getReciverid();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", reciverid);
		Login login = loginMapper.login(map);
		
		Wechatusers wechatusers = wechatusersMapper.selectByUsername(login.getUserName());
		if(null != wechatusers && wechatusers.getUserhospitalid().equals(reciverid) && wechatusers.getUsertype().equals(Constant.USER_TYPE_HOSPITAL)){
			String sender = order.getSender();
			String transport = order.getTransport();
			HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/sendout_inform", "orderno="+orderno+"&sender="+sender+"&openid="+wechatusers.getUserid()+"&transporter="+transport+"&arrivetime="+arrivetime);	        
		}
        
		result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_OK);
		result.put(RequestConstance.RESULT_MSG, RequestConstance.MSG_SUCCESS);
		return result.toString();
	}

}
