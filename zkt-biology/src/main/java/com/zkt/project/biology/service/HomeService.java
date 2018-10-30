package com.zkt.project.biology.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkt.common.core.util.DateUtils;
import com.zkt.common.core.util.UserInfo;
import com.zkt.project.biology.constant.Constant;
import com.zkt.project.biology.constant.ReturnSimpleHandle;
//import com.mantu.system.content.RedisContent;
//import com.mantu.system.returnObj.ReturnSimpleHandle;
import com.zkt.project.biology.mapper.LoginMapper;
import com.zkt.project.biology.mapper.OrderMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class HomeService {

	@Autowired
	private LoginMapper loginMapper;
	
	@Autowired
	private OrderMapper orderMapper;
	
	//获取市区 医院 运输公司数量 及订单各参数数量
	public JSONObject getNums() {
		
		UserInfo userInfo = null;//RedisContent.getUserInfo();
		String userType = userInfo.getUserType();
		String userID = String.valueOf(userInfo.getUserId());// 锁定权限
		JSONObject userJson = JSONObject.fromObject(userInfo.getUser());
		String city = userJson.getString("city");
		String area = userJson.getString("area");
		
		//获取市 区 医院 运输公司数量
		JSONObject json = new JSONObject();
		json.put("city", loginMapper.getCityNum());
		json.put("area", loginMapper.getAreaNum());
		json.put("hospital", loginMapper.getHospitalNum());
		json.put("transport", loginMapper.getTransportNum());
		
		
		Map<String, Object> map = new HashMap<>();//各医院对应的订单总数
		Map<String, Object> map1 = new HashMap<>();//昨日各种状态订单数
		Map<String, Object> map2 = new HashMap<>();//今日各种状态订单数	
		Map<String, Object> map3 = new HashMap<>();//7日订单总数	7日订单完成数
		if(Constant.USER_TYPE_CITY.equals(userType)){
			map.put("city", city);
			map1.put("city", city);
			map2.put("city", city);
			map3.put("city", city);
		}
		
		if(Constant.USER_TYPE_AREA.equals(userType)){
			map.put("city", city);
			map.put("area", area);
			map1.put("city", city);
			map1.put("area", area);
			map2.put("city", city);
			map2.put("area", area);
			map3.put("city", city);
			map3.put("area", area);
		}
		
		if(Constant.USER_TYPE_HOSPITAL.equals(userType)){
			map1.put("senderid", userID);
			map2.put("senderid", userID);
			map3.put("senderid", userID);
		}
		
		if(Constant.USER_TYPE_TRANSPORT.equals(userType)){
			map1.put("transportid", userID);
			map2.put("transportid", userID);
			map3.put("transportid", userID);
		}
		
		JSONArray yesterdayHospitals = new JSONArray();
		JSONArray yesterdayHospitalnums = new JSONArray();
		JSONArray todayHospitals = new JSONArray();
		JSONArray todayHospitalnums = new JSONArray();
		List<Map<String, Object>> hospital = orderMapper.getYesterdayHospitalNums(map);
		for(int i=0; i<hospital.size(); i++){
			if(null == hospital.get(i).get("SENDER")){
				yesterdayHospitals.add("无数据");
			}else{
				yesterdayHospitals.add(hospital.get(i).get("SENDER"));
			}			
			yesterdayHospitalnums.add(hospital.get(i).get("NUMS"));		
		}
		
		List<Map<String, Object>> hospitalnum = orderMapper.getTodayHospitalNums(map);
		for(int i=0; i<hospitalnum.size(); i++){
			if(null == hospitalnum.get(i).get("SENDER")){
				todayHospitals.add("无数据");
				todayHospitalnums.add(0);
			}else{
				todayHospitals.add(hospitalnum.get(i).get("SENDER"));
				todayHospitalnums.add(hospitalnum.get(i).get("NUMS"));
			}						
		}
		json.put("yesterdayHospitals", yesterdayHospitals);
		json.put("yesterdayHospitalnums", yesterdayHospitalnums);
		json.put("todayHospitals", todayHospitals);
		json.put("todayHospitalnums", todayHospitalnums);
		
		//昨日各种状态订单数		
		map1.put("orderStatus", "");
		map1.put("isProblem", "");
		json.put("yesterday", orderMapper.getYesterdayNum(map1));
		
		map1.put("orderStatus", Constant.ORDER_STATUS_FINISH);
		map1.put("isProblem", Constant.IS_PROBLEM_N);
		json.put("yesterdayOver", orderMapper.getYesterdayNum(map1));
		
		map1.put("orderStatus", Constant.ORDER_STATUS_TRANSPORT);
		map1.put("isProblem", Constant.IS_PROBLEM_N);
		json.put("yesterdayTransport", orderMapper.getYesterdayNum(map1));
		
		map1.put("orderStatus", "");
		map1.put("isProblem", Constant.IS_PROBLEM_Y);
		json.put("yesterdayProblem", orderMapper.getYesterdayNum(map1));
		
		
		//今日各种状态订单数		
		map2.put("orderStatus", "");
		map2.put("isProblem", "");
		json.put("today", orderMapper.getTodayNum(map2));
		
		map2.put("orderStatus", Constant.ORDER_STATUS_FINISH);
		map2.put("isProblem", Constant.IS_PROBLEM_N);
		json.put("todayOver", orderMapper.getTodayNum(map2));
		
		map2.put("orderStatus", Constant.ORDER_STATUS_TRANSPORT);
		map2.put("isProblem", Constant.IS_PROBLEM_N);
		json.put("todayTransport", orderMapper.getTodayNum(map2));
		
		map2.put("orderStatus", "");
		map2.put("isProblem", Constant.IS_PROBLEM_Y);
		json.put("todayProblem", orderMapper.getTodayNum(map2));
		
		
		
		JSONObject sevendays = new JSONObject();
		//7日订单总数
		sevendays.put("sends", orderMapper.topSevenAll(map3));
		//7日订单完成数
		sevendays.put("overs", orderMapper.topSevenOver(map3));
		//设置7日时间显示
		Calendar instance = Calendar.getInstance();
		instance.setTime(new Date());
		JSONArray times = new JSONArray();
		for (int i = 6; i > -1; i--) {
			instance.setTime(new Date());
			instance.add(Calendar.DATE, -i);
			times.add(DateUtils.formatDate(instance.getTime(), DateUtils.MM_DD));
		}
		sevendays.put("times", times);
		json.put("sevendays", sevendays);
		
		json.put("userType", userType);//分割饼状图类型数据
//		ReturnSimpleHandle handle = ReturnSimpleHandle.createServerHandle();
//		handle.setData(json);
		
		return json;
	}
	
	
}
