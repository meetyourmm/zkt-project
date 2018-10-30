package com.zkt.project.biology.mapper;

import java.util.List;
import java.util.Map;

import com.zkt.project.biology.entity.Problemmonitor;

import tk.mybatis.mapper.common.Mapper;

public interface ProblemmonitorMapper extends Mapper<Problemmonitor>{
	
    int deleteByPrimaryKey(String id);

    int insert(Problemmonitor record);

    int insertSelective(Problemmonitor record);

    Problemmonitor selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Problemmonitor record);

    int updateByPrimaryKey(Problemmonitor record);

	List<Problemmonitor> selectByOrderNo(String orderNo);

	List<Problemmonitor> selectAllByMap(Map<String, Object> map);

	Integer countAll(Map<String, Object> map);

	List<Problemmonitor> selectAllOver(Map<String, Object> map);

	Integer countAllOver(Map<String, Object> map);
    
}