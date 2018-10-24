/*
 * Copyright(C), 2015-2018, Beifeng
 * FileName: User
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

import com.zkt.common.core.constant.EnumUtil;
import com.zkt.common.core.constant.UUIdGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体
 *
 * @author liwei
 * @create 2018/10/11 0011
 */
@Table(name="sys_user")
public class SysUser implements Serializable {

    @Id
    @KeySql(genId = UUIdGenId.class)
    private String id;//用户编号
    @Column(name="user_name")
    private String userName;//用户登陆账号
    private String password;//用户登陆密码
    private String email;
    private String mobile;//用户手机号
    private String telphone;//用户固话
    private String status = EnumUtil.DataStatus.ENABLE.getStatus();//状态(1 启用 ,2 禁用 , 3 删除)
    @Column(name="user_type")
    private String userType ;//用户类型(1后台用户, 2 前端用户)
    @Column(name="real_name")
    private String realName;//真实姓名
    @Column(name="share_code")
    private String shareCode;//邀请码
    private Integer age;//年龄
    private Date birthday;//生日
    private String gender;//性别字典
    @Column(name="nick_name")
    private String nickName;//昵称
    private String qq;//qq号码
    @Column(name="wei_chat")
    private String weiChat;//微信号码
    @Column(name="wei_bo")
    private String weiBo;//微博号码
    private String avatar;//头像图片
    @Column(name="id_num")
    private String idNum;//身份证号码

    private String country;//现居住国家
    private String province;//现住地省
    private String city;//现住地城市
    private String area;//现住地地区
    private String address;//居住详细地址

    @Column(name="we_chat_open_id")
    private String weChatOpenId;//微信端唯一标识码
    @Column(name="qq_open_id")
    private String qqOpenId;//qq唯一标识码
    @Column(name="wei_bo_open_id")
    private String weiBoOpenId;//微博唯标识码
    private String job;//职位信息
    private String company;//公司名称
    private String industry;//industry

    @Column(name="w_country")
    private String wCountry;//微信带过来的国家
    @Column(name="w_province")
    private String wProvince;//微信带过来的省
    @Column(name="w_city")
    private String wCity;//微信带过来的市
    @Column(name="w_area")
    private String wArea;//微信带过来的区
    @Column(name="uni_id")
    private String uniId;//微信的uniid
    @Column(name="is_com")
    private String isCom;//是否企业用户
    @Column(name="is_admin")
    private String isAdmin ;//超级管理员1不是 2是
    @Column(name="create_time")
    private Date createTime;//创建时间
    @Column(name="create_user")
    private Date createUser;//创建人
    @Column(name="update_time")
    private Date updateTime;//最后一次时间
    @Column(name="update_user")
    private Date updateUser;//最后一次更新人

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
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getShareCode() {
        return shareCode;
    }

    public void setShareCode(String shareCode) {
        this.shareCode = shareCode;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWeiChat() {
        return weiChat;
    }

    public void setWeiChat(String weiChat) {
        this.weiChat = weiChat;
    }

    public String getWeiBo() {
        return weiBo;
    }

    public void setWeiBo(String weiBo) {
        this.weiBo = weiBo;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWeChatOpenId() {
        return weChatOpenId;
    }

    public void setWeChatOpenId(String weChatOpenId) {
        this.weChatOpenId = weChatOpenId;
    }

    public String getQqOpenId() {
        return qqOpenId;
    }

    public void setQqOpenId(String qqOpenId) {
        this.qqOpenId = qqOpenId;
    }

    public String getWeiBoOpenId() {
        return weiBoOpenId;
    }

    public void setWeiBoOpenId(String weiBoOpenId) {
        this.weiBoOpenId = weiBoOpenId;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getwCountry() {
        return wCountry;
    }

    public void setwCountry(String wCountry) {
        this.wCountry = wCountry;
    }

    public String getwProvince() {
        return wProvince;
    }

    public void setwProvince(String wProvince) {
        this.wProvince = wProvince;
    }

    public String getwCity() {
        return wCity;
    }

    public void setwCity(String wCity) {
        this.wCity = wCity;
    }

    public String getwArea() {
        return wArea;
    }

    public void setwArea(String wArea) {
        this.wArea = wArea;
    }

    public String getUniId() {
        return uniId;
    }

    public void setUniId(String uniId) {
        this.uniId = uniId;
    }

    public String getIsCom() {
        return isCom;
    }

    public void setIsCom(String isCom) {
        this.isCom = isCom;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Date createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Date updateUser) {
        this.updateUser = updateUser;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }
}
