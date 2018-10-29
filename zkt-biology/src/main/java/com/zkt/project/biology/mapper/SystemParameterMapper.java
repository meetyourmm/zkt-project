package com.zkt.project.biology.mapper;

import java.util.HashMap;

import com.zkt.project.biology.entity.SystemParameter;

import tk.mybatis.mapper.common.Mapper;

public interface SystemParameterMapper extends Mapper<SystemParameter>{
	
    int deleteByPrimaryKey(String id);

    int insert(SystemParameter record);

    int insertSelective(SystemParameter record);

    SystemParameter selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SystemParameter record);

    int updateByPrimaryKey(SystemParameter record);

	String selectAll(String hospitalid);

	void updateByHospitalid(HashMap<String, String> map);
    
}