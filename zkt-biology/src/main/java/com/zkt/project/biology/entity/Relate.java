package com.zkt.project.biology.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zkt.common.core.constant.UUIdGenId;

import tk.mybatis.mapper.annotation.KeySql;

//发货医院和运输方
@Table(name = "bio_relate")
public class Relate implements Serializable {
	
	@Id
    @KeySql(genId = UUIdGenId.class)
    private String id;
    //医院账号ID
	@Column(name="hospital_id")
    private String hospitalid;
    //运输方账号ID
	@Column(name="transport_id")
    private String transportid;
    //备用
    private String spare1;
    //备用
    private String spare2;
    //备用
    private String spare3;
    //备用
    private String spare4;
    //备用
    private String spare5;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHospitalid() {
        return hospitalid;
    }

    public void setHospitalid(String hospitalid) {
        this.hospitalid = hospitalid == null ? null : hospitalid.trim();
    }

    public String getTransportid() {
        return transportid;
    }

    public void setTransportid(String transportid) {
        this.transportid = transportid == null ? null : transportid.trim();
    }

    public String getSpare1() {
        return spare1;
    }

    public void setSpare1(String spare1) {
        this.spare1 = spare1 == null ? null : spare1.trim();
    }

    public String getSpare2() {
        return spare2;
    }

    public void setSpare2(String spare2) {
        this.spare2 = spare2 == null ? null : spare2.trim();
    }

    public String getSpare3() {
        return spare3;
    }

    public void setSpare3(String spare3) {
        this.spare3 = spare3 == null ? null : spare3.trim();
    }

    public String getSpare4() {
        return spare4;
    }

    public void setSpare4(String spare4) {
        this.spare4 = spare4 == null ? null : spare4.trim();
    }

    public String getSpare5() {
        return spare5;
    }

    public void setSpare5(String spare5) {
        this.spare5 = spare5 == null ? null : spare5.trim();
    }
}