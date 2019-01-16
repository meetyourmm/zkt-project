/*
 * Copyright(C), 2015-2019, Beifeng
 * FileName: JobInfoService
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
package com.zkt.project.admin.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zkt.project.admin.entity.CompanyInfo;
import com.zkt.project.admin.entity.JobInfo;
import com.zkt.project.admin.entity.JobUserRelate;
import com.zkt.project.admin.mapper.CompanyInfoMapper;
import com.zkt.project.admin.mapper.JobInfoMapper;
import com.zkt.project.admin.mapper.JobUserRelateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 职位相关service
 *
 * @author liwei
 * @create 2019/1/16 0016
 */
@Service
public class JobInfoService {
    @Autowired
    private JobInfoMapper jobInfoMapper;
    @Autowired
    private  JobUserRelateMapper jobUserRelateMapper;

    @Autowired
    private CompanyInfoMapper companyInfoMapper;
    public PageInfo<JobInfo> getRecommendJobPageList(int pageStart, int pageSize,String userId){
        PageHelper.startPage(pageStart,pageSize);

        List<JobInfo> list = jobInfoMapper.getRecommendJobList(userId);
        if(list.size()==0){
            list = jobInfoMapper.getHotJobList();
        }
        return new PageInfo<>(list);
    }

    public PageInfo<JobInfo> getJobHistoryPageList(int pageStart, int pageSize, String userId) {
        PageHelper.startPage(pageStart,pageSize);
        List<JobInfo> list = jobInfoMapper.getJobHistoryList(userId);
        return new PageInfo<>(list);
    }

    public void saveJobRelate(String userId, String jobId, String scanResult) {
        JobUserRelate  relate = new JobUserRelate();
        relate.setUserId(userId);
        relate.setJobId(jobId);

        JobUserRelate one = jobUserRelateMapper.selectOne(relate);
        if(one==null){
            relate.setScanResult(scanResult);
            jobUserRelateMapper.insertSelective(relate);
        }else{
            one.setScanResult(scanResult);
            jobUserRelateMapper.updateByPrimaryKeySelective(one);
        }
    }

    public JobInfo getJobInfo(String jobId) {
        JobInfo job = jobInfoMapper.selectByPrimaryKey(jobId);
        CompanyInfo company = companyInfoMapper.selectByPrimaryKey(job.getCompanyId());
        job.setCompany(company);
        return job;
    }

    public PageInfo<JobInfo> getCompanyJobListPage(int pageStart, int pageSize, String companyId, String key) {
        PageHelper.startPage(pageStart,pageSize);
        List<JobInfo> list = jobInfoMapper.getCompanyJobList(companyId,key);
        return new PageInfo<>(list);
    }
}
