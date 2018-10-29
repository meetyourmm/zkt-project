package com.zkt.project.biology.mapper;

import java.util.List;

import com.zkt.project.biology.entity.Location;

import tk.mybatis.mapper.common.Mapper;

public interface LocationMapper extends Mapper<Location>{
	
    int deleteByPrimaryKey(String id);

    int insert(Location record);

    int insertSelective(Location record);

    Location selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Location record);

    int updateByPrimaryKey(Location record);

	List<Location> selectByOrderNo(String orderNo);
    
}