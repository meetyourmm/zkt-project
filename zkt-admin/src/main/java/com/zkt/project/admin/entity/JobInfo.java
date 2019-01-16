/*
 * Copyright(C), 2015-2019, Beifeng
 * FileName: JobInfo
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
 * 职位信息表
 *
 * @author liwei
 * @create 2019/1/15 0015
 */
@Table(name = "wx_job_info")
public class JobInfo {
    @Id
    @KeySql(genId = UUIdGenId.class)
    @Column(name="id")
    private String id;

    private String url;
    private String title;
    private String link;
    private String salary;
    @Column(name = "salary_dic")
    private String salaryDic;
    @Column(name = "work_place")
    private String workPlace;
    @Column(name = "public_date")
    private Date publicDate;
    private String nature;
    @Column(name = "nature_dic")
    private String natureDic;
    private String experience;
    @Column(name = "experience_dic")
    private String experienceDic;
    @Column(name = "low_education")
    private String lowEducation;
    @Column(name = "low_education_dic")
    private String lowEducationDic;
    @Column(name = "head_count")
    private String headCount;
    private String category;
    @Column(name = "category_dic")
    private String categoryDic;
    private String description;
    @Column(name = "from_recruit_site")
    private String fromRecruitSite;
    @Column(name = "from_recruit_site_dic")
    private String fromRecruitSiteDic;
    @Column(name = "company_id")
    private String companyId;

    @Transient
    private CompanyInfo company;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getSalaryDic() {
        return salaryDic;
    }

    public void setSalaryDic(String salaryDic) {
        this.salaryDic = salaryDic;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public Date getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(Date publicDate) {
        this.publicDate = publicDate;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getNatureDic() {
        return natureDic;
    }

    public void setNatureDic(String natureDic) {
        this.natureDic = natureDic;
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

    public String getLowEducation() {
        return lowEducation;
    }

    public void setLowEducation(String lowEducation) {
        this.lowEducation = lowEducation;
    }

    public String getLowEducationDic() {
        return lowEducationDic;
    }

    public void setLowEducationDic(String lowEducationDic) {
        this.lowEducationDic = lowEducationDic;
    }

    public String getHeadCount() {
        return headCount;
    }

    public void setHeadCount(String headCount) {
        this.headCount = headCount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryDic() {
        return categoryDic;
    }

    public void setCategoryDic(String categoryDic) {
        this.categoryDic = categoryDic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFromRecruitSite() {
        return fromRecruitSite;
    }

    public void setFromRecruitSite(String fromRecruitSite) {
        this.fromRecruitSite = fromRecruitSite;
    }

    public String getFromRecruitSiteDic() {
        return fromRecruitSiteDic;
    }

    public void setFromRecruitSiteDic(String fromRecruitSiteDic) {
        this.fromRecruitSiteDic = fromRecruitSiteDic;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public CompanyInfo getCompany() {
        return company;
    }

    public void setCompany(CompanyInfo company) {
        this.company = company;
    }
}
