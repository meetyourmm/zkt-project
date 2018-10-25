package com.zkt.project.biology.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zkt.common.core.constant.UUIdGenId;

import tk.mybatis.mapper.annotation.KeySql;

@Table(name = "bio_system_parameter")
public class SystemParameter implements Serializable {
	
	@Id
    @KeySql(genId = UUIdGenId.class)
    private String id;
    //警报时间间隔
	@Column(name="alarm_interval")
    private String alarmInterval;
    //医院ID
	@Column(name="hospital_id")
    private String hospitalid;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlarmInterval() {
        return alarmInterval;
    }

    public void setAlarmInterval(String alarmInterval) {
        this.alarmInterval = alarmInterval == null ? null : alarmInterval.trim();
    }
    
    public String getHospitalid() {
        return hospitalid;
    }

    public void setHospitalid(String hospitalid) {
        this.hospitalid = hospitalid == null ? null : hospitalid.trim();
    }
    
}