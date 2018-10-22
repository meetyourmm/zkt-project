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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

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
    @ApiOperation(value="获取用户信息",tags = "获取用户信息")
    public ApiResponse getUserInfo(){
        String userId = UserContextHandler.getUserID();
        SysUser user = userService.getUserById(userId);
        FrontUser frontUser = new FrontUser();
        BeanUtils.copyProperties(user, frontUser);
        return new ApiResponse(frontUser);
    }

}
