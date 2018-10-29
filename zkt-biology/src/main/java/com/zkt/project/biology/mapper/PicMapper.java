package com.zkt.project.biology.mapper;

import java.util.List;
import java.util.Map;

import com.zkt.project.biology.entity.Pic;

import tk.mybatis.mapper.common.Mapper;

public interface PicMapper extends Mapper<Pic>{
	
    int deleteByPrimaryKey(String id);

    int insert(Pic record);

    int insertSelective(Pic record);

    Pic selectByPrimaryKey(String id);
    
    List<Pic> selectByClassify(Map<String, Object> map);
    
    int updateByPrimaryKeySelective(Pic record);

    int updateByPrimaryKey(Pic record);
    
}