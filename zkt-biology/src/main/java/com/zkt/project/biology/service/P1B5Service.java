package com.zkt.project.biology.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkt.common.core.util.UserInfo;
import com.zkt.common.core.util.ValidateUtil;
import com.zkt.project.biology.constant.Constant;
import com.zkt.project.biology.constant.ReturnObjectHandle;
import com.zkt.project.biology.constant.ReturnSimpleHandle;
import com.zkt.project.biology.entity.Cage;
import com.zkt.project.biology.entity.Order;
import com.zkt.project.biology.entity.Sample;
import com.zkt.project.biology.mapper.CageMapper;
import com.zkt.project.biology.mapper.LoginMapper;
import com.zkt.project.biology.mapper.OperatrecordMapper;
import com.zkt.project.biology.mapper.OrderMapper;
import com.zkt.project.biology.mapper.RunningMapper;
import com.zkt.project.biology.mapper.SampleMapper;
import com.zkt.project.biology.mapper.WechatusersMapper;

import net.sf.json.JSONObject;

@Service
public class P1B5Service {

	@Autowired
	private LoginMapper loginMapper;

	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private SampleMapper sampleMapper;
	
	@Autowired
	private CageMapper cageMapper;
	
	@Autowired
	private RunningMapper runningMapper;
	
	@Autowired
	private OperatrecordMapper operatrecordMapper;
	
	@Autowired
	private WechatusersMapper wechatusersMapper;
	
	//查询可修改订单
	
	public String search(JSONObject json) throws Exception {
		
		UserInfo userInfo = null;//RedisContent.getUserInfo();
		String userId = String.valueOf(userInfo.getUserId());// 锁定权限
		
		String orderNo = json.getString("orderNo");
		String cageno = json.getString("cageno");
		String classify = json.getString("classify");

		Integer draw = Integer.parseInt(json.getString("draw"));
		Integer from = Integer.parseInt(json.getString("start"));
		Integer pageSize = Integer.parseInt(json.getString("pageCount"));
		
		Map<String, Object> map = new HashMap<>();		
		map.put("senderid", userId);
		map.put("orderNo", orderNo);
		map.put("cageno", cageno);		
		map.put("classify", classify);
		map.put("pageSize", pageSize);
		map.put("from", from);
		
		List<Order> orderList = orderMapper.selectModifyOrder(map);
		Integer orderListCount = orderMapper.countModifyOrder(map);

		// 封装返回参数
		ReturnObjectHandle returnHandle = ReturnObjectHandle.createServerHandle();
		returnHandle.setDraw(draw);
		returnHandle.setData(orderList);
		returnHandle.setPageCount(pageSize);
		returnHandle.setDataMaxCount(orderListCount);
		returnHandle.setDataMaxPage(
				orderListCount % pageSize == 0 ? orderListCount / pageSize : orderListCount / pageSize + 1);
		return JSONObject.fromObject(returnHandle).toString();
	}

	
	
	//订单详情
	
	public String detail(JSONObject json) {
		
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
		}
		
		JSONObject result = new JSONObject();
		result.put("order", order);// 订单信息
		result.put("cage", cage);// 箱体信息
		result.put("sample", sample);// 样本信息
		
		ReturnSimpleHandle returnHandle = ReturnSimpleHandle.createServerHandle();
		returnHandle.setData(result);
		return JSONObject.fromObject(returnHandle).toString();
	}

	
	
	//修改订单
	
	public String editOrder(JSONObject json) throws Exception {
		
		UserInfo userInfo = null;//RedisContent.getUserInfo();
		String userName = userInfo.getUserName();
		String userId = String.valueOf(userInfo.getUserId());
		JSONObject userJson = JSONObject.fromObject(userInfo.getUser());
		String city = userJson.getString("city");
		String area = userJson.getString("area");
		
		String orderNo = json.getString("orderNo");
		String userOfficeId = json.getString("userOfficeId");// 承运单位ID
		String userHospitalId = json.getString("userHospitalId");// 医院ID
		String transport = json.getString("transport");// 承运单位
		String reciver = json.getString("reciver");// 医院
		String transport1 = json.getString("transport1");// 原承运单位
		String reciver1 = json.getString("reciver1");// 原医院
		String cageno = json.getString("cageno");// 箱子编号
		String cageno1 = json.getString("cageno1");// 原箱子编号
		String tlimitup = json.getString("tlimitup");
		String tlimitdown = json.getString("tlimitdown");
		String hlimitup = json.getString("hlimitup");
		String hlimitdown = json.getString("hlimitdown");
		String remarks = json.getString("remarks");
		String sampleclassify2 = json.getString("sampleclassify2");// 原样本类型
		
		String sign = json.getString("sign");
		String sampleclassify = json.getString("sampleclassify");
		String counter = json.getString("counter");
		
		String sign1 = json.getString("sign1");
		String sampleclassify1 = json.getString("sampleclassify1");
		String counter1 = json.getString("counter1");
		String totalNumber=json.getString("totalNumber");
		
		//用户下单后容易出现写入两个相同订单的问题，而且无法删除，同时导致扫码开锁出现问题
		//解决办法，在写入订单时，先判断是否有相同编号的箱体绑定的订单处于未完成状态，即状态不是完成和异常状态，如果存在这样的订单就不再写入
		//修改时相同订单号的可以修改
		Order isExistence = orderMapper.selectByCageno(cageno);
		if(null != isExistence && !orderNo.equals(isExistence.getOrderNo())){
			return ReturnSimpleHandle.createServerError("此箱体已绑定了订单并开始使用，请重新选择箱体!", "-1", null, null);			
		}
		
		// 保存订单
		Order order = orderMapper.selectByOrderNo(orderNo);
		if(!ValidateUtil.isEmpty(transport)){
			order.setTransportid(loginMapper.getOfficeID(transport));
			order.setReciverid(loginMapper.getHospitalID(reciver));
			order.setTransport(transport);
			order.setReciver(reciver);
		}
		if(!ValidateUtil.isEmpty(userOfficeId)){
			order.setTransportid(userOfficeId);
			order.setReciverid(userHospitalId);
			order.setTransport(loginMapper.getOffice(userOfficeId));
			order.setReciver(loginMapper.getHospital(userHospitalId));
		}
		
		if(!ValidateUtil.isEmpty(cageno)){
			// 更新箱子
			Cage cage = cageMapper.selectByCageno(cageno);
			cage.setState(Constant.CAGESTATE_ORDER);// 已绑定订单
			cageMapper.updateByPrimaryKeySelective(cage);
			
			Cage cage1 = cageMapper.selectByCageno(order.getCageNo());
			cage1.setState(Constant.CAGESTATE_FREE);
			cageMapper.updateByPrimaryKeySelective(cage1);
			
			order.setCageNo(cageno);
		}
		
		order.setSenderid(userId);// 发货医院ID
		order.setSender(loginMapper.getHospital(userId));//发货医院名称
		order.setTlimitup(tlimitup);
		order.setTlimitdown(tlimitdown);
		order.setHlimitup(hlimitup);
		order.setHlimitdown(hlimitdown);
		order.setRemarks(remarks);
		order.setCity(city);
		order.setArea(area);
		order.setUpdatedBy(userName);//订单所有关联的发 送 收 三方人员全部用账号绑定 账号不能修改锁定
		order.setUpdatedAt(new Date());
		order.setNum(totalNumber);
		if(!sampleclassify2.isEmpty() && !counter.isEmpty()){
			order.setSampleclassify(sampleclassify2);//样本类型
		}
		if(!sampleclassify.isEmpty() && !counter.isEmpty()){
			order.setSampleclassify(sampleclassify);
		}
		if(!sampleclassify1.isEmpty() && !counter1.isEmpty()){
			order.setSampleclassify(sampleclassify1);
		}
		
		sampleMapper.deleteByOrderNo(orderNo);
		Sample upSample = new Sample();
		List<Sample> sample = sampleMapper.selectBySign(sign);
		List<Sample> sample1 = sampleMapper.selectBySign(sign1);
		if(!sample.isEmpty()){
			for(int i=0; i<sample.size(); i++){
				upSample.setId(sample.get(i).getId());
				upSample.setOrderNo(orderNo);
				sampleMapper.updateByPrimaryKeySelective(upSample);
			}
		}
		if(!sample1.isEmpty()){
			for(int i=0; i<sample1.size(); i++){
				upSample.setId(sample1.get(i).getId());
				upSample.setOrderNo(orderNo);
				sampleMapper.updateByPrimaryKeySelective(upSample);
			}
		}
		
		// 关联订单流水
		/*Running running = runningMapper.selectByOrderNo(orderNo);
		running.setOperator(userName);
		running.setTime(new Date());
		runningMapper.updateByPrimaryKeySelective(running);*/
		
		// 关联操作记录
		/*Operatrecord operatrecord = operatrecordMapper.selectByOrderNo(orderNo);
		operatrecord.setCageno(cageno);
		operatrecord.setCreatedBy(userName);
		operatrecord.setCreatedAt(new Date());
		operatrecordMapper.updateByPrimaryKeySelective(operatrecord);
		
		orderMapper.updateByPrimaryKeySelective(order);*/
		
		// 下完单推送运货方
		/*HashMap<String, String> maps = new HashMap<String, String>();
		if(!ValidateUtil.isEmpty(transport)){
			maps.put("id", loginMapper.getOfficeID(transport));
		}
		if(!ValidateUtil.isEmpty(userOfficeId)){
			maps.put("id", userOfficeId);
		}
		if(!ValidateUtil.isEmpty(transport1)){
			maps.put("id", order.getTransportid());
		}
		Login login1 = loginMapper.login(maps);
		Wechatusers wechatusers = wechatusersMapper.selectByUsername(login1.getUserName());
		if(null != wechatusers && wechatusers.getUserhospitalid().equals(userOfficeId) && wechatusers.getUsertype().equals(Constant.USER_TYPE_TRANSPORT)){
			HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/handel_inform", "orderno="+orderNo+"&sender="+loginMapper.getHospital(Long.parseLong(userId))+"&openid="+wechatusers.getUserid()+"&receiver="+reciver);
		}*/

		ReturnSimpleHandle returnHandle = ReturnSimpleHandle.createServerHandle();
		return JSONObject.fromObject(returnHandle).toString();
	}
	
}
