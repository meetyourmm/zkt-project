package com.zkt.project.biology.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zkt.common.core.constant.UUIdGenId;

import tk.mybatis.mapper.annotation.KeySql;

//订单表	31
@Table(name = "bio_order")
public class Order implements Serializable {

	@Id
    @KeySql(genId = UUIdGenId.class)
	private String id;
	
	//订单号
	@Column(name="order_no")
	private String orderNo;
	//发货医院ID
	@Column(name="sender_id")
	private String senderid;
	//运输方ID
	@Column(name="transport_id")
	private String transportid;
	//收货医院ID
	@Column(name="reciver_id")
	private String reciverid;
	//发货医院
	private String sender;
	//运输方
	private String transport;
	//收货医院
	private String reciver;	
	//新建人员
	@Column(name="created_by")
	private String createdBy;
	//新建时间
	@Column(name="created_at")
	private Date createdAt;
	//修改人
	@Column(name="updated_by")
	private String updatedBy;
	//修改时间
	@Column(name="updated_at")
	private Date updatedAt;
	//装箱人员
	@Column(name="pack_operator")
	private String packOperator;	
	//运输人员
	@Column(name="transport_operator")
	private String transportOperator;
	//接收人员
	@Column(name="receive_operator")
	private String receiveOperator;
	
	//发出时间
	@Column(name="send_time")
	private Date sendtime;
	//预计到达时间
	private Date arrivetime;
	//接收时间
	@Column(name="receive_time")
	private Date receivetime;
	//箱体编号
	@Column(name="cage_no")
	private String cageno;
	//车牌号码
	@Column(name="car_no")
	private String carNo;
	//下单分类 PC端录入：1 微信录入：2
	private String classify;
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
	//备注
	private String remarks;
	//样本总数
	private String num;
	//样本类型 A和B两种情况
	private String sampleclassify;
	
	//订单状态 1：新建 2：已装箱 3：运输中 4：已完成 5：待仲裁
	@Column(name="order_status")
	private String orderStatus;
	//异常 0：是异常件 1：不是异常件
	@Column(name="is_problem")
	private String isProblem;
	//异常描述
	@Column(name="problem_cause")
	private String problemCause;
	//审核状态 0：待处理 1：审核中 2：处理完毕
	@Column(name="is_finish")
	private String isFinish;
	
	//所属市
    private String city;
    //所属区
    private String area;
    
    //预计时间内未到达报警
    private String sign;   
    //温度上限异常
    private String sign1;    
    //温度下限异常
    private String sign2;    
    //湿度上限异常
    private String sign3;   
    //湿度下限异常
    private String sign4;    
    //箱体非正常打开
    private String sign5;
    //电池电量过低
    private String sign6;
    
    //报警时间(温度上限异常)
    private Date alarmtime;
    //报警时间(温度下限异常)
    private Date alarmtime1;
    //报警时间(湿度上限异常)
    private Date alarmtime2;
    //报警时间(湿度下限异常)
    private Date alarmtime3;
    //报警时间(电池电量过低)
    private Date alarmtime4;
    
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

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus == null ? null : orderStatus.trim();
	}

	public String getIsProblem() {
		return isProblem;
	}

	public void setIsProblem(String isProblem) {
		this.isProblem = isProblem == null ? null : isProblem.trim();
	}

	public String getProblemCause() {
		return problemCause;
	}

	public void setProblemCause(String problemCause) {
		this.problemCause = problemCause == null ? null : problemCause.trim();
	}

	public String getIsFinish() {
		return isFinish;
	}

	public void setIsFinish(String isFinish) {
		this.isFinish = isFinish == null ? null : isFinish.trim();
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

	public String getPackOperator() {
		return packOperator;
	}

	public void setPackOperator(String packOperator) {
		this.packOperator = packOperator == null ? null : packOperator.trim();
	}

	public String getTransportOperator() {
		return transportOperator;
	}

	public void setTransportOperator(String transportOperator) {
		this.transportOperator = transportOperator == null ? null : transportOperator.trim();
	}

	public String getReceiveOperator() {
		return receiveOperator;
	}

	public void setReceiveOperator(String receiveOperator) {
		this.receiveOperator = receiveOperator == null ? null : receiveOperator.trim();
	}

	public Date getSendtime() {
		return sendtime;
	}

	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}

	public Date getReceivetime() {
		return receivetime;
	}

	public void setReceivetime(Date receivetime) {
		this.receivetime = receivetime;
	}

	public String getCageNo() {
		return cageno;
	}

	public void setCageNo(String cageno) {
		this.cageno = cageno == null ? null : cageno.trim();
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo == null ? null : carNo.trim();
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify == null ? null : classify.trim();
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

	public Date getArrivetime() {
		return arrivetime;
	}

	public void setArrivetime(Date arrivetime) {
		this.arrivetime = arrivetime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks == null ? null : remarks.trim();
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num == null ? null : num.trim();
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

	public String getSampleclassify() {
		return sampleclassify;
	}

	public void setSampleclassify(String sampleclassify) {
		this.sampleclassify = sampleclassify == null ? null : sampleclassify.trim();
	}
	
	public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }
    
    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }
    
    public String getSign1() {
        return sign1;
    }

    public void setSign1(String sign1) {
        this.sign1 = sign1 == null ? null : sign1.trim();
    }
    
    public String getSign2() {
        return sign2;
    }

    public void setSign2(String sign2) {
        this.sign2 = sign2 == null ? null : sign2.trim();
    }
    
    public String getSign3() {
        return sign3;
    }

    public void setSign3(String sign3) {
        this.sign3 = sign3 == null ? null : sign3.trim();
    }
    
    public String getSign4() {
        return sign4;
    }

    public void setSign4(String sign4) {
        this.sign4 = sign4 == null ? null : sign4.trim();
    }
    
    public String getSign5() {
        return sign5;
    }

    public void setSign5(String sign5) {
        this.sign5 = sign5 == null ? null : sign5.trim();
    }
    
    public String getSign6() {
        return sign6;
    }

    public void setSign6(String sign6) {
        this.sign6 = sign6 == null ? null : sign6.trim();
    }

    public Date getAlarmTime() {
		return alarmtime;
	}

	public void setAlarmTime(Date alarmtime) {
		this.alarmtime = alarmtime;
	}
	
	public Date getAlarmTime1() {
		return alarmtime1;
	}

	public void setAlarmTime1(Date alarmtime1) {
		this.alarmtime1 = alarmtime1;
	}
	
	public Date getAlarmTime2() {
		return alarmtime2;
	}

	public void setAlarmTime2(Date alarmtime2) {
		this.alarmtime2 = alarmtime2;
	}
	
	public Date getAlarmTime3() {
		return alarmtime3;
	}

	public void setAlarmTime3(Date alarmtime3) {
		this.alarmtime3 = alarmtime3;
	}
	
	public Date getAlarmTime4() {
		return alarmtime4;
	}

	public void setAlarmTime4(Date alarmtime4) {
		this.alarmtime4 = alarmtime4;
	}
	
}