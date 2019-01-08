/*
 * Copyright(C), 2015-2018, Beifeng
 * FileName: SysApiLog
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

import com.zkt.common.core.constant.UUIdGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * api接口访问日志
 *
 * @author liwei
 * @create 2018/10/17 0017
 */
@Table(name = "sys_api_log")
public class SysApiLog {
    @Id
    @KeySql(genId = UUIdGenId.class)
    @Column(name="id")
    private String id;

    private String name;
    private String uri;
    private String params;

    @Column(name = "create_time")
    private Date createTime;//创建时间

    @Column(name = "create_user")
    private String createUser;//创建人

    @Column(name = "create_host")
    private String createHost;//访问主机

    @Transient
    private String createUserName;//创建人用户名

    private Integer state;//返回状态吗
    private String result;//返回结果s
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
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

    public String getCreateHost() {
        return createHost;
    }

    public void setCreateHost(String createHost) {
        this.createHost = createHost;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }
}
