/*
 * Copyright(C), 2015-2019, Beifeng
 * FileName: Resume
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * Email Address: liwei@ibeifeng.com
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zkt.project.admin.entity;

import com.zkt.common.core.constant.UUIdGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * 简历表
 *
 * @author liwei
 * @create 2019/1/15 0015
 */
@Table(name = "wx_resume")
public class Resume {
    @Id
    @KeySql(genId = UUIdGenId.class)
    @Column(name="id")
    private String id;

    @Column(name = "real_name")
    private String realName;

    @Column(name = "head_img")
    private String headImg;
    private String gender;
    private Date birthday;
    private Integer age;
    private String experience;
    @Column(name = "experience_dic")
    private String experienceDic;
    private String email;
    private String mobile;
    private String education;
    @Column(name = "education_dic")
    private String educationDic;
    private String major;
    private String marita;
    @Column(name = "marita_dic")
    private String maritaDic;
    @Column(name = "english_level")
    private String englishLevel;

    private String domicile;
    @Column(name = "join_school_date")
    private Date joinSchoolDate;
    @Column(name = "out_school_date")
    private Date outSchoolDate;
    @Column(name = "now_state")
    private String nowState;
    @Column(name = "now_state_dic")
    private String nowStateDic;

    @Column(name = "will_job")
    private String willJob;
    @Column(name = "will_job_dic")
    private String willJobDic;
    @Column(name = "will_city")
    private String willCity;
    @Column(name = "will_city_dic")
    private String willCityDic;
    @Column(name = "will_salary")
    private String willSalary;
    @Column(name = "will_salary_dic")
    private String willSalaryDic;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "update_user")
    private String updateUser;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getExperienceDic() {
        return experienceDic;
    }

    public void setExperienceDic(String experienceDic) {
        this.experienceDic = experienceDic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getEducationDic() {
        return educationDic;
    }

    public void setEducationDic(String educationDic) {
        this.educationDic = educationDic;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMarita() {
        return marita;
    }

    public void setMarita(String marita) {
        this.marita = marita;
    }

    public String getMaritaDic() {
        return maritaDic;
    }

    public void setMaritaDic(String maritaDic) {
        this.maritaDic = maritaDic;
    }

    public String getEnglishLevel() {
        return englishLevel;
    }

    public void setEnglishLevel(String englishLevel) {
        this.englishLevel = englishLevel;
    }

    public String getDomicile() {
        return domicile;
    }

    public void setDomicile(String domicile) {
        this.domicile = domicile;
    }

    public Date getJoinSchoolDate() {
        return joinSchoolDate;
    }

    public void setJoinSchoolDate(Date joinSchoolDate) {
        this.joinSchoolDate = joinSchoolDate;
    }

    public Date getOutSchoolDate() {
        return outSchoolDate;
    }

    public void setOutSchoolDate(Date outSchoolDate) {
        this.outSchoolDate = outSchoolDate;
    }

    public String getNowState() {
        return nowState;
    }

    public void setNowState(String nowState) {
        this.nowState = nowState;
    }

    public String getNowStateDic() {
        return nowStateDic;
    }

    public void setNowStateDic(String nowStateDic) {
        this.nowStateDic = nowStateDic;
    }

    public String getWillJob() {
        return willJob;
    }

    public void setWillJob(String willJob) {
        this.willJob = willJob;
    }

    public String getWillJobDic() {
        return willJobDic;
    }

    public void setWillJobDic(String willJobDic) {
        this.willJobDic = willJobDic;
    }

    public String getWillCity() {
        return willCity;
    }

    public void setWillCity(String willCity) {
        this.willCity = willCity;
    }

    public String getWillCityDic() {
        return willCityDic;
    }

    public void setWillCityDic(String willCityDic) {
        this.willCityDic = willCityDic;
    }

    public String getWillSalary() {
        return willSalary;
    }

    public void setWillSalary(String willSalary) {
        this.willSalary = willSalary;
    }

    public String getWillSalaryDic() {
        return willSalaryDic;
    }

    public void setWillSalaryDic(String willSalaryDic) {
        this.willSalaryDic = willSalaryDic;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}
