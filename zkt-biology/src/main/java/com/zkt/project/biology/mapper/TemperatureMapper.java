package com.zkt.project.biology.mapper;

import java.util.List;

import com.zkt.project.biology.entity.Temperature;

import tk.mybatis.mapper.common.Mapper;

public interface TemperatureMapper extends Mapper<Temperature>{
	
    int deleteByPrimaryKey(String id);

    int insert(Temperature record);

    int insertSelective(Temperature record);

    Temperature selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Temperature record);

    int updateByPrimaryKey(Temperature record);
    
    List<Temperature> selectByOrderNo(String orderNo);
    
}