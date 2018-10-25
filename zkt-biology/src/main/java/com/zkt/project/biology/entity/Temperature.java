package com.zkt.project.biology.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zkt.common.core.constant.UUIdGenId;

import tk.mybatis.mapper.annotation.KeySql;

//过程温度数据表
@Table(name = "bio_temperature")
public class Temperature implements Serializable {
	
	@Id
    @KeySql(genId = UUIdGenId.class)
    private String id;
    //订单ID
	@Column(name="order_id")
    private String orderId;
    //温度数据
    private String temperature;
    //上传时间
    @Column(name="upload_time")
    private Date uploadtime;
    //箱体编号
    @Column(name="cage_no")
    private String cageno;
    
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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature == null ? null : temperature.trim();
    }

    public Date getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(Date uploadtime) {
        this.uploadtime = uploadtime;
    }

    public String getCageno() {
        return cageno;
    }

    public void setCageno(String cageno) {
        this.cageno = cageno == null ? null : cageno.trim();
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