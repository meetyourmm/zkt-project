package com.zkt.project.biology.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zkt.common.core.constant.UUIdGenId;

import tk.mybatis.mapper.annotation.KeySql;

@Table(name = "bio_running")
public class Running implements Serializable {
	
	@Id
    @KeySql(genId = UUIdGenId.class)
    private String id;
    //订单编号
	@Column(name="order_no")
    private String orderno;
    // 1：新建 2：已装箱 3：运输中 4：已完成 5：待仲裁
    private String state;
    //操作人员
    private String operator;
    //操作时间
    private Date time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}