package com.zkt.project.biology.mapper;

import java.util.HashMap;
import java.util.List;

import com.zkt.project.biology.entity.Running;

import tk.mybatis.mapper.common.Mapper;

public interface RunningMapper extends Mapper<Running>{
	
    int deleteByPrimaryKey(Integer id);

    int insert(Running record);

    int insertSelective(Running record);

    Running selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Running record);

    int updateByPrimaryKey(Running record);

	List<Running> selectByOrderNo(HashMap<String, String> maps);

	Running selectByOrderNo(String orderNo);
    
}