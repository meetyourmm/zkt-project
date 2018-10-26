package com.zkt.project.biology.mapper;

import java.util.List;

import com.zkt.project.biology.entity.AuthRes;

import tk.mybatis.mapper.common.Mapper;

public interface AuthResMapper extends Mapper<AuthRes>{

	int deleteByPrimaryKey(Long id);

	int insert(AuthRes record);

	int insertSelective(AuthRes record);

	AuthRes selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(AuthRes record);

	int updateByPrimaryKey(AuthRes record);

	List<String> selectByUserName(String userName);

	int deleteByUserName(String userName);

}