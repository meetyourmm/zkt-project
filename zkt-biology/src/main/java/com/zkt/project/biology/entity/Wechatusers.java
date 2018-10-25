package com.zkt.project.biology.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zkt.common.core.constant.UUIdGenId;

import tk.mybatis.mapper.annotation.KeySql;

@Table(name = "bio_wechatusers")
public class Wechatusers implements Serializable {
	
	@Id
    @KeySql(genId = UUIdGenId.class)
    private String id;
    //用户ID
	@Column(name="user_id")
    private String userid;
    //账号
	@Column(name="user_name")
    private String username;
    //密码
    private String password;
    //操作员姓名
    private String operator;
    //操作员单位	可能是医院也有可能是运输单位
    @Column(name="user_hospital")
    private String userhospital;
    //操作员单位ID
    @Column(name="user_hospital_id")
    private String userhospitalid;
    //操作员类型	4是医院操作员，6是运输员，9是白卡
    @Column(name="user_type")
    private String usertype;
    //登入时间
    @Column(name="last_time")
    private String lasttime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getUserhospital() {
        return userhospital;
    }

    public void setUserhospital(String userhospital) {
        this.userhospital = userhospital == null ? null : userhospital.trim();
    }

    public String getUserhospitalid() {
        return userhospitalid;
    }

    public void setUserhospitalid(String userhospitalid) {
        this.userhospitalid = userhospitalid == null ? null : userhospitalid.trim();
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype == null ? null : usertype.trim();
    }

    public String getLasttime() {
        return lasttime;
    }

    public void setLasttime(String lasttime) {
        this.lasttime = lasttime == null ? null : lasttime.trim();
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
    
}