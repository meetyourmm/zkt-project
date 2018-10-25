package com.zkt.project.biology.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zkt.common.core.constant.UUIdGenId;

import tk.mybatis.mapper.annotation.KeySql;

//异常信息监控审核
@Table(name = "bio_problemrecord")
public class Problemrecord implements Serializable {

	@Id
    @KeySql(genId = UUIdGenId.class)
	private String id;
	// 订单号
	@Column(name="order_no")
	private String orderNo;
	// 发送方异常反馈描述
	@Column(name="sender_discribtion")
	private String senderDiscribtion;
	// 运输方异常反馈描述
	@Column(name="transport_discribtion")
	private String transportDiscribtion;
	// 发送方
	@Column(name="sender_by")
	private String senderBy;
	// 发送方反馈时间
	@Column(name="sender_at")
	private Date senderAt;
	// 运输方
	@Column(name="transport_by")
	private String transportBy;
	// 运输方反馈时间
	@Column(name="transport_at")
	private Date transportAt;
	// 区级审核人
	@Column(name="updated_by")
	private String updatedBy;
	// 区级审核时间
	@Column(name="updated_at")
	private Date updatedAt;
	// 区级审核意见
	private String comments;
	// 市级审核人
	@Column(name="updated_byshi")
	private String updatedByShi;
	// 市级审核时间
	@Column(name="updated_atshi")
	private Date updatedAtShi;
	// 市级审核意见
	@Column(name="comments_shi")
	private String commentsShi;
	// 备用
	private String spare5;

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

	public String getSenderDiscribtion() {
		return senderDiscribtion;
	}

	public void setSenderDiscribtion(String senderDiscribtion) {
		this.senderDiscribtion = senderDiscribtion == null ? null : senderDiscribtion.trim();
	}
	
	public String getTransportDiscribtion() {
		return transportDiscribtion;
	}

	public void setTransportDiscribtion(String transportDiscribtion) {
		this.transportDiscribtion = transportDiscribtion == null ? null : transportDiscribtion.trim();
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

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy == null ? null : updatedBy.trim();
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments == null ? null : comments.trim();
	}

	public String getUpdatedByShi() {
		return updatedByShi;
	}

	public void setUpdatedByShi(String updatedByShi) {
		this.updatedByShi = updatedByShi == null ? null : updatedByShi.trim();
	}

	public Date getUpdatedAtShi() {
		return updatedAtShi;
	}

	public void setUpdatedAtShi(Date updatedAtShi) {
		this.updatedAtShi = updatedAtShi;
	}

	public String getCommentsShi() {
		return commentsShi;
	}

	public void setCommentsShi(String commentsShi) {
		this.commentsShi = commentsShi == null ? null : commentsShi.trim();
	}

	public String getSpare5() {
		return spare5;
	}

	public void setSpare5(String spare5) {
		this.spare5 = spare5 == null ? null : spare5.trim();
	}
}