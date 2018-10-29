package com.zkt.project.biology.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkt.common.core.util.HttpRequest;
import com.zkt.project.biology.constant.Constant;
import com.zkt.project.biology.entity.Order;
import com.zkt.project.biology.entity.Problemmonitor;
import com.zkt.project.biology.entity.Wechatusers;
import com.zkt.project.biology.mapper.CageMapper;
import com.zkt.project.biology.mapper.HumidityMapper;
import com.zkt.project.biology.mapper.LocationMapper;
import com.zkt.project.biology.mapper.LoginMapper;
import com.zkt.project.biology.mapper.OrderMapper;
import com.zkt.project.biology.mapper.ProblemmonitorMapper;
import com.zkt.project.biology.mapper.TemperatureMapper;
import com.zkt.project.biology.mapper.WechatusersMapper;

@Service
public class ProblemmonitorService {
	
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
	
	//箱子被非法打开警报
	
	public void saveProblemmonitor(String cageno, String errors) {
		
		Order order = orderMapper.selectByCageNo(cageno);
		String orderNo = order.getOrderNo();
		String senderid = order.getSenderid();
		String transportid = order.getTransportid();
		String reciverid = order.getReciverid();
		String sender = order.getSender();
		String receiver = order.getReciver();
		String createdBy = order.getCreatedBy();
		String transportOperator = order.getTransportOperator();
		
		String senderUserName = loginMapper.getUserName(senderid);
		String transportUserName = loginMapper.getUserName(transportid);
		String reciverUserName = loginMapper.getUserName(reciverid);
		
		String packOperatorUserid = "";
		String transportOperatorUserid = "";
		String receiveOperatorUserid = "";
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
		
		Problemmonitor problemmonitor = new Problemmonitor();
		problemmonitor.setOrderNo(orderNo);
		problemmonitor.setDiscribtion(errors);
		problemmonitor.setStatus(Constant.TREATMENT_SITUATION_ING);
		problemmonitor.setCreatedAt(new Date());
		problemmonitor.setSenderBy(createdBy);
		problemmonitor.setTransportBy(transportOperator);
		problemmonitorMapper.insert(problemmonitor);
		
		if("" != packOperatorUserid ){
			HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+packOperatorUserid+"&receiver="+receiver+"&discribtion="+"箱子被异常打开");			        
		}
		if("" != transportOperatorUserid ){
			HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+transportOperatorUserid+"&receiver="+receiver+"&discribtion="+"箱子被异常打开");			        
		}
		if("" != receiveOperatorUserid ){
			HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/alarm_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+receiveOperatorUserid+"&receiver="+receiver+"&discribtion="+"箱子被异常打开");			        
		}
		
		
	}
	
}
