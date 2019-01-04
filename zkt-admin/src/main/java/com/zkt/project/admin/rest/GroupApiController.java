/*
 * Copyright(C), 2015-2018, Beifeng
 * FileName: GroupApiController
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
import com.zkt.project.admin.entity.SysGroup;
import com.zkt.project.admin.entity.SysMenu;
import com.zkt.project.admin.service.GroupService;
import com.zkt.project.admin.service.MenuService;
import com.zkt.project.admin.vo.MenuTreeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 部门与组controller
 *
 * @author liwei
 * @create 2018/12/20 0020
 */
@RestController
@Api(value="GroupApiController|部门或组接口")
@RequestMapping("/api/group")
public class GroupApiController {

    @Autowired
    private GroupService groupService;


    /**
     * 根据查询获取根节点树
     * @return
     */
    @GetMapping(value = "/tree")
    @ApiOperation(value="获取部门或者权限树",tags = "获取部门或者权限树")
    public ApiResponse getGroupTreeByTitle(@ApiParam(name="title",value="节点名称") String title,@ApiParam(name="type",value="type：1角色2部门") String type){
        return new ApiResponse( MenuTreeUtil.getGroupTree(groupService.getDeaprtTreeByTitle(title,type), CommonConstant.ROOT_NODE));
    }

    @PostMapping(value = "/addObj")
    @ApiOperation(value="添加部门或者权限组",tags = "添加部门或者权限组")
    public ApiResponse addGroup(@RequestBody @ApiParam(name="菜单对象",value="传入json格式",required=true) SysGroup group){
        groupService.addGroup(group);
        return new ApiResponse();
    }

    @PostMapping(value = "/getObj")
    @ApiOperation(value="获取部门或者权限组",tags = "获取部门或者权限组")
    public ApiResponse getMenuById(@ApiParam(name="id",value="部门或者组id",required=true) String id){
        return new ApiResponse(groupService.getGroupById(id));
    }

    @PostMapping(value = "/putObj")
    @ApiOperation(value="修改部门或者权限组",tags = "修改部门或者权限组")
    public ApiResponse updateMenu(@RequestBody @ApiParam(name="部门或者权限组对象",value="传入json格式",required=true) SysGroup group){
        groupService.updateGroup(group);
        return new ApiResponse();
    }

    @PostMapping(value = "/delObj")
    @ApiOperation(value="删除部门或者权限组",tags = "删除部门或者权限组")
    public ApiResponse deleteMenu(@ApiParam(name="id",value="部门或者权限组id",required=true) String id){
        groupService.deleteGroup(id);
        return new ApiResponse();
    }

    @PostMapping(value = "/validateCode")
    @ApiOperation(value="验证编码是否存在",tags = "验证编码是否存在")
    public ApiResponse validateCode(@ApiParam(name="code",value="编码",required=true) String code){
        return new ApiResponse(groupService.validateCode(code));
    }

    @GetMapping(value = "/getGroupUsers")
    @ApiOperation(value="获取部门或者权限组下所有用户",tags = "获取部门或者权限组下所有用户")
    public ApiResponse getGroupUserList(@ApiParam(name="page",value="起始位置",required=true) Integer page,
                                        @ApiParam(name="limit",value="分页大小",required=true) Integer limit,
                                        @ApiParam(name="groupId",value="组id",required=true) String groupId,
                                        @ApiParam(name="name",value="用户账号或者姓名") String name){
        PageInfo<Map> list = groupService.getGroupUserPage(page,limit,groupId,name);
        return new ApiResponse(list);
    }

    @PostMapping(value = "/delGroupUsers")
    @ApiOperation(value="删除组下用户",tags = "删除组下用户")
    public ApiResponse delGroupUsers(@ApiParam(name="ids",value="用户ids,多个用户逗号隔开",required=true) String ids,
                                     @ApiParam(name="groupId",value="组id",required=true) String groupId){
        groupService.deleteGroupUsers(ids,groupId);
        return new ApiResponse();
    }

    @PostMapping(value = "/addGroupUsers")
    @ApiOperation(value="添加组下用户",tags = "添加组下用户")
    public ApiResponse addGroupUsers(@ApiParam(name="ids",value="用户ids,多个用户逗号隔开",required=true) String ids,
                                     @ApiParam(name="groupId",value="组id",required=true) String groupId){
        groupService.addGroupUsers(ids,groupId);
        return new ApiResponse();
    }
}
