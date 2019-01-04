/*
 * Copyright(C), 2015-2018, Beifeng
 * FileName: GroupService
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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zkt.common.core.constant.CommonConstant;
import com.zkt.common.core.constant.EnumUtil;
import com.zkt.common.core.context.UserContextHandler;
import com.zkt.common.web.response.ApiResponse;
import com.zkt.project.admin.entity.SysGroup;
import com.zkt.project.admin.entity.SysGroupUser;
import com.zkt.project.admin.entity.SysMenu;
import com.zkt.project.admin.mapper.GroupMapper;
import com.zkt.project.admin.mapper.GroupUserMapper;
import com.zkt.project.admin.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 权限组或部门service
 *
 * @author liwei
 * @create 2018/10/17 0017
 */
@Service
public class GroupService {
    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private GroupUserMapper groupUserMapper;

    @Autowired
    private UserMapper userMapper;
    /**
     * 保存组织结构
     * @param group
     */
    public void addGroup(SysGroup group){
        if(group.getParentId().equals(CommonConstant.ROOT_NODE)){
            group.setPath("/"+group.getCode());
        }else{
            SysGroup parent = groupMapper.selectByPrimaryKey(group.getParentId());
            group.setPath(parent.getPath() + "/" + group.getCode());
        }
        String operatorUserId = UserContextHandler.getUserID();
        group.setCreateTime(new Date());
        group.setCreateUser(operatorUserId);
        group.setUpdateTime(new Date());
        group.setUpdateUser(operatorUserId);
        groupMapper.insertSelective(group);
    }
    public SysGroup getGroupById(String groupId){
        return groupMapper.selectByPrimaryKey(groupId);
    }
    public void updateGroup(SysGroup group) {
        String operatorUserId = UserContextHandler.getUserID();
        group.setUpdateTime(new Date());
        group.setUpdateUser(operatorUserId);
        groupMapper.updateByPrimaryKeySelective(group);
    }
    public List<SysGroup> getDeaprtTreeByTitle(String name,String type) {
        Example example = new Example(SysGroup.class);
        Example.Criteria crt = example.createCriteria();
        if (!StringUtils.isEmpty(name)) {
            crt.andLike("name", "%" + name + "%");
        }
        crt.andEqualTo("groupType", type);
        example.setOrderByClause("order_num asc");
        return groupMapper.selectByExample(example);
    }

    public void deleteGroup(String id) {
        groupMapper.deleteByPrimaryKey(id);
    }

    public Object validateCode(String code) {
        return groupMapper.checkByCode(code)==0?false:true;
    }

    public PageInfo<Map> getGroupUserPage(int pageStart, int pageSize, String groupId, String name) {
        PageHelper.startPage(pageStart,pageSize);

        List<Map> list = userMapper.getGroupUserPage(groupId,name);
        return new PageInfo<>(list);
    }

    public void deleteGroupUsers(String ids,String groupId) {
        groupUserMapper.deleteGroupUsers(ids,groupId);
    }

    @Transactional
    public void addGroupUsers(String ids, String groupId) {
        String [] idList = ids.split(",");
        for(String id :idList){
            SysGroupUser guser = new SysGroupUser();
            guser.setGroupId(groupId);
            guser.setUserId(id);
            if(groupUserMapper.selectOne(guser)== null){
                groupUserMapper.insertSelective(guser);
            }
        }
    }
}
