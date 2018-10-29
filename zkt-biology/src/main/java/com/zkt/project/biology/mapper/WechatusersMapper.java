package com.zkt.project.biology.mapper;

import com.zkt.project.biology.entity.Wechatusers;

import tk.mybatis.mapper.common.Mapper;

public interface WechatusersMapper extends Mapper<Wechatusers>{
	
    int deleteByPrimaryKey(String id);
    
    void deleteByUserid(String userid);
    
    int insert(Wechatusers record);

    int insertSelective(Wechatusers record);

    Wechatusers selectByPrimaryKey(String id);
    
    Wechatusers selectByUsername(String username);
    
    int updateByPrimaryKeySelective(Wechatusers record);

    int updateByPrimaryKey(Wechatusers record);
    
    
}