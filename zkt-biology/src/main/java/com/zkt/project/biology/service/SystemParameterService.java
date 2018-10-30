package com.zkt.project.biology.service;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkt.common.core.util.UserInfo;
import com.zkt.project.biology.constant.ReturnSimpleHandle;
import com.zkt.project.biology.entity.SystemParameter;
import com.zkt.project.biology.mapper.SystemParameterMapper;

import net.sf.json.JSONObject;

@Service
public class SystemParameterService {

	@Autowired
	private SystemParameterMapper systemParameterMapper;
	
	//显示报警间隔
	
	public ReturnSimpleHandle searchAlarmInterval(){

		UserInfo userInfo = null;//RedisContent.getUserInfo();
		String hospitalid = String.valueOf(userInfo.getUserId());
		
		String alarmInterval = systemParameterMapper.selectAllByMap(hospitalid);
		ReturnSimpleHandle returnHandle = ReturnSimpleHandle.createServerHandle();
		returnHandle.setData(alarmInterval);
		return returnHandle;
	}
	
	//设置报警间隔
	
	public ReturnSimpleHandle saveAlarmInterval(JSONObject json){
		
		String alarmInterval = json.getString("alarmInterval");//警报时间间隔
		
		UserInfo userInfo = null;//RedisContent.getUserInfo();
		String hospitalid = String.valueOf(userInfo.getUserId());
				
		String sign = systemParameterMapper.selectAllByMap(hospitalid);
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
		return returnHandle;
	}

	
		
}
