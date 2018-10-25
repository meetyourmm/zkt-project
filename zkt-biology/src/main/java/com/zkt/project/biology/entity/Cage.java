package com.zkt.project.biology.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zkt.common.core.constant.UUIdGenId;

import tk.mybatis.mapper.annotation.KeySql;

//箱体信息表
@Table(name = "bio_cage")
public class Cage implements Serializable {
	
	@Id
    @KeySql(genId = UUIdGenId.class)
    private String id;
    //箱体编号
	@Column(name="cage_no")
    private String cageno;
    //对应医院ID或操作人员ID
	@Column(name="hospital_id")
    private String hospitalid;
    //电量
    private String battery;
    //空闲状态 0：空闲 1：已绑定订单 2：在途 3:故障
    private String state;
    //备注
    private String remarks;
    //创建人
    @Column(name="created_by")
    private String createdBy;
    //创建时间
    @Column(name="created_at")
    private Date createdAt;
    //所属市
    private String city;
    //所属区
    private String area;
    //所属医院
    @Column(name="user_hospital")
    private String userHospital;
    //时间间隔
    @Column(name="time_interval")
    private String timeInterval;
    //备用
    private String spare5;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCageno() {
        return cageno;
    }

    public void setCageno(String cageno) {
        this.cageno = cageno == null ? null : cageno.trim();
    }

    public String getHospitalid() {
        return hospitalid;
    }

    public void setHospitalid(String hospitalid) {
        this.hospitalid = hospitalid == null ? null : hospitalid.trim();
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery == null ? null : battery.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getUserHospital() {
        return userHospital;
    }

    public void setUserHospital(String userHospital) {
        this.userHospital = userHospital == null ? null : userHospital.trim();
    }

    public String getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(String timeInterval) {
        this.timeInterval = timeInterval == null ? null : timeInterval.trim();
    }

    public String getSpare5() {
        return spare5;
    }

    public void setSpare5(String spare5) {
        this.spare5 = spare5 == null ? null : spare5.trim();
    }
}