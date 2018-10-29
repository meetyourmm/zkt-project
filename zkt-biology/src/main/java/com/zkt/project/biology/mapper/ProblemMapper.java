package com.zkt.project.biology.mapper;

import com.zkt.project.biology.entity.Problem;

import tk.mybatis.mapper.common.Mapper;

public interface ProblemMapper extends Mapper<Problem>{
	
    int deleteByPrimaryKey(String id);

    int insert(Problem record);

    int insertSelective(Problem record);

    Problem selectByPrimaryKey(String id);
    
    Problem selectByOrderNo(String orderNo);
    
    int updateByPrimaryKeySelective(Problem record);

    int updateByPrimaryKey(Problem record);
    
}