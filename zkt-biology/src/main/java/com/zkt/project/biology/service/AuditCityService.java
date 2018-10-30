package com.zkt.project.biology.service;

import java.util.Date;
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
import com.zkt.project.biology.entity.Problem;
import com.zkt.project.biology.entity.Problemmonitor;
import com.zkt.project.biology.entity.Problemrecord;
import com.zkt.project.biology.entity.Sample;
import com.zkt.project.biology.mapper.CageMapper;
import com.zkt.project.biology.mapper.OperatrecordMapper;
import com.zkt.project.biology.mapper.OrderMapper;
import com.zkt.project.biology.mapper.PicMapper;
import com.zkt.project.biology.mapper.ProblemMapper;
import com.zkt.project.biology.mapper.ProblemmonitorMapper;
import com.zkt.project.biology.mapper.ProblemrecordMapper;
import com.zkt.project.biology.mapper.SampleMapper;

import net.sf.json.JSONObject;

@Service
public class AuditCityService {

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private CommonService commonService;

	@Autowired
	private PicMapper picMapper;

	@Autowired
	private SampleMapper sampleMapper;

	@Autowired
	private CageMapper cageMapper;

	@Autowired
	private OperatrecordMapper operatrecordMapper;
	
	@Autowired
	private ProblemrecordMapper problemrecordMapper;
	
	@Autowired
	private ProblemMapper problemMapper;
	
	@Autowired
	private ProblemmonitorMapper problemmonitorMapper;
	
	// 查询市级审核单
	
	public ReturnObjectHandle search(JSONObject json) throws Exception {
		
		UserInfo userInfo = null;//RedisContent.getUserInfo();
		JSONObject userJson = JSONObject.fromObject(userInfo.getUser());// 锁定权限
		String city = userJson.getString("city");
		/*String userType = userJson.getString("userType");
		String roleId = userJson.getString("roleId");*/
		
		String orderNo = json.getString("orderNo");
		String cageno = json.getString("cageno");
		String classify = json.getString("classify");

		Integer draw = Integer.parseInt(json.getString("draw"));
		Integer from = Integer.parseInt(json.getString("start"));
		Integer pageSize = Integer.parseInt(json.getString("pageCount"));

		Map<String, Object> map = new HashMap<>();
		map.put("city", city);
		/*map.put("userType", userType);
		map.put("roleId", roleId);*/
		map.put("orderNo", orderNo);
		map.put("cageno", cageno);		
		map.put("classify", classify);
		map.put("pageSize", pageSize);
		map.put("from", from);

		List<Order> orderList = orderMapper.selectProblemOrderShi(map);
		Integer orderListCount = orderMapper.countByProblemOrderShi(map);

		// 封装返回参数
		ReturnObjectHandle returnHandle = ReturnObjectHandle.createServerHandle();
		returnHandle.setDraw(draw);
		returnHandle.setData(orderList);
		returnHandle.setPageCount(pageSize);
		returnHandle.setDataMaxCount(orderListCount);
		returnHandle.setDataMaxPage(
				orderListCount % pageSize == 0 ? orderListCount / pageSize : orderListCount / pageSize + 1);
		return returnHandle;
	}

	// 审核单详情
	
	public ReturnSimpleHandle detail(JSONObject json) throws Exception {
		
		String orderNo = json.getString("orderNo");
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
		// 下单分类
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
		// 箱子状态
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
		classify1.put("classify", "1");
		List<Pic> picAddresList1 = picMapper.selectByClassify(classify1);

		Map<String, Object> classify2 = new HashMap<>();
		classify2.put("orderId", orderNo);
		classify2.put("classify", "2");
		List<Pic> picAddresList2 = picMapper.selectByClassify(classify2);

		Map<String, Object> classify4 = new HashMap<>();
		classify4.put("orderId", orderNo);
		classify4.put("classify", "4");
		List<Pic> picAddresList4 = picMapper.selectByClassify(classify4);
		
		Problem problem = problemMapper.selectByOrderNo(orderNo);
		// 处理状态
		String status = "";
		switch (problem.getStatus()) {
		case Constant.TREATMENT_SITUATION_ING:
			status = "处理中";
			problem.setStatus(status);
			break;
		case Constant.TREATMENT_SITUATION_OVER:
			state = "处理完毕";
			problem.setStatus(status);
			break;
		default:
			break;
		}
		
		List<Problemmonitor> problemmonitor = problemmonitorMapper.selectByOrderNo(orderNo);
		for(int i=0; i<problemmonitor.size(); i++){
			
			//处理状态
			String status1 = "";
			switch (problemmonitor.get(i).getStatus()) {
			case Constant.TREATMENT_SITUATION_ING:
				status1 = "处理中";
				problemmonitor.get(i).setStatus(status1);;
				break;
			case Constant.TREATMENT_SITUATION_OVER:
				status1 = "处理完毕";
				problemmonitor.get(i).setStatus(status1);
				break;
			default:
				break;
			}
			
			//异常描述
			String discribtion = "";
			switch (problemmonitor.get(i).getDiscribtion()) {
			case Constant.TLIMITUP_ERROR:
				discribtion = "温度上限异常";
				problemmonitor.get(i).setDiscribtion(discribtion);
				break;
			case Constant.TLIMITDOWN_ERROR:
				discribtion = "温度下限异常";
				problemmonitor.get(i).setDiscribtion(discribtion);
				break;
			case Constant.HLIMITUP_ERROR:
				discribtion = "湿度上限异常";
				problemmonitor.get(i).setDiscribtion(discribtion);
				break;
			case Constant.HLIMITDOWN_ERROR:
				discribtion = "湿度下限异常";
				problemmonitor.get(i).setDiscribtion(discribtion);
				break;
			case Constant.CAGE_OPEN_ERROR:
				discribtion = "箱体非正常打开";
				problemmonitor.get(i).setDiscribtion(discribtion);
				break;
			case Constant.ARRIVETIME_ERROR:
				discribtion = "预计时间未到达报警";
				problemmonitor.get(i).setDiscribtion(discribtion);
				break;
			default:
				break;
			}
			
		}
		
		Problemrecord problemrecord = problemrecordMapper.selectByOrderNo(orderNo);
		
		JSONObject result = new JSONObject();
		result.put("order", order);// 订单信息
		result.put("cage", cage);// 箱体信息
		result.put("sample", sample);// 样本信息
		result.put("picAddresList1", picAddresList1);
		result.put("picAddresList2", picAddresList2);
		result.put("picAddresList4", picAddresList4);
		result.put("problem", problem);
		result.put("problemrecord", problemrecord);
		result.put("problemmonitor", problemmonitor);
		
		ReturnSimpleHandle returnHandle = ReturnSimpleHandle.createServerHandle();
		returnHandle.setData(result);
		return returnHandle;
	}
	
	//审核终了
	
	public void updateOver(JSONObject json) throws Exception {
		
		UserInfo userInfo = null;//RedisContent.getUserInfo();
		String userName = userInfo.getUserName();
		String orderNo = json.getString("orderNo");
		String commentsShi = json.getString("commentsShi");

		//更新订单
		Order order = orderMapper.selectByOrderNo(orderNo);
		order.setOrderStatus(Constant.ORDER_STATUS_FINISH);
		order.setIsFinish(Constant.CHECK_TYPE_OVER);
		orderMapper.updateByPrimaryKeySelective(order);

		//保存异常
		Problem problem = problemMapper.selectByOrderNo(orderNo);
		problem.setStatus(Constant.TREATMENT_SITUATION_OVER);
		problemMapper.updateByPrimaryKeySelective(problem);

		//更新异常记录
		Problemrecord problemrecord = problemrecordMapper.selectByOrderNo(orderNo);
		problemrecord.setUpdatedByShi(userName);
		problemrecord.setUpdatedAtShi(new Date());
		problemrecord.setCommentsShi(commentsShi);
		problemrecordMapper.updateByPrimaryKeySelective(problemrecord);
		
	}

}
