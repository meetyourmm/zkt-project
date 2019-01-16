/*
 * Copyright(C), 2015-2019, Beifeng
 * FileName: CompanyInfo
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
import java.util.Date;

/**
 * 公司表
 *
 * @author liwei
 * @create 2019/1/16 0016
 */
@Table(name = "wx_company_info")
public class CompanyInfo {
    @Id
    @KeySql(genId = UUIdGenId.class)
    @Column(name="id")
    private String id;

    private String name;
    private String tags;
    private String description;
    private String size;
    @Column(name = "size_dic")
    private String sizeDic;
    private String nature;
    @Column(name = "nature_dic")
    private String natureDic;
    private String industry;
    @Column(name = "industry_dic")
    private String industryDic;
    private String site;
    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSizeDic() {
        return sizeDic;
    }

    public void setSizeDic(String sizeDic) {
        this.sizeDic = sizeDic;
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

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getIndustryDic() {
        return industryDic;
    }

    public void setIndustryDic(String industryDic) {
        this.industryDic = industryDic;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
