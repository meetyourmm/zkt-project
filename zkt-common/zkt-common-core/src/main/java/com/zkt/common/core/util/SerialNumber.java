package com.zkt.common.core.util;

import java.io.Serializable;

//序列号JAVA BEAN
public class SerialNumber implements Serializable {

	private static final long serialVersionUID = -1872555455229517480L;
	
	//主键
	private String c_id;
	//序号类型
	private String c_sntype;
	//序号
	private Integer c_sn;
	//前缀
	private String c_prefix;
	//完整序列号
	private String c_lastsn;
	//序号
	private Integer c_length;
	// 序列模式
	private String c_pattern;
	// 库索引
	private Integer c_dbindex;
	//备注
	private String c_remarks;
	
	public String getC_id() {
		return c_id;
	}
	public void setC_id(String c_id) {
		this.c_id = c_id;
	}
	public String getC_sntype() {
		return c_sntype;
	}
	public void setC_sntype(String c_sntype) {
		this.c_sntype = c_sntype;
	}
	public Integer getC_sn() {
		return c_sn;
	}
	public void setC_sn(Integer c_sn) {
		this.c_sn = c_sn;
	}
	public String getC_prefix() {
		return c_prefix;
	}
	public void setC_prefix(String c_prefix) {
		this.c_prefix = c_prefix;
	}
	public String getC_lastsn() {
		return c_lastsn;
	}
	public void setC_lastsn(String c_lastsn) {
		this.c_lastsn = c_lastsn;
	}
	public Integer getC_length() {
		return c_length;
	}
	public void setC_length(Integer c_length) {
		this.c_length = c_length;
	}
	public String getC_pattern() {
		return c_pattern;
	}
	public void setC_pattern(String c_pattern) {
		this.c_pattern = c_pattern;
	}
	public Integer getC_dbindex() {
		return c_dbindex;
	}
	public void setC_dbindex(Integer c_dbindex) {
		this.c_dbindex = c_dbindex;
	}
	public String getC_remarks() {
		return c_remarks;
	}
	public void setC_remarks(String c_remarks) {
		this.c_remarks = c_remarks;
	}
	
}
