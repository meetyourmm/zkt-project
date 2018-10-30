package com.zkt.project.biology.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkt.common.core.util.DateUtils;
import com.zkt.common.core.util.HttpRequest;
import com.zkt.project.biology.constant.Constant;
import com.zkt.project.biology.entity.Cage;
import com.zkt.project.biology.entity.Humidity;
import com.zkt.project.biology.entity.Location;
import com.zkt.project.biology.entity.Order;
import com.zkt.project.biology.entity.Problemmonitor;
import com.zkt.project.biology.entity.Temperature;
import com.zkt.project.biology.entity.Wechatusers;
import com.zkt.project.biology.mapper.CageMapper;
import com.zkt.project.biology.mapper.HumidityMapper;
import com.zkt.project.biology.mapper.LocationMapper;
import com.zkt.project.biology.mapper.LoginMapper;
import com.zkt.project.biology.mapper.OrderMapper;
import com.zkt.project.biology.mapper.ProblemmonitorMapper;
import com.zkt.project.biology.mapper.SystemParameterMapper;
import com.zkt.project.biology.mapper.TemperatureMapper;
import com.zkt.project.biology.mapper.WechatusersMapper;
import com.zkt.project.biology.utils.GPSTransformMars;
import com.zkt.project.biology.utils.ModifyData;
import com.zkt.project.biology.utils.ModifyElectricity;


@Service
public class MonitorDataService {

	@Autowired
	private TemperatureMapper temperatureMapper;

	@Autowired
	private HumidityMapper humidityMapper;

	@Autowired
	private LocationMapper locationMapper;

	@Autowired
	private LoginMapper loginMapper;

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private CageMapper cageMapper;
	
	@Autowired
	private WechatusersMapper wechatusersMapper;
	
	@Autowired
	private ProblemmonitorMapper problemmonitorMapper;
	
	@Autowired
	private SystemParameterMapper systemParameterMapper;
	
	//保存温湿度 路径
	public void saveMonitorData(String cageno, double temperature, int humidity, double battery, int lat1, int lat2, int lng1, int lng2)
			throws Exception {
		
		cageno = cageno.replaceAll(" ", "");
		//获取对应订单信息
		Order order = orderMapper.selectByCageNo1(cageno);
		String orderNo = order.getOrderNo();
		String senderid = order.getSenderid();
		String transportid = order.getTransportid();
		String reciverid = order.getReciverid();
		String sender = order.getSender();
		String receiver = order.getReciver();
		String createdBy = order.getCreatedBy();
		
		String orderStatus = order.getOrderStatus();
		
		//操作人员userName
		String sendOperator=order.getCreatedBy();//发送方操作人员
		String receiveOperator=order.getReceiveOperator();//接收方操作人员
		String transportOperator = order.getTransportOperator();//运输方操作人员
		
		//只给微信在线用户推送
		String senderUserName = loginMapper.getUserName(senderid);//发送方领导
		String transportUserName = loginMapper.getUserName(transportid);//运输方领导
		String reciverUserName = loginMapper.getUserName(reciverid);//接收方领导
		//管理员userid
		String packOperatorUserid = "";
		String transportOperatorUserid = "";
		String receiveOperatorUserid = "";
		//操作人员userId
		String sendUserId="";
		String transportUserId="";
		String receiveUserId="";
		
		Wechatusers wechatusers1 = wechatusersMapper.selectByUsername(senderUserName);
		if(null != wechatusers1){
			packOperatorUserid = wechatusers1.getUserid();
		}
		
		Wechatusers wechatusers2 = wechatusersMapper.selectByUsername(transportUserName);
		if(null != wechatusers2){
			transportOperatorUserid = wechatusers2.getUserid();
		}
		
		Wechatusers wechatusers3 = wechatusersMapper.selectByUsername(reciverUserName);
		if(null != wechatusers3){
			receiveOperatorUserid = wechatusers3.getUserid();
		}
		if(!senderUserName.equals(sendOperator)){
			Wechatusers wechatusers4 = wechatusersMapper.selectByUsername(sendOperator);
			if(null != wechatusers4){
				sendUserId=wechatusers4.getUserid();
			}
		}
		if(!transportUserName.equals(transportOperator)){
			Wechatusers wechatusers5 = wechatusersMapper.selectByUsername(transportOperator);
			if(null != wechatusers5){
				transportUserId=wechatusers5.getUserid();
			}
		}
		if(!reciverUserName.equals(receiveOperator)){
			Wechatusers wechatusers6 = wechatusersMapper.selectByUsername(receiveOperator);
			if(null != wechatusers6){
				receiveUserId=wechatusers6.getUserid();
			}
		}
		
		//解析指令参数
		temperature = temperature/10.0;
		
		humidity = humidity/10;
		
		double tlimitup = Double.parseDouble(order.getTlimitup());
		double tlimitdown = Double.parseDouble(order.getTlimitdown());
		int	hlimitup = Integer.parseInt(order.getHlimitup());
		int	hlimitdown = Integer.parseInt(order.getHlimitdown());
		
		//获取参数时间
		String alarmInterval = systemParameterMapper.selectAllByMap(senderid);
		long newAlarmInterval = ModifyData.getNewAlarmInterval(alarmInterval);//报警时间				
		long signDate1 = new Date().getTime();//当前时间
		
		//进行运输异常记录
		Problemmonitor problemmonitor = new Problemmonitor();
		
		//发车前不判断预计时间
		if(!Constant.ORDER_STATUS_PACK.equals(orderStatus)){
			//进行日期比较参数获取
			Date arrivetime = order.getArrivetime();
			Date signDate = new Date();
			
			//预计时间内未到达报警
			if(signDate.getTime() > arrivetime.getTime()){
				
				//超过预计时间只报警一次
				if(!Constant.ARRIVETIME_ERROR.equals(order.getSign())){
					//更新标记
					order.setSign(Constant.ARRIVETIME_ERROR);
					orderMapper.updateByPrimaryKeySelective(order);
					problemmonitor.setOrderNo(orderNo);
					problemmonitor.setDiscribtion(Constant.ARRIVETIME_ERROR);
					problemmonitor.setStatus(Constant.TREATMENT_SITUATION_ING);
					problemmonitor.setCreatedAt(new Date());
					problemmonitor.setSenderBy(createdBy);
					problemmonitor.setTransportBy(transportOperator);
					problemmonitorMapper.insert(problemmonitor);
					//推送
					if("" != packOperatorUserid ){
						HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+packOperatorUserid+"&receiver="+receiver+"&discribtion="+"预计时间内未到达（预计时间"+DateUtils.formatDate(arrivetime, DateUtils.YYYY_MM_DD_HH_MM_SS)+"，当前时间"+DateUtils.formatDate(signDate, DateUtils.YYYY_MM_DD_HH_MM_SS)+"）");			        
					}
					if("" != transportOperatorUserid ){
						HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+transportOperatorUserid+"&receiver="+receiver+"&discribtion="+"预计时间内未到达（预计时间"+DateUtils.formatDate(arrivetime, DateUtils.YYYY_MM_DD_HH_MM_SS)+"，当前时间"+DateUtils.formatDate(signDate, DateUtils.YYYY_MM_DD_HH_MM_SS)+"）");			        
					}
					if("" != receiveOperatorUserid ){
						HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+receiveOperatorUserid+"&receiver="+receiver+"&discribtion="+"预计时间内未到达（预计时间"+DateUtils.formatDate(arrivetime, DateUtils.YYYY_MM_DD_HH_MM_SS)+"，当前时间"+DateUtils.formatDate(signDate, DateUtils.YYYY_MM_DD_HH_MM_SS)+"）");			        
					}
					if("" != sendUserId ){
						HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+sendUserId+"&receiver="+receiver+"&discribtion="+"预计时间内未到达（预计时间"+DateUtils.formatDate(arrivetime, DateUtils.YYYY_MM_DD_HH_MM_SS)+"，当前时间"+DateUtils.formatDate(signDate, DateUtils.YYYY_MM_DD_HH_MM_SS)+"）");			        
					}
					if("" != transportUserId ){
						HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+transportUserId+"&receiver="+receiver+"&discribtion="+"预计时间内未到达（预计时间"+DateUtils.formatDate(arrivetime, DateUtils.YYYY_MM_DD_HH_MM_SS)+"，当前时间"+DateUtils.formatDate(signDate, DateUtils.YYYY_MM_DD_HH_MM_SS)+"）");			        
					}
					if("" != receiveUserId ){
						HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+receiveUserId+"&receiver="+receiver+"&discribtion="+"预计时间内未到达（预计时间"+DateUtils.formatDate(arrivetime, DateUtils.YYYY_MM_DD_HH_MM_SS)+"，当前时间"+DateUtils.formatDate(signDate, DateUtils.YYYY_MM_DD_HH_MM_SS)+"）");			        
					}
				}			
							
			}
		}
				
				
		
		//温度下限异常
		if(temperature < tlimitdown){	
			
			//第一次异常
			if(!Constant.TLIMITDOWN_ERROR.equals(order.getSign2())){
				order.setAlarmTime1(new Date());
				order.setSign2(Constant.TLIMITDOWN_ERROR);
				orderMapper.updateByPrimaryKeySelective(order);
				problemmonitor.setOrderNo(orderNo);
				problemmonitor.setDiscribtion(Constant.TLIMITDOWN_ERROR);
				problemmonitor.setStatus(Constant.TREATMENT_SITUATION_ING);
				problemmonitor.setCreatedAt(new Date());
				problemmonitor.setSenderBy(createdBy);
				problemmonitor.setTransportBy(transportOperator);
				problemmonitorMapper.insert(problemmonitor);
				if("" != packOperatorUserid ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+packOperatorUserid+"&receiver="+receiver+"&discribtion="+"温度报警下限值（下限值"+tlimitdown+"℃，当前值"+temperature+"℃）");			        
				}
				if("" != transportOperatorUserid ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+transportOperatorUserid+"&receiver="+receiver+"&discribtion="+"温度报警下限值（下限值"+tlimitdown+"℃，当前值"+temperature+"℃）");			        
				}
				if("" != receiveOperatorUserid ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+receiveOperatorUserid+"&receiver="+receiver+"&discribtion="+"温度报警下限值（下限值"+tlimitdown+"℃，当前值"+temperature+"℃）");			        
				}
				if("" != sendUserId ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+sendUserId+"&receiver="+receiver+"&discribtion="+"温度报警下限值（下限值"+tlimitdown+"℃，当前值"+temperature+"℃）");			        
				}
				if("" != transportUserId ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+transportUserId+"&receiver="+receiver+"&discribtion="+"温度报警下限值（下限值"+tlimitdown+"℃，当前值"+temperature+"℃）");			        
				}
				if("" != receiveUserId ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+receiveUserId+"&receiver="+receiver+"&discribtion="+"温度报警下限值（下限值"+tlimitdown+"℃，当前值"+temperature+"℃）");			        
				}
			}
			
			//第二次异常
			else if(Constant.TLIMITDOWN_ERROR.equals(order.getSign2())){				
			//进行日期比较参数获取
			long alarmTime = order.getAlarmTime1().getTime() + newAlarmInterval;			
			//第二次异常(报警后超过指定时间再报警)
			if(signDate1 > alarmTime){
				//更新标记
				order.setAlarmTime1(new Date());
				orderMapper.updateByPrimaryKeySelective(order);
				problemmonitor.setOrderNo(orderNo);
				problemmonitor.setDiscribtion(Constant.TLIMITDOWN_ERROR);
				problemmonitor.setStatus(Constant.TREATMENT_SITUATION_ING);
				problemmonitor.setCreatedAt(new Date());
				problemmonitor.setSenderBy(createdBy);
				problemmonitor.setTransportBy(transportOperator);
				problemmonitorMapper.insert(problemmonitor);
				if("" != packOperatorUserid ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+packOperatorUserid+"&receiver="+receiver+"&discribtion="+"温度报警下限值（下限值"+tlimitdown+"℃，当前值"+temperature+"℃）");			        
				}
				if("" != transportOperatorUserid ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+transportOperatorUserid+"&receiver="+receiver+"&discribtion="+"温度报警下限值（下限值"+tlimitdown+"℃，当前值"+temperature+"℃）");			        
				}
				if("" != receiveOperatorUserid ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+receiveOperatorUserid+"&receiver="+receiver+"&discribtion="+"温度报警下限值（下限值"+tlimitdown+"℃，当前值"+temperature+"℃）");			        
				}
				if("" != sendUserId ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+receiveUserId+"&receiver="+receiver+"&discribtion="+"温度报警下限值（下限值"+tlimitdown+"℃，当前值"+temperature+"℃）");			        
				}
				if("" != transportUserId ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+transportUserId+"&receiver="+receiver+"&discribtion="+"温度报警下限值（下限值"+tlimitdown+"℃，当前值"+temperature+"℃）");			        
				}
				if("" != receiveUserId ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+receiveUserId+"&receiver="+receiver+"&discribtion="+"温度报警下限值（下限值"+tlimitdown+"℃，当前值"+temperature+"℃）");			        
				}
			}
			
		}
			
		//再次接受数据无异常就把标记归零等待下次报警
		}else{	
			order.setAlarmTime1(new Date());
			order.setSign2(Constant.RETURN_TO_ZERO);
			orderMapper.updateByPrimaryKeySelective(order);
		}
		
		
		
		
		//温度上限异常
		if(temperature > tlimitup){
			
			if(!Constant.TLIMITUP_ERROR.equals(order.getSign1())){
				order.setAlarmTime(new Date());
				order.setSign1(Constant.TLIMITUP_ERROR);
				orderMapper.updateByPrimaryKeySelective(order);
				problemmonitor.setOrderNo(orderNo);
				problemmonitor.setDiscribtion(Constant.TLIMITUP_ERROR);
				problemmonitor.setStatus(Constant.TREATMENT_SITUATION_ING);
				problemmonitor.setCreatedAt(new Date());
				problemmonitor.setSenderBy(createdBy);
				problemmonitor.setTransportBy(transportOperator);
				problemmonitorMapper.insert(problemmonitor);
				if("" != packOperatorUserid ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+packOperatorUserid+"&receiver="+receiver+"&discribtion="+"温度报警上限值（上限值"+tlimitup+"℃，当前值"+temperature+"℃）");			        
				}
				if("" != transportOperatorUserid ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+transportOperatorUserid+"&receiver="+receiver+"&discribtion="+"温度报警上限值（上限值"+tlimitup+"℃，当前值"+temperature+"℃）");			        
				}
				if("" != receiveOperatorUserid ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+receiveOperatorUserid+"&receiver="+receiver+"&discribtion="+"温度报警上限值（上限值"+tlimitup+"℃，当前值"+temperature+"℃）");			        
				}
				if("" != sendUserId ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+sendUserId+"&receiver="+receiver+"&discribtion="+"温度报警上限值（上限值"+tlimitup+"℃，当前值"+temperature+"℃）");			        
				}
				if("" != transportUserId ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+transportUserId+"&receiver="+receiver+"&discribtion="+"温度报警上限值（上限值"+tlimitup+"℃，当前值"+temperature+"℃）");			        
				}
				if("" != receiveUserId ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+receiveUserId+"&receiver="+receiver+"&discribtion="+"温度报警上限值（上限值"+tlimitup+"℃，当前值"+temperature+"℃）");			        
				}
				
		}else if(Constant.TLIMITUP_ERROR.equals(order.getSign1())){
			
			long alarmTime = order.getAlarmTime().getTime() + newAlarmInterval;
			
			if(signDate1 > alarmTime){	
				order.setAlarmTime(new Date());
				orderMapper.updateByPrimaryKeySelective(order);
				problemmonitor.setOrderNo(orderNo);
				problemmonitor.setDiscribtion(Constant.TLIMITUP_ERROR);
				problemmonitor.setStatus(Constant.TREATMENT_SITUATION_ING);
				problemmonitor.setCreatedAt(new Date());
				problemmonitor.setSenderBy(createdBy);
				problemmonitor.setTransportBy(transportOperator);
				problemmonitorMapper.insert(problemmonitor);
				if("" != packOperatorUserid ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+packOperatorUserid+"&receiver="+receiver+"&discribtion="+"温度报警上限值（上限值"+tlimitup+"℃，当前值"+temperature+"℃）");			        
				}
				if("" != transportOperatorUserid ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+transportOperatorUserid+"&receiver="+receiver+"&discribtion="+"温度报警上限值（上限值"+tlimitup+"℃，当前值"+temperature+"℃）");			        
				}
				if("" != receiveOperatorUserid ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+receiveOperatorUserid+"&receiver="+receiver+"&discribtion="+"温度报警上限值（上限值"+tlimitup+"℃，当前值"+temperature+"℃）");			        
				}
				if("" != sendUserId ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+sendUserId+"&receiver="+receiver+"&discribtion="+"温度报警上限值（上限值"+tlimitup+"℃，当前值"+temperature+"℃）");			        
				}
				if("" != transportUserId ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+transportUserId+"&receiver="+receiver+"&discribtion="+"温度报警上限值（上限值"+tlimitup+"℃，当前值"+temperature+"℃）");			        
				}
				if("" != receiveUserId ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+receiveUserId+"&receiver="+receiver+"&discribtion="+"温度报警上限值（上限值"+tlimitup+"℃，当前值"+temperature+"℃）");			        
				}
			}
			
		}
		
		}else{
			order.setAlarmTime(new Date());
			order.setSign1(Constant.RETURN_TO_ZERO);
			orderMapper.updateByPrimaryKeySelective(order);
		}
		
		//保存温度记录
		Temperature temperatures = new Temperature();
		temperatures.setCageno(cageno);
		temperatures.setOrderId(orderNo);
		temperatures.setUploadtime(new Date());
		temperatures.setTemperature(String.valueOf(temperature));// 上传的温度
		temperatureMapper.insertSelective(temperatures);
		
		
		
		
		//湿度下限异常
		if(humidity < hlimitdown){
			
			if(!Constant.HLIMITDOWN_ERROR.equals(order.getSign4())){
				order.setAlarmTime3(new Date());
				order.setSign4(Constant.HLIMITDOWN_ERROR);
				orderMapper.updateByPrimaryKeySelective(order);
				problemmonitor.setOrderNo(orderNo);
				problemmonitor.setDiscribtion(Constant.HLIMITDOWN_ERROR);
				problemmonitor.setStatus(Constant.TREATMENT_SITUATION_ING);
				problemmonitor.setCreatedAt(new Date());
				problemmonitor.setSenderBy(createdBy);
				problemmonitor.setTransportBy(transportOperator);
				problemmonitorMapper.insert(problemmonitor);
				if("" != packOperatorUserid ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+packOperatorUserid+"&receiver="+receiver+"&discribtion="+"湿度报警下限值（下限值"+hlimitdown+"%，当前值"+humidity+"%）");			        
				}
				if("" != transportOperatorUserid ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+transportOperatorUserid+"&receiver="+receiver+"&discribtion="+"湿度报警下限值（下限值"+hlimitdown+"%，当前值"+humidity+"%）");			        
				}
				if("" != receiveOperatorUserid ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+receiveOperatorUserid+"&receiver="+receiver+"&discribtion="+"湿度报警下限值（下限值"+hlimitdown+"%，当前值"+humidity+"%）");			        
				}
				if("" != sendUserId ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+sendUserId+"&receiver="+receiver+"&discribtion="+"湿度报警下限值（下限值"+hlimitdown+"%，当前值"+humidity+"%）");			        
				}
				if("" != transportUserId ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+transportUserId+"&receiver="+receiver+"&discribtion="+"湿度报警下限值（下限值"+hlimitdown+"%，当前值"+humidity+"%）");			        
				}
				if("" != receiveUserId ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+receiveUserId+"&receiver="+receiver+"&discribtion="+"湿度报警下限值（下限值"+hlimitdown+"%，当前值"+humidity+"%）");			        
				}
						
		}else if(Constant.HLIMITDOWN_ERROR.equals(order.getSign4())){
			
			long alarmTime = order.getAlarmTime3().getTime() + newAlarmInterval;
			
			if(signDate1 > alarmTime){
				order.setAlarmTime3(new Date());
				orderMapper.updateByPrimaryKeySelective(order);
				problemmonitor.setOrderNo(orderNo);
				problemmonitor.setDiscribtion(Constant.HLIMITDOWN_ERROR);
				problemmonitor.setStatus(Constant.TREATMENT_SITUATION_ING);
				problemmonitor.setCreatedAt(new Date());
				problemmonitor.setSenderBy(createdBy);
				problemmonitor.setTransportBy(transportOperator);
				problemmonitorMapper.insert(problemmonitor);
				if("" != packOperatorUserid ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+packOperatorUserid+"&receiver="+receiver+"&discribtion="+"湿度报警下限值（下限值"+hlimitdown+"%，当前值"+humidity+"%）");			        
				}
				if("" != transportOperatorUserid ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+transportOperatorUserid+"&receiver="+receiver+"&discribtion="+"湿度报警下限值（下限值"+hlimitdown+"%，当前值"+humidity+"%）");			        
				}
				if("" != receiveOperatorUserid ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+receiveOperatorUserid+"&receiver="+receiver+"&discribtion="+"湿度报警下限值（下限值"+hlimitdown+"%，当前值"+humidity+"%）");			        
				}
				if("" != sendUserId ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+sendUserId+"&receiver="+receiver+"&discribtion="+"湿度报警下限值（下限值"+hlimitdown+"%，当前值"+humidity+"%）");			        
				}
				if("" != transportUserId ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+transportUserId+"&receiver="+receiver+"&discribtion="+"湿度报警下限值（下限值"+hlimitdown+"%，当前值"+humidity+"%）");			        
				}
				if("" != receiveUserId ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+receiveUserId+"&receiver="+receiver+"&discribtion="+"湿度报警下限值（下限值"+hlimitdown+"%，当前值"+humidity+"%）");			        
				}
			}
			
		}
		
		}else{
			order.setAlarmTime3(new Date());
			order.setSign4(Constant.RETURN_TO_ZERO);
			orderMapper.updateByPrimaryKeySelective(order);
		}
		
		
		
		
		//湿度上限异常
		if(humidity > hlimitup){
			
			if(!Constant.HLIMITUP_ERROR.equals(order.getSign3())){
				order.setAlarmTime2(new Date());
				order.setSign3(Constant.HLIMITUP_ERROR);
				orderMapper.updateByPrimaryKeySelective(order);
				problemmonitor.setOrderNo(orderNo);
				problemmonitor.setDiscribtion(Constant.HLIMITUP_ERROR);
				problemmonitor.setStatus(Constant.TREATMENT_SITUATION_ING);
				problemmonitor.setCreatedAt(new Date());
				problemmonitor.setSenderBy(createdBy);
				problemmonitor.setTransportBy(transportOperator);
				problemmonitorMapper.insert(problemmonitor);
				if("" != packOperatorUserid ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+packOperatorUserid+"&receiver="+receiver+"&discribtion="+"湿度报警上限值（上限值"+hlimitup+"%，当前值"+humidity+"%）");			        
				}
				if("" != transportOperatorUserid ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+transportOperatorUserid+"&receiver="+receiver+"&discribtion="+"湿度报警上限值（上限值"+hlimitup+"%，当前值"+humidity+"%）");			        
				}
				if("" != receiveOperatorUserid ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+receiveOperatorUserid+"&receiver="+receiver+"&discribtion="+"湿度报警上限值（上限值"+hlimitup+"%，当前值"+humidity+"%）");			        
				}
				if("" != sendUserId ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+sendUserId+"&receiver="+receiver+"&discribtion="+"湿度报警上限值（上限值"+hlimitup+"%，当前值"+humidity+"%）");			        
				}
				if("" != transportUserId ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+transportUserId+"&receiver="+receiver+"&discribtion="+"湿度报警上限值（上限值"+hlimitup+"%，当前值"+humidity+"%）");			        
				}
				if("" != receiveUserId ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+receiveUserId+"&receiver="+receiver+"&discribtion="+"湿度报警上限值（上限值"+hlimitup+"%，当前值"+humidity+"%）");			        
				}
								
		}else if(Constant.HLIMITUP_ERROR.equals(order.getSign3())){
			
			long alarmTime = order.getAlarmTime2().getTime() + newAlarmInterval;
			
			if(signDate1 > alarmTime){
				order.setAlarmTime2(new Date());
				orderMapper.updateByPrimaryKeySelective(order);
				problemmonitor.setOrderNo(orderNo);
				problemmonitor.setDiscribtion(Constant.HLIMITUP_ERROR);
				problemmonitor.setStatus(Constant.TREATMENT_SITUATION_ING);
				problemmonitor.setCreatedAt(new Date());
				problemmonitor.setSenderBy(createdBy);
				problemmonitor.setTransportBy(transportOperator);
				problemmonitorMapper.insert(problemmonitor);
				if("" != packOperatorUserid ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+packOperatorUserid+"&receiver="+receiver+"&discribtion="+"湿度报警上限值（上限值"+hlimitup+"%，当前值"+humidity+"%）");			        
				}
				if("" != transportOperatorUserid ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+transportOperatorUserid+"&receiver="+receiver+"&discribtion="+"湿度报警上限值（上限值"+hlimitup+"%，当前值"+humidity+"%）");			        
				}
				if("" != receiveOperatorUserid ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+receiveOperatorUserid+"&receiver="+receiver+"&discribtion="+"湿度报警上限值（上限值"+hlimitup+"%，当前值"+humidity+"%）");			        
				}
				if("" != sendUserId ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+sendUserId+"&receiver="+receiver+"&discribtion="+"湿度报警上限值（上限值"+hlimitup+"%，当前值"+humidity+"%）");			        
				}
				if("" != transportUserId ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+transportUserId+"&receiver="+receiver+"&discribtion="+"湿度报警上限值（上限值"+hlimitup+"%，当前值"+humidity+"%）");			        
				}
				if("" != receiveUserId ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+receiveUserId+"&receiver="+receiver+"&discribtion="+"湿度报警上限值（上限值"+hlimitup+"%，当前值"+humidity+"%）");			        
				}
			}
			
		}
		
		}else{
			order.setAlarmTime2(new Date());
			order.setSign3(Constant.RETURN_TO_ZERO);
			orderMapper.updateByPrimaryKeySelective(order);
		}
		
		//保存湿度记录	
		Humidity humiditys = new Humidity();
		humiditys.setCageno(cageno);
		humiditys.setOrderId(orderNo);
		humiditys.setUploadtime(new Date());
		humiditys.setHumidity(String.valueOf(humidity));// 上传的湿度
		humidityMapper.insertSelective(humiditys);
		
		
		
		//箱子电池电量低于20%报警
		int electricity = ModifyElectricity.getElectricity(battery);
		
		if(electricity < 20){
			
			if(!Constant.ELECTRICITY_ERROR.equals(order.getSign6())){
				order.setAlarmTime4(new Date());
				order.setSign6(Constant.ELECTRICITY_ERROR);
				orderMapper.updateByPrimaryKeySelective(order);
				problemmonitor.setOrderNo(orderNo);
				problemmonitor.setDiscribtion(Constant.ELECTRICITY_ERROR);
				problemmonitor.setStatus(Constant.TREATMENT_SITUATION_ING);
				problemmonitor.setCreatedAt(new Date());
				problemmonitor.setSenderBy(createdBy);
				problemmonitor.setTransportBy(transportOperator);
				problemmonitorMapper.insert(problemmonitor);
				if("" != packOperatorUserid ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+packOperatorUserid+"&receiver="+receiver+"&discribtion="+"电池电量过低（当前值"+electricity+"%）");			        
				}
				if("" != transportOperatorUserid ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+transportOperatorUserid+"&receiver="+receiver+"&discribtion="+"电池电量过低（当前值"+electricity+"%）");			        
				}
				if("" != receiveOperatorUserid ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+receiveOperatorUserid+"&receiver="+receiver+"&discribtion="+"电池电量过低（当前值"+electricity+"%）");			        
				}
				if("" != sendUserId ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+sendUserId+"&receiver="+receiver+"&discribtion="+"电池电量过低（当前值"+electricity+"%）");			        
				}
				if("" != transportUserId ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+transportUserId+"&receiver="+receiver+"&discribtion="+"电池电量过低（当前值"+electricity+"%）");			        
				}
				if("" != receiveUserId ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+receiveUserId+"&receiver="+receiver+"&discribtion="+"电池电量过低（当前值"+electricity+"%）");			        
				}
			
		}else if(Constant.ELECTRICITY_ERROR.equals(order.getSign6())){
			
			long alarmTime = order.getAlarmTime4().getTime() + newAlarmInterval;
			
			if(signDate1 > alarmTime){
				order.setAlarmTime4(new Date());
				orderMapper.updateByPrimaryKeySelective(order);
				problemmonitor.setOrderNo(orderNo);
				problemmonitor.setDiscribtion(Constant.ELECTRICITY_ERROR);
				problemmonitor.setStatus(Constant.TREATMENT_SITUATION_ING);
				problemmonitor.setCreatedAt(new Date());
				problemmonitor.setSenderBy(createdBy);
				problemmonitor.setTransportBy(transportOperator);
				problemmonitorMapper.insert(problemmonitor);
				if("" != packOperatorUserid ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+packOperatorUserid+"&receiver="+receiver+"&discribtion="+"电池电量过低（当前值"+electricity+"%）");			        
				}
				if("" != transportOperatorUserid ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+transportOperatorUserid+"&receiver="+receiver+"&discribtion="+"电池电量过低（当前值"+electricity+"%）");			        
				}
				if("" != receiveOperatorUserid ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+receiveOperatorUserid+"&receiver="+receiver+"&discribtion="+"电池电量过低（当前值"+electricity+"%）");			        
				}
				if("" != sendUserId ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+sendUserId+"&receiver="+receiver+"&discribtion="+"电池电量过低（当前值"+electricity+"%）");			        
				}
				if("" != transportUserId ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+transportUserId+"&receiver="+receiver+"&discribtion="+"电池电量过低（当前值"+electricity+"%）");			        
				}
				if("" != receiveUserId ){
					HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+receiveUserId+"&receiver="+receiver+"&discribtion="+"电池电量过低（当前值"+electricity+"%）");			        
				}
			}
			
		}
			
		}else{
			order.setAlarmTime4(new Date());
			order.setSign6(Constant.RETURN_TO_ZERO);
			orderMapper.updateByPrimaryKeySelective(order);
		}
		
		//更新箱子电池电量	
		Cage cage = cageMapper.selectByCageno(cageno);
		cage.setBattery(String.valueOf(electricity));
		cageMapper.updateByPrimaryKeySelective(cage);				
		
		
		
		//箱子初始化时GPS未启用防止空数据接入		
		if(lat1 != 0){
			Location location = new Location();
			String sign3 = String.valueOf(lat1) + "." + String.valueOf(lat2);
			String sign4 = String.valueOf(lng1) + "." + String.valueOf(lng2);
			double wgLat = Double.parseDouble(sign3);		
			double wgLng = Double.parseDouble(sign4);
			
			double [] ds = GPSTransformMars.transLatLng(wgLat, wgLng);
			location.setLat(String.valueOf(ds[0]));// 上传的纬度
			location.setLng(String.valueOf(ds[1]));// 上传的经度
			location.setCageno(cageno);
			location.setOrderId(orderNo);
			location.setCreatedAt(new Date());		
			locationMapper.insertSelective(location);
		}
		
		
	}

}
