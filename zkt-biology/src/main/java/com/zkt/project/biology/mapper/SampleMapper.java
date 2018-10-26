package com.zkt.project.biology.mapper;

import java.util.List;
import java.util.Map;

import com.zkt.project.biology.entity.Sample;

import tk.mybatis.mapper.common.Mapper;

public interface SampleMapper extends Mapper<Sample>{
	
    int deleteByPrimaryKey(Long id);

    int insert(Sample record);

    int insertSelective(Sample record);

    Sample selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Sample upSample);

    int updateByPrimaryKey(Sample record);
    
    List<Sample> selectByKeys(String cageno);
    
    List<Sample> selectByOrderNo(String orderNo);
    
    List<Sample> selectExcel(Map<String, Object> map);

	Integer countByExcel(Map<String, Object> map);

	List<Sample> selectBySign(String sign);

	void deleteByOrderNo(String orderNo);
	
}