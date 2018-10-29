package com.zkt.project.biology.mapper;

import java.util.List;
import java.util.Map;

import com.zkt.project.biology.entity.Model;

import tk.mybatis.mapper.common.Mapper;

public interface ModelMapper extends Mapper<Model>{
	
    int deleteByPrimaryKey(String id);

    int insert(Model record);

    int insertSelective(Model record);

    Model selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Model record);

    int updateByPrimaryKey(Model record);
    
    //获取Model数据自动填充页面
	List<Model> selectModel(Map<String, Object> map);
	
	//获取modelNo列表
	List<Model> getModelNo(Map<String, Object> map);

	List<Model> selectWeChatModel(Map<String, Object> map);

	List<Model> selectAll(Map<String, Object> map);
	
	Integer countAll(Map<String, Object> map);

	Model selectByModelNo(String modelNo);
    
}