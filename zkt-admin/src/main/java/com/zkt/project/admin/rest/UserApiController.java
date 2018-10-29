/*
 * Copyright(C), 2015-2018, Beifeng
 * FileName: UserController
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

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zkt.common.core.constant.CommonConstant;
import com.zkt.common.core.context.UserContextHandler;
import com.zkt.common.core.util.JwtHelper;
import com.zkt.common.web.constant.ResponseConstant;
import com.zkt.common.web.exception.auth.UserTokenException;
import com.zkt.common.web.jwt.AccessToken;
import com.zkt.common.web.response.ApiResponse;
import com.zkt.project.admin.entity.SysUser;
import com.zkt.project.admin.service.MenuService;
import com.zkt.project.admin.service.UserService;
import com.zkt.project.admin.vo.FrontUser;
import com.zkt.project.admin.vo.MenuTree;
import com.zkt.project.admin.vo.MenuTreeUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liwei
 * @create 2018/10/16 0016
 */
@RestController
@Api(value="UserApiController|用户相关接口")
@RequestMapping("/api/user")
public class UserApiController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/menus")
    @ApiOperation(value="获取用户菜单",tags = "获取用户菜单")
    public ApiResponse getMenusByUserId() {
        String userId = UserContextHandler.getUserID();
        List<MenuTree> menus =  MenuTreeUtil.getMenuTree(menuService.getUserAuthorityMenuByUserId(userId),CommonConstant.ROOT_NODE);
        return new ApiResponse(menus);
    }

    @GetMapping(value = "/info")
    @ApiOperation(value="获取当前用户信息",tags = "获取当前用户信息")
    public ApiResponse getUserInfo(){
        String userId = UserContextHandler.getUserID();
        SysUser user = userService.getUserById(userId);
        List<MenuTree> menus =  MenuTreeUtil.getMenuTree(menuService.getUserAuthorityMenuByUserId(userId),CommonConstant.ROOT_NODE);
        FrontUser frontUser = new FrontUser();
        BeanUtils.copyProperties(user, frontUser);
        frontUser.setMenus(menus);
        if(user.getIsAdmin().equals("2")){//超级管理员
            frontUser.setRole("admin");
        }
        return new ApiResponse(frontUser);
    }

    @PostMapping(value = "/page")
    @ApiOperation(value="获取用户列表",tags = "获取用户列表")

    public ApiResponse getUserPage(@ApiParam(name="page",value="起始位置",required=true) Integer page,
                                   @ApiParam(name="limit",value="分页大小",required=true) Integer limit,
                                   @ApiParam(name="name",value="账号、昵称、姓名") String name){
        PageInfo<Map> list = userService.getUserPage(page,limit,name);
        return new ApiResponse(list);
    }

    @PostMapping(value = "/addObj")
    @ApiOperation(value="添加用户",tags = "添加用户")
    public ApiResponse addUser(@RequestBody @ApiParam(name="用户对象",value="传入json格式",required=true) SysUser user){
        userService.addUser(user);
        return new ApiResponse();
    }

    @PostMapping(value = "/getObj")
    @ApiOperation(value="获取用户",tags = "获取用户")
    public ApiResponse getUserById(@ApiParam(name="id",value="用户id",required=true) String id){
        return new ApiResponse(userService.getUserById(id));
    }

    @PostMapping(value = "/putObj")
    @ApiOperation(value="修改用户",tags = "修改用户")
    public ApiResponse updateUser(@RequestBody @ApiParam(name="用户对象",value="传入json格式",required=true) SysUser user){
        userService.updateUser(user);
        return new ApiResponse();
    }

    @PostMapping(value = "/delObj")
    @ApiOperation(value="删除用户",tags = "删除用户")
    public ApiResponse deleteUser(@ApiParam(name="id",value="用户id",required=true) String id){
        userService.deleteUser(id);
        return new ApiResponse();
    }
    @PostMapping(value = "/validateUser")
    @ApiOperation(value="验证账号是否存在",tags = "验证账号是否存在")
    public ApiResponse validateUser(@ApiParam(name="userName",value="用户账号",required=true) String userName){
        return new ApiResponse(userService.checkByUserName(userName));
    }

}
