package com.zkt.project.biology.mapper;

import com.zkt.project.biology.entity.Operatrecord;

import tk.mybatis.mapper.common.Mapper;

public interface OperatrecordMapper extends Mapper<Operatrecord>{
	
    int deleteByPrimaryKey(Long id);

    int insert(Operatrecord record);

    int insertSelective(Operatrecord record);

    Operatrecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Operatrecord record);

    int updateByPrimaryKey(Operatrecord record);
    
    Operatrecord selectByOrderNo(String orderNo);
    
}