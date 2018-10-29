package com.zkt.project.biology.mapper;

import java.util.List;
import java.util.Map;

import com.zkt.project.biology.entity.Cage;

import tk.mybatis.mapper.common.Mapper;

public interface CageMapper  extends Mapper<Cage> {

	int deleteByPrimaryKey(String id);

	int insert(Cage record);

	int insertSelective(Cage record);

	Cage selectByPrimaryKey(String id);
	
	Cage selectByCageno(String cageno);

	int updateByPrimaryKeySelective(Cage record);

	int updateByPrimaryKey(Cage record);

	List<Cage> selectByKeys(Map<String,Object> map);
	
	List<Map<String,Object>> selectCage();
    
	List<Cage> selectCageByHospitalid(String hospitalid);
	
    int countByKeys(Map<String,Object> map);
    
    Cage login(Map<String,String> map);
    
}