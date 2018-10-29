package com.zkt.project.biology.service;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zkt.common.core.util.UserInfo;
import com.zkt.project.biology.constant.ReturnSimpleHandle;
import com.zkt.project.biology.entity.SystemParameter;
import com.zkt.project.biology.mapper.SystemParameterMapper;

import net.sf.json.JSONObject;

@Service
public class SystemParameterService {

	@Resource(name = "systemParameterMapper")
	private SystemParameterMapper systemParameterMapper;
	
	//显示报警间隔
	
	public String searchAlarmInterval() throws Exception {

		UserInfo userInfo = null;//RedisContent.getUserInfo();
		String hospitalid = String.valueOf(userInfo.getUserId());
		
		String alarmInterval = systemParameterMapper.selectAll(hospitalid);
		ReturnSimpleHandle returnHandle = ReturnSimpleHandle.createServerHandle();
		returnHandle.setData(alarmInterval);
		return JSONObject.fromObject(returnHandle).toString();		
	}
	
	//设置报警间隔
	
	public String saveAlarmInterval(JSONObject json) throws Exception {
		
		String alarmInterval = json.getString("alarmInterval");//警报时间间隔
		
		UserInfo userInfo = null;//RedisContent.getUserInfo();
		String hospitalid = String.valueOf(userInfo.getUserId());
				
		String sign = systemParameterMapper.selectAll(hospitalid);
		if(null != sign && sign != ""){	
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("alarmInterval", alarmInterval);
			map.put("hospitalid", hospitalid);
			systemParameterMapper.updateByHospitalid(map);
		}else{
			SystemParameter systemparameter = new SystemParameter();
			systemparameter.setAlarmInterval(alarmInterval);
			systemparameter.setHospitalid(hospitalid);
			systemParameterMapper.insertSelective(systemparameter);
		}
		ReturnSimpleHandle returnHandle = ReturnSimpleHandle.createServerHandle();
		return JSONObject.fromObject(returnHandle).toString();
	}

	
		
}
