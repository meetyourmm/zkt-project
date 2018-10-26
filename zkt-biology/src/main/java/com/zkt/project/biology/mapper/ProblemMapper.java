package com.zkt.project.biology.mapper;

import com.zkt.project.biology.entity.Problem;

import tk.mybatis.mapper.common.Mapper;

public interface ProblemMapper extends Mapper<Problem>{
	
    int deleteByPrimaryKey(Long id);

    int insert(Problem record);

    int insertSelective(Problem record);

    Problem selectByPrimaryKey(Long id);
    
    Problem selectByOrderNo(String orderNo);
    
    int updateByPrimaryKeySelective(Problem record);

    int updateByPrimaryKey(Problem record);
    
}