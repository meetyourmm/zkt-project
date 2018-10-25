package com.zkt.project.biology.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zkt.common.core.constant.UUIdGenId;

import tk.mybatis.mapper.annotation.KeySql;

//样本信息表
@Table(name = "bio_sample")
public class Sample implements Serializable {

	@Id
    @KeySql(genId = UUIdGenId.class)
	private String id;
	// 订单编号
	@Column(name="order_no")
	private String orderNo;
	// 样本编号
	@Column(name="sample_number")
	private String sampleNumber;
	// 缓存excel
	private String excel;
	//标示
	private String sign;
	//样本类型
	private String spare4;
	//样本数量
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

	public String getSampleNumber() {
		return sampleNumber;
	}

	public void setSampleNumber(String sampleNumber) {
		this.sampleNumber = sampleNumber == null ? null : sampleNumber.trim();
	}

	public String getExcel() {
		return excel;
	}

	public void setExcel(String excel) {
		this.excel = excel == null ? null : excel.trim();
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign == null ? null : sign.trim();
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