package com.zkt.project.biology.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zkt.common.core.constant.UUIdGenId;

import tk.mybatis.mapper.annotation.KeySql;

//模板 11
@Table(name = "bio_model")
public class Model implements Serializable {
	
	@Id
    @KeySql(genId = UUIdGenId.class)
    private String id;
    
    //模板名称
    @Column(name="model_no")
    private String modelNo;
    //发送医院ID
    @Column(name="sender_id")
    private String senderid;
    //运输公司ID
    @Column(name="transport_id")
    private String transportid;
    //收货医院ID
    @Column(name="reciver_id")
    private String reciverid;
    //发货医院名称
    private String sender;
    //运输公司名称
    private String transport;
    //收货医院名称
    private String reciver;
    
    //温度上限值
    @Column(name="tlimit_up")
    private String tlimitup;
    //温度下限值
    @Column(name="tlimit_down")
    private String tlimitdown;
    //湿度上限值
    @Column(name="hlimit_up")
    private String hlimitup;
    //湿度下限值
    @Column(name="hlimit_down")
    private String hlimitdown;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSenderid() {
        return senderid;
    }

    public void setSenderid(String senderid) {
        this.senderid = senderid == null ? null : senderid.trim();
    }

    public String getTransportid() {
        return transportid;
    }

    public void setTransportid(String transportid) {
        this.transportid = transportid == null ? null : transportid.trim();
    }

    public String getReciverid() {
        return reciverid;
    }

    public void setReciverid(String reciverid) {
        this.reciverid = reciverid == null ? null : reciverid.trim();
    }

    public String getTlimitup() {
        return tlimitup;
    }

    public void setTlimitup(String tlimitup) {
        this.tlimitup = tlimitup == null ? null : tlimitup.trim();
    }

    public String getTlimitdown() {
        return tlimitdown;
    }

    public void setTlimitdown(String tlimitdown) {
        this.tlimitdown = tlimitdown == null ? null : tlimitdown.trim();
    }

    public String getHlimitup() {
        return hlimitup;
    }

    public void setHlimitup(String hlimitup) {
        this.hlimitup = hlimitup == null ? null : hlimitup.trim();
    }

    public String getHlimitdown() {
        return hlimitdown;
    }

    public void setHlimitdown(String hlimitdown) {
        this.hlimitdown = hlimitdown == null ? null : hlimitdown.trim();
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender == null ? null : sender.trim();
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport == null ? null : transport.trim();
    }

    public String getReciver() {
        return reciver;
    }

    public void setReciver(String reciver) {
        this.reciver = reciver == null ? null : reciver.trim();
    }
    
    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo == null ? null : modelNo.trim();
    }
    
}