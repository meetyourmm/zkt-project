package com.zkt.project.biology.mapper;

import java.util.List;

import com.zkt.project.biology.entity.Login;
import com.zkt.project.biology.entity.Relate;

import tk.mybatis.mapper.common.Mapper;

public interface RelateMapper extends Mapper<Relate>{
	
    int deleteByPrimaryKey(String id);
    
    int deleteByHospitalid(String hospitalid);
    
    int insert(Relate record);

    int insertSelective(Relate record);

    Relate selectByPrimaryKey(String id);
    
    List<Login> selectByUserName(String userName);
    
    int updateByPrimaryKeySelective(Relate record);

    int updateByPrimaryKey(Relate record);
    
}