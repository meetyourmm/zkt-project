package com.zkt.project.biology.mapper;

import com.zkt.project.biology.entity.Problemrecord;

import tk.mybatis.mapper.common.Mapper;

public interface ProblemrecordMapper extends Mapper<Problemrecord>{
	
    int deleteByPrimaryKey(String id);

    int insert(Problemrecord record);

    int insertSelective(Problemrecord record);

    Problemrecord selectByPrimaryKey(String id);
    
    Problemrecord selectByOrderNo(String orderNo);
    
    int updateByPrimaryKeySelective(Problemrecord record);

    int updateByPrimaryKey(Problemrecord record);
    
}