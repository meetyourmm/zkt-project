/*
 * Copyright(C), 2015-2019, Beifeng
 * FileName: JobInfoController
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
package com.zkt.project.admin.rest;

import com.github.pagehelper.PageInfo;
import com.zkt.common.core.context.UserContextHandler;
import com.zkt.common.web.response.ApiResponse;
import com.zkt.project.admin.entity.JobInfo;
import com.zkt.project.admin.service.JobInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 职位相关控制器
 *
 * @author liwei
 * @create 2019/1/16 0016
 */
@RestController
@Api(value="JobInfoController|职位相关接口")
@RequestMapping("/api/job")
public class JobInfoController {

    @Autowired
    private JobInfoService jobInfoService;

    @PostMapping(value = "/getRecommendJobPageList")
    @ApiOperation(value="获取用户推荐职位",tags = "获取用户推荐职位")
    public ApiResponse getRecommendJobPageList(@ApiParam(name="page",value="起始位置",required=true) Integer page,
                                               @ApiParam(name="limit",value="分页大小",required=true) Integer limit){
        String userId = UserContextHandler.getUserID();
        PageInfo<JobInfo> list =jobInfoService.getRecommendJobPageList(page,limit,userId);
        return new ApiResponse(list);
    }

    @PostMapping(value = "/getJobHistoryPageList")
    @ApiOperation(value="获取用户历史浏览职位",tags = "获取用户历史浏览职位")
    public ApiResponse getJobHistoryPageList(@ApiParam(name="page",value="起始位置",required=true) Integer page,
                                               @ApiParam(name="limit",value="分页大小",required=true) Integer limit){
        String userId = UserContextHandler.getUserID();
        PageInfo<JobInfo> list =jobInfoService.getJobHistoryPageList(page,limit,userId);
        return new ApiResponse(list);
    }

    @PostMapping(value = "/saveJobRelate")
    @ApiOperation(value="用户对职位操作记录",tags = "用户对职位操作记录")
    public ApiResponse saveJobRelate(@ApiParam(name="jobId",value="职位id",required=true) String jobId,
                                             @ApiParam(name="scanResult",value="操作结果",required=true) String scanResult){
        String userId = UserContextHandler.getUserID();
        jobInfoService.saveJobRelate(userId,jobId,scanResult);
        return new ApiResponse();
    }

    @PostMapping(value = "/getJobInfo")
    @ApiOperation(value="获取职位信息",tags = "获取职位信息")
    public ApiResponse getJobInfo(@ApiParam(name="jobId",value="职位id",required=true) String jobId){
        return new ApiResponse(jobInfoService.getJobInfo(jobId));
    }

    @PostMapping(value = "/getCompanyJobListPage")
    @ApiOperation(value="分页获取企业职位列表",tags = "分页获取企业职位列表")
    public ApiResponse getCompanyJobListPage(@ApiParam(name="page",value="起始位置",required=true) Integer page,
                                             @ApiParam(name="limit",value="分页大小",required=true) Integer limit,
                                             @ApiParam(name="companyId",value="企业id",required=true) String companyId,
                                             @ApiParam(name="key",value="职位关键字") String key){
        PageInfo<JobInfo> list =jobInfoService.getCompanyJobListPage(page,limit,companyId,key);
        return new ApiResponse(list);
    }
}
