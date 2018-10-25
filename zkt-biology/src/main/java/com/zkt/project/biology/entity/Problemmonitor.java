package com.zkt.project.biology.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zkt.common.core.constant.UUIdGenId;

import tk.mybatis.mapper.annotation.KeySql;

//运输时异常信息记录
@Table(name = "bio_problemmonitor")
public class Problemmonitor implements Serializable {

	@Id
    @KeySql(genId = UUIdGenId.class)
	private String id;
	// 订单号
	@Column(name="order_no")
	private String orderNo;
	// 异常描述
	private String discribtion;
	// 处理状态 0：处理中 1：处理完毕
	private String status;
	// 异常上报时间
	@Column(name="created_at")
	private Date createdAt;
	// 发送方
	@Column(name="sender_by")
	private String senderBy;
	// 发送方反馈时间
	@Column(name="sender_at")
	private Date senderAt;
	// 发送方异常反馈描述
	@Column(name="sender_discribtion")
	private String senderDiscribtion;
	// 运输方
	@Column(name="transport_by")
	private String transportBy;
	// 运输方反馈时间
	@Column(name="transport_at")
	private Date transportAt;
	// 运输方异常反馈描述
	@Column(name="transport_discribtion")
	private String transportDiscribtion;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo == null ? null : orderNo.trim();
	}

	public String getDiscribtion() {
		return discribtion;
	}

	public void setDiscribtion(String discribtion) {
		this.discribtion = discribtion == null ? null : discribtion.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getSenderBy() {
		return senderBy;
	}

	public void setSenderBy(String senderBy) {
		this.senderBy = senderBy == null ? null : senderBy.trim();
	}

	public Date getSenderAt() {
		return senderAt;
	}

	public void setSenderAt(Date senderAt) {
		this.senderAt = senderAt;
	}

	public String getSenderDiscribtion() {
		return senderDiscribtion;
	}

	public void setSenderDiscribtion(String senderDiscribtion) {
		this.senderDiscribtion = senderDiscribtion == null ? null : senderDiscribtion.trim();
	}

	public String getTransportBy() {
		return transportBy;
	}

	public void setTransportBy(String transportBy) {
		this.transportBy = transportBy == null ? null : transportBy.trim();
	}

	public Date getTransportAt() {
		return transportAt;
	}

	public void setTransportAt(Date transportAt) {
		this.transportAt = transportAt;
	}

	public String getTransportDiscribtion() {
		return transportDiscribtion;
	}

	public void setTransportDiscribtion(String transportDiscribtion) {
		this.transportDiscribtion = transportDiscribtion == null ? null : transportDiscribtion.trim();
	}

}