/*
 * Copyright(C), 2015-2019, Beifeng
 * FileName: ApiLogController
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
import com.zkt.common.web.response.ApiResponse;
import com.zkt.project.admin.service.ApiLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 接口日志
 *
 * @author liwei
 * @create 2019/1/8 0008
 */
@RestController
@Api(value="ApiLogController|接口日志相关接口")
@RequestMapping("/api/apiLog")
public class ApiLogController {

    @Autowired
    private ApiLogService apiLogService;

    @PostMapping(value = "/page")
    @ApiOperation(value="获取接口列表",tags = "获取接口列表")
    public ApiResponse getApiLogPage(@ApiParam(name="page",value="起始位置",required=true) Integer page,
                                     @ApiParam(name="limit",value="分页大小",required=true) Integer limit,
                                     @ApiParam(name="name",value="操作账号") String name,
                                     @ApiParam(name="startDate",value="起始时间") String startDate,
                                     @ApiParam(name="endDate",value="结束时间") String endDate){
        PageInfo<Map> list = apiLogService.getLogListPage(page,limit,name,startDate,endDate);
        return new ApiResponse(list);
    }

    @DeleteMapping(value = "/delAll")
    @ApiOperation(value="清除所有日志",tags = "清除所有日志")
    public ApiResponse delAll(){
        apiLogService.removeAll();
        return new ApiResponse();
    }
}
