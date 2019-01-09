/*
 * Copyright(C), 2015-2019, Beifeng
 * FileName: DictionaryApiController
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
import com.zkt.common.core.constant.CommonConstant;
import com.zkt.common.web.response.ApiResponse;
import com.zkt.project.admin.service.DictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 字典接口
 *
 * @author liwei
 * @create 2019/1/9 0009
 */
@RestController
@Api(value="DictionaryApiController|字典相关接口")
@RequestMapping("/api/dict")
public class DictionaryApiController {

    @Autowired
    private DictionaryService dictionaryService;

    @PostMapping(value = "/getRootDict")
    @ApiOperation(value="获取最上层字典值",tags = "获取最上层字典值")
    public ApiResponse getRootDict(@ApiParam(name="name",value="名称") String name){
        return new ApiResponse(dictionaryService.getDictListByParentId(CommonConstant.ROOT_NODE,name));
    }

    @PostMapping(value = "/getDictChildren")
    @ApiOperation(value="获取字典所有子集",tags = "获取字典所有子集")
    public ApiResponse getDictChildren(@ApiParam(name="parentId",value="父节点id",required = true) String parentId,
                                       @ApiParam(name="ctype",value="所属类型编码",required = true) String ctype){
        return new ApiResponse(dictionaryService.getDictChildren(parentId,ctype));
    }

}
