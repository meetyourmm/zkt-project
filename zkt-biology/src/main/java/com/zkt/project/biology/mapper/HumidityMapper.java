package com.zkt.project.biology.mapper;

import java.util.List;

import com.zkt.project.biology.entity.Humidity;

import tk.mybatis.mapper.common.Mapper;

public interface HumidityMapper extends Mapper<Humidity>{
	
    int deleteByPrimaryKey(String id);

    int insert(Humidity record);

    int insertSelective(Humidity record);

    Humidity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Humidity record);

    int updateByPrimaryKey(Humidity record);
    
    List<Humidity> selectByOrderNo(String orderNo);
    
}