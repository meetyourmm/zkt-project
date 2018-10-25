package com.zkt.project.biology.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zkt.common.core.constant.UUIdGenId;

import tk.mybatis.mapper.annotation.KeySql;

//微信箱体操作记录
@Table(name = "bio_operatrecord")
public class Operatrecord implements Serializable {
	
	@Id
    @KeySql(genId = UUIdGenId.class)
    private String id;
    //箱体编号
	@Column(name="cage_no")
    private String cageno;
    //订单号
	@Column(name="order_no")
    private String orderNo;
    //操作类型	创建订单：0 发货开箱：1 发出扫码：2 收货开箱：3 异常记录：4
	@Column(name="operat_classify")
    private String operatclassify;
    //备注描述
    private String discribtion;
    //新建人
    @Column(name="created_by")
    private String createdBy;
    //新建时间
    @Column(name="created_at")
    private Date createdAt;
    //发货开箱人
    @Column(name="send_by")
    private String sendBy;
    //发出扫码人
    @Column(name="start_by")
    private String startBy;
    //收货开箱人
    @Column(name="receipt_by")
    private String receiptBy;
    //异常记录人
    @Column(name="submit_by")
    private String submitBy;
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

    public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo == null ? null : orderNo.trim();
	}

    public String getOperatclassify() {
        return operatclassify;
    }

    public void setOperatclassify(String operatclassify) {
        this.operatclassify = operatclassify == null ? null : operatclassify.trim();
    }

    public String getDiscribtion() {
        return discribtion;
    }

    public void setDiscribtion(String discribtion) {
        this.discribtion = discribtion == null ? null : discribtion.trim();
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

    public String getSendBy() {
        return sendBy;
    }

    public void setSendBy(String sendBy) {
        this.sendBy = sendBy == null ? null : sendBy.trim();
    }

    public String getStartBy() {
        return startBy;
    }

    public void setStartBy(String startBy) {
        this.startBy = startBy == null ? null : startBy.trim();
    }

    public String getReceiptBy() {
        return receiptBy;
    }

    public void setReceiptBy(String receiptBy) {
        this.receiptBy = receiptBy == null ? null : receiptBy.trim();
    }

    public String getSubmitBy() {
        return submitBy;
    }

    public void setSubmitBy(String submitBy) {
        this.submitBy = submitBy == null ? null : submitBy.trim();
    }

    public String getSpare5() {
        return spare5;
    }

    public void setSpare5(String spare5) {
        this.spare5 = spare5 == null ? null : spare5.trim();
    }
}