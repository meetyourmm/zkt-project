package com.zkt.project.biology.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zkt.common.core.constant.UUIdGenId;

import tk.mybatis.mapper.annotation.KeySql;

//登入表
@Table(name = "bio_login")
public class Login implements Serializable {
	
	@Id
    @KeySql(genId = UUIdGenId.class)
    private String id;
    //用户名
	@Column(name="user_name")
    private String userName;
    //密码
    private String password;
    //用户类型	【总公司：1 市监管部门：2 区监管部门：3 医院：4 医院操作员：5 运输方：6 运输方操作员：7 总公司员工：8 医院白卡账号：9】
    @Column(name="user_type")
    private String userType;
    //关联用户所属的上级ID
    @Column(name="role_id")
    private String roleId;
    //备注
    private String remarks;
    //创建人
    @Column(name="created_by")
    private String createdBy;
    //创建时间
    @Column(name="created_at")
    private Date createdAt;
    //修改人
    @Column(name="updated_by")
    private String updatedBy;
    //修改时间
    @Column(name="updated_at")
    private Date updatedAt;
    //姓名
    @Column(name="user_operator")
    private String userOperator;
    //电话
    @Column(name="user_tel")
    private String userTel;
    //科室/单位
    @Column(name="user_office")
    private String userOffice;
    //所属医院
    @Column(name="user_hospital")
    private String userHospital;
    //账号冻结状态	0为正常，1为冻结，2删除	冻结-删除后不允许进行任何操作
    @Column(name="user_islock")
    private String userIslock;
    //所属市
    private String city;
    //所属区
    private String area;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
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

    public String getUserOperator() {
        return userOperator;
    }

    public void setUserOperator(String userOperator) {
        this.userOperator = userOperator == null ? null : userOperator.trim();
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel == null ? null : userTel.trim();
    }

    public String getUserOffice() {
        return userOffice;
    }

    public void setUserOffice(String userOffice) {
        this.userOffice = userOffice == null ? null : userOffice.trim();
    }

    public String getUserHospital() {
        return userHospital;
    }

    public void setUserHospital(String userHospital) {
        this.userHospital = userHospital == null ? null : userHospital.trim();
    }

    public String getUserIslock() {
        return userIslock;
    }

    public void setUserIslock(String userIslock) {
        this.userIslock = userIslock == null ? null : userIslock.trim();
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