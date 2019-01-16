/*
 * Copyright(C), 2015-2019, Beifeng
 * FileName: JobInfoMapper
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
package com.zkt.project.admin.mapper;

import com.zkt.project.admin.entity.JobInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.code.ORDER;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 职位mapper
 *
 * @author liwei
 * @create 2019/1/16 0016
 */
public interface JobInfoMapper extends Mapper<JobInfo> {

    @Select("select b.* from wx_job_hot a LEFT JOIN wx_job_info b on a.job_id = b.id")
    List<JobInfo> getHotJobList();

    @Select("select * from wx_job_recommend a LEFT JOIN wx_job_info b on a.job_id = b.id where a.user_id = #{userId}  ORDER BY a.score desc")
    List<JobInfo> getRecommendJobList(@Param("userId") String userId);

    @Select("select * from wx_job_relate a LEFT JOIN wx_job_info b on a.job_id = b.id where a.user_id = #{userId}  ORDER BY a.scan_time desc")
    List<JobInfo> getJobHistoryList(@Param("userId")String userId);

    List<JobInfo> getCompanyJobList(@Param("companyId")String companyId, @Param("key")String key);
}
