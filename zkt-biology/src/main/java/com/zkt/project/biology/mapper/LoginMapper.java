package com.zkt.project.biology.mapper;

import java.util.List;
import java.util.Map;

import com.zkt.project.biology.entity.Login;

import tk.mybatis.mapper.common.Mapper;

public interface LoginMapper extends Mapper<Login>{

	int deleteByPrimaryKey(Long id);

	int insert(Login record);

	int insertSelective(Login record);

	Login selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(Login record);

	int updateByPrimaryKey(Login record);

	List<Login> selectByKeys(Map<String, Object> map);

	int countByKeys(Map<String, Object> map);

	List<Map<String, Object>> getHospitals(String userHospital);

	List<Login> getOffices(Long id);

	List<String> getCitys();

	List<String> getAreas();

	List<String> getUserHospitals();

	List<Login> getUserHospitals1();

	List<String> getUserOffices();

	String getOperator(Long id);

	String getHospital(Long id);

	String getOffice(Long id);

	/**
	 * 登陆
	 * 
	 * @param map
	 * @return
	 */
	Login login(Map<String, String> map);

	String getCityNum();

	String getAreaNum();

	String getHospitalNum();

	String getTransportNum();

	long getRoleID(long userId);

	String getUserName(long userId);

	String getOfficeID(String transport1);

	String getHospitalID(String reciver1);

	void updateByRoleId(Login login);

}