package com.zkt.project.biology.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkt.common.core.util.HttpRequest;
import com.zkt.common.core.util.UserInfo;
import com.zkt.project.biology.constant.Constant;
import com.zkt.project.biology.constant.ReturnObjectHandle;
import com.zkt.project.biology.constant.ReturnSimpleHandle;
import com.zkt.project.biology.entity.Cage;
import com.zkt.project.biology.entity.Login;
import com.zkt.project.biology.entity.Order;
import com.zkt.project.biology.entity.Pic;
import com.zkt.project.biology.entity.Problem;
import com.zkt.project.biology.entity.Problemmonitor;
import com.zkt.project.biology.entity.Running;
import com.zkt.project.biology.entity.Sample;
import com.zkt.project.biology.entity.Wechatusers;
import com.zkt.project.biology.mapper.CageMapper;
import com.zkt.project.biology.mapper.LoginMapper;
import com.zkt.project.biology.mapper.OperatrecordMapper;
import com.zkt.project.biology.mapper.OrderMapper;
import com.zkt.project.biology.mapper.PicMapper;
import com.zkt.project.biology.mapper.ProblemMapper;
import com.zkt.project.biology.mapper.ProblemmonitorMapper;
import com.zkt.project.biology.mapper.RunningMapper;
import com.zkt.project.biology.mapper.SampleMapper;
import com.zkt.project.biology.mapper.WechatusersMapper;

import net.sf.json.JSONObject;

@Service
public class OrderReceiveService {

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
	private LoginMapper loginMapper;
	
	@Autowired
	private ProblemMapper problemMapper;
	
	@Autowired
	private RunningMapper runningMapper;
	
	@Autowired
	private ProblemmonitorMapper problemmonitorMapper;
	
	@Autowired
	private WechatusersMapper wechatusersMapper;
	
	// 查询签收订单
	public ReturnObjectHandle search(JSONObject json){
		
		UserInfo userInfo = null;//RedisContent.getUserInfo();
		String userId = String.valueOf(userInfo.getUserId());// 锁定权限
		
		String orderNo = json.getString("orderNo");
		String cageno = json.getString("cageno");
		String classify = json.getString("classify");
		
		Integer draw = Integer.parseInt(json.getString("draw"));
		Integer from = Integer.parseInt(json.getString("start"));
		Integer pageSize = Integer.parseInt(json.getString("pageCount"));

		Map<String, Object> map = new HashMap<>();
		map.put("reciverid", userId);//只有接受人才能签收
		map.put("orderNo", orderNo);
		map.put("cageno", cageno);		
		map.put("classify", classify);		
		map.put("orderStatus", Constant.ORDER_STATUS_TRANSPORT);
		map.put("pageSize", pageSize);
		map.put("from", from);

		List<Order> orderList = orderMapper.selectOrder(map);
		Integer orderListCount = orderMapper.countByOrder(map);

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

	// 订单签收详情
	public ReturnSimpleHandle detail(JSONObject json){
		
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
		
		List<Problemmonitor> problemmonitor = problemmonitorMapper.selectByOrderNo(orderNo);
		for(int i=0; i<problemmonitor.size(); i++){
			if(Constant.TREATMENT_SITUATION_ING.equals(problemmonitor.get(i).getStatus())){
				problemmonitor.get(i).setStatus("处理中");
			}
			if(Constant.TREATMENT_SITUATION_OVER.equals(problemmonitor.get(i).getStatus())){
				problemmonitor.get(i).setStatus("处理完毕");
			}
		}
		
		Map<String, Object> classify1 = new HashMap<>();
		classify1.put("orderId", orderNo);
		classify1.put("classify", "1");		
		List<Pic> picAddresList1 = picMapper.selectByClassify(classify1);
		
		Map<String, Object> classify2 = new HashMap<>();
		classify2.put("orderId", orderNo);
		classify2.put("classify", "2");		
		List<Pic> picAddresList2 = picMapper.selectByClassify(classify2);
		
		JSONObject result = new JSONObject();
		result.put("order", order);// 订单信息
		result.put("cage", cage);// 箱体信息
		result.put("sample", sample);// 样本信息
		result.put("problemmonitor", problemmonitor);// 运输异常信息
		result.put("picAddresList1", picAddresList1);
		result.put("picAddresList2", picAddresList2);
		
		ReturnSimpleHandle returnHandle = ReturnSimpleHandle.createServerHandle();
		returnHandle.setData(result);
		return returnHandle;
	}

	//样本正常，确认签收
	public void updateNormal(JSONObject json){
		
		UserInfo userInfo = null;//RedisContent.getUserInfo();
		String userName = userInfo.getUserName();
		
		String orderNo = json.getString("orderNo");
		String cageno = json.getString("cageno");
		
		//更新订单
		Order order = orderMapper.selectByOrderNo(orderNo);
		order.setReceiveOperator(userName);//写入接受人员账号
		order.setOrderStatus(Constant.ORDER_STATUS_FINISH);
		order.setReceivetime(new Date());
		orderMapper.updateByPrimaryKeySelective(order);
		
		// 关联订单流水记录
		Running running = new Running();
		running.setOrderno(orderNo);
		running.setState(Constant.ORDER_STATUS_FINISH);
		running.setOperator(userName);
		running.setTime(new Date());
		runningMapper.insertSelective(running);
				
		// 更新箱子
		Cage cage = cageMapper.selectByCageno(cageno);
		cage.setState(Constant.CAGESTATE_FREE);
		cageMapper.updateByPrimaryKeySelective(cage);
		
		//推送三方流程结束 结束监控
		String senderid = order.getSenderid();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", senderid);
		Login login = loginMapper.login(map);
		
		Wechatusers wechatusers = wechatusersMapper.selectByUsername(login.getUserName());
		if(null != wechatusers && wechatusers.getUserhospitalid().equals(senderid) && wechatusers.getUsertype().equals(Constant.USER_TYPE_HOSPITAL)){			
			HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/receive_inform", "orderno="+orderNo+"&receiver="+order.getReciver()+"&openid="+wechatusers.getUserid()+"&operator="+userName);	        
		}
		
	}
	
	//样本异常，提交异常
	public void updateAbnormal(JSONObject json){
		
		UserInfo userInfo = null;//RedisContent.getUserInfo();
		String userName = userInfo.getUserName();
		
		String orderNo = json.getString("orderNo");
		String discribtion = json.getString("discribtion");
		String cageno = json.getString("cageno");
		
		//更新订单
		Order order = orderMapper.selectByOrderNo(orderNo);
		order.setReceiveOperator(userName);//写入接受人员账号
		order.setOrderStatus(Constant.ORDER_STATUS_XIETIAO);
		order.setIsProblem(Constant.IS_PROBLEM_Y);
		order.setIsFinish(Constant.CHECK_TYPE_WAIT);
		order.setReceivetime(new Date());
		orderMapper.updateByPrimaryKeySelective(order);
		
		// 关联订单流水记录
		Running running = new Running();
		running.setOrderno(orderNo);
		running.setState(Constant.ORDER_STATUS_XIETIAO);
		running.setOperator(userName);
		running.setTime(new Date());
		runningMapper.insertSelective(running);
				
		// 更新箱子
		Cage cage = cageMapper.selectByCageno(cageno);
		cage.setState(Constant.CAGESTATE_FREE);
		cageMapper.updateByPrimaryKeySelective(cage);
		
		//保存异常
		Problem problem = new Problem();
		problem.setOrderNo(orderNo);
		problem.setDiscribtion(discribtion);
		problem.setStatus(Constant.TREATMENT_SITUATION_ING);
		problem.setCreatedAt(new Date());
		problem.setCreatedBy(userName);
		problemMapper.insertSelective(problem);
		
		//推送 发 送 双方 异常信息
		String senderid = order.getSenderid();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", senderid);
		Login login = loginMapper.login(map);
		
		Wechatusers wechatusers = wechatusersMapper.selectByUsername(login.getUserName());
		if(null != wechatusers && wechatusers.getUserhospitalid().equals(senderid) && wechatusers.getUsertype().equals(Constant.USER_TYPE_HOSPITAL)){			
			String htttp_Post = HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/refuse_inform", "orderno="+orderNo+"&receiver="+order.getReciver()+"&openid="+wechatusers.getUserid()+"&discribtion="+discribtion+"&operator="+userName);
	        System.out.println(htttp_Post);
		}
		
	}
		
}


