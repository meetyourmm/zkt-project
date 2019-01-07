/*
 * Copyright(C), 2015-2018, Beifeng
 * FileName: UserService
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * Email Address: liwei@ibeifeng.com
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zkt.project.admin.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zkt.common.core.constant.EnumUtil;
import com.zkt.common.core.context.UserContextHandler;
import com.zkt.common.web.constant.ResponseConstant;
import com.zkt.common.web.exception.auth.UserInvalidException;
import com.zkt.project.admin.entity.SysUser;
import com.zkt.project.admin.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用户服务
 *
 * @author liwei
 * @create 2018/10/12 0012
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


    public SysUser validate(String username, String password) throws UserInvalidException{
        SysUser info = new SysUser();
        info.setUserName(username);
        SysUser user = userMapper.selectOne(info);

        if(user == null){
            throw new UserInvalidException(ResponseConstant.EX_USER_PASS_INVALID_MSG);
        }
        if (encoder.matches(password, user.getPassword())) {
            info.setId(user.getId());
            return user;
        }

        throw new UserInvalidException(ResponseConstant.EX_USER_PASS_INVALID_MSG);

    }

    @Cacheable(value="userInfo",key="#userId")
    public SysUser getUserById(String userId){
        SysUser user = userMapper.selectByPrimaryKey(userId);
        if(user == null){
            throw new UserInvalidException(ResponseConstant.EX_USER_NON_MSG);
        }
        return user;
    }

    public PageInfo<Map> getUserPage(int pageStart, int pageSize, String name){
        PageHelper.startPage(pageStart,pageSize);

        List<Map> list = userMapper.getUserPage(name);
        return new PageInfo<>(list);
    }

    public void addUser(SysUser user){
        String operatorUserId = UserContextHandler.getUserID();
        user.setCreateTime(new Date());
        user.setCreateUser(operatorUserId);
        user.setUpdateTime(new Date());
        user.setUpdateUser(operatorUserId);
        if(StringUtils.isEmpty(user.getPassword())){
            user.setPassword(encoder.encode("123456"));//默认密码
        }
        userMapper.insertSelective(user);
    }

    public void updateUser(SysUser user){
        String operatorUserId = UserContextHandler.getUserID();
        user.setUpdateTime(new Date());
        user.setUpdateUser(operatorUserId);
        userMapper.updateByPrimaryKeySelective(user);
    }

    public void deleteUser(String userId){
        SysUser user = userMapper.selectByPrimaryKey(userId);
        user.setStatus(EnumUtil.DataStatus.DELETE.getStatus());
        userMapper.updateByPrimaryKeySelective(user);
    }

    public Boolean checkByUserName(String userName){
        return userMapper.checkByUserName(userName)==0?false:true;

    }
}
