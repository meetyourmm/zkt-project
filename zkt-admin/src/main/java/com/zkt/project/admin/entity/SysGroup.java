/*
 * Copyright(C), 2015-2018, Beifeng
 * FileName: SysGroup
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
package com.zkt.project.admin.entity;

import com.zkt.common.core.constant.CommonConstant;
import com.zkt.common.core.constant.EnumUtil;
import com.zkt.common.core.constant.UUIdGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 权限组
 *
 * @author liwei
 * @create 2018/10/16 0016
 */
@Table(name = "sys_group")
public class SysGroup implements Serializable{
    @Id
    @KeySql(genId = UUIdGenId.class)
    @Column(name="id")
    private String id;

    private String code;//编码

    private String name;//名称

    @Column(name = "parent_id")
    private String parentId = CommonConstant.ROOT_NODE;//父节点

    private String path;//树形层级关系

    @Column(name = "group_type")
    private String groupType ;//组类型1:role 、2:depart

    @Column(name = "order_num")
    private Integer orderNum;//排序

    private String description;//描述

    @Column(name = "create_time")
    private Date createTime;//创建时间

    @Column(name = "create_user")
    private String createUser;//创建人

    @Column(name = "update_time")
    private Date updateTime;//最后更新时间

    @Column(name = "update_user")
    private String updateUser;//最后更新人

    private String status = EnumUtil.DataStatus.ENABLE.getStatus();//状态(1 启用 ,2 禁用 , 3 删除)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
}
