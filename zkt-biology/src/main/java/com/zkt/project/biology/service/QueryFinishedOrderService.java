package com.zkt.project.biology.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkt.common.core.util.UserInfo;
import com.zkt.project.biology.constant.Constant;
import com.zkt.project.biology.constant.ReturnObjectHandle;
import com.zkt.project.biology.constant.ReturnSimpleHandle;
import com.zkt.project.biology.entity.Cage;
import com.zkt.project.biology.entity.Order;
import com.zkt.project.biology.entity.Pic;
import com.zkt.project.biology.entity.Sample;
import com.zkt.project.biology.mapper.CageMapper;
import com.zkt.project.biology.mapper.LoginMapper;
import com.zkt.project.biology.mapper.OperatrecordMapper;
import com.zkt.project.biology.mapper.OrderMapper;
import com.zkt.project.biology.mapper.PicMapper;
import com.zkt.project.biology.mapper.ProblemMapper;
import com.zkt.project.biology.mapper.ProblemrecordMapper;
import com.zkt.project.biology.mapper.SampleMapper;

import net.sf.json.JSONObject;

@Service
public class QueryFinishedOrderService {

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private PicMapper picMapper;

	@Autowired
	private SampleMapper sampleMapper;

	@Autowired
	private CageMapper cageMapper;

	@Autowired
	private OperatrecordMapper operatrecordMapper;

	@Autowired
	private ProblemMapper problemMapper;

	@Autowired
	private ProblemrecordMapper problemrecordMapper;
	
	@Autowired
	private LoginMapper loginMapper;
	
	//查询已完成订单(不含已完成异常件)
	
	public String search(JSONObject json) throws Exception {
		
		UserInfo userInfo = null;//RedisContent.getUserInfo();
		String userID = String.valueOf(userInfo.getUserId());// 锁定权限	
		String userType = userInfo.getUserType();
		JSONObject userJson = JSONObject.fromObject(userInfo.getUser());
		String city = userJson.getString("city");
		String area = userJson.getString("area");
		
		String orderNo = json.getString("orderNo");
		String cageno = json.getString("cageno");
		String classify = json.getString("classify");
		String startDate = json.getString("startDate");
		String endDate = json.getString("endDate");
		
		Integer draw = Integer.parseInt(json.getString("draw"));
		Integer from = Integer.parseInt(json.getString("start"));
		Integer pageSize = Integer.parseInt(json.getString("pageCount"));
		
		Map<String, Object> map = new HashMap<>();		
		if(Constant.USER_TYPE_CITY.equals(userType)){			
			map.put("city", city);		
		}
		if(Constant.USER_TYPE_AREA.equals(userType)){
			map.put("city", city);
			map.put("area", area);
		}
		if(Constant.USER_TYPE_HOSPITAL.equals(userType)){
			map.put("senderid", userID);
			map.put("reciverid", userID);
		}
		if(Constant.USER_TYPE_TRANSPORT.equals(userType)){			
			map.put("transportid", userID);			
		}
		map.put("orderNo", orderNo);
		map.put("cageno", cageno);		
		map.put("classify", classify);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("orderStatus", Constant.ORDER_STATUS_FINISH);
		map.put("isProblem", Constant.IS_PROBLEM_N);
		map.put("pageSize", pageSize);
		map.put("from", from);

		List<Order> orderList = orderMapper.selectOver(map);
		Integer orderListCount = orderMapper.countByOver(map);
		//封装返回参数
		ReturnObjectHandle returnHandle = ReturnObjectHandle.createServerHandle();
		returnHandle.setDraw(draw);
		returnHandle.setData(orderList);
		returnHandle.setPageCount(pageSize);
		returnHandle.setDataMaxCount(orderListCount);
		returnHandle.setDataMaxPage(
				orderListCount % pageSize == 0 ? orderListCount / pageSize : orderListCount / pageSize + 1);
		return JSONObject.fromObject(returnHandle).toString();
	}

	//已完成订单详情(不含已完成异常件)
	
	public String detail(JSONObject json) throws Exception {
		
		String orderNo = json.getString("orderNo");
		Order order = orderMapper.selectByOrderNo(orderNo);
		//订单状态
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
		//问题件状态
		String isProblem = "";
		switch (order.getIsProblem()) {
		case Constant.IS_PROBLEM_Y:
			isProblem = "是异常件";
			order.setIsProblem(isProblem);
			break;
		case Constant.IS_PROBLEM_N:
			isProblem = "不是异常件";
			order.setIsProblem(isProblem);
			break;
		default:
			break;
		}
		//下单分类
		String classify = "";
		switch (order.getClassify()) {
		case Constant.ORDER_CLASSIFY_PC:
			classify = "PC端录入";
			order.setClassify(classify);
			break;
		case Constant.ORDER_CLASSIFY_WEIXIN:
			classify = "微信录入";
			order.setClassify(classify);
			break;
		default:
			break;
		}
		Cage cage = cageMapper.selectByCageno(order.getCageNo());
		//箱子状态
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

		List<Sample> sample = sampleMapper.selectByKeys(orderNo);
		for(int i=0; i<sample.size(); i++){
			sample.get(i).setSpare4(order.getSampleclassify());//样本类型
			sample.get(i).setSpare5(order.getNum());//样本数量
		}

		Map<String, Object> classify1 = new HashMap<>();
		classify1.put("orderId", orderNo);
		classify1.put("classify", Constant.PICTURE_TYPE_CAGE);
		List<Pic> picAddresList1 = picMapper.selectByClassify(classify1);
		
		Map<String, Object> classify2 = new HashMap<>();
		classify2.put("orderId", orderNo);
		classify2.put("classify", Constant.PICTURE_TYPE_CAR);
		List<Pic> picAddresList2 = picMapper.selectByClassify(classify2);
		
		Map<String, Object> classify3 = new HashMap<>();
		classify3.put("orderId", orderNo);
		classify3.put("classify", Constant.PICTURE_TYPE_RECEIVE);
		List<Pic> picAddresList3 = picMapper.selectByClassify(classify3);
		
		JSONObject result = new JSONObject();
		result.put("order", order);// 订单信息
		result.put("cage", cage);// 箱体信息
		result.put("sample", sample);// 样本信息
		result.put("picAddresList1", picAddresList1);
		result.put("picAddresList2", picAddresList2);
		result.put("picAddresList3", picAddresList3);
		
		ReturnSimpleHandle returnHandle = ReturnSimpleHandle.createServerHandle();
		returnHandle.setData(result);
		return JSONObject.fromObject(returnHandle).toString();
	}
	
}
