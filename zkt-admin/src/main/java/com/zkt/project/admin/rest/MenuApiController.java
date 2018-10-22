/*
 * Copyright(C), 2015-2018, Beifeng
 * FileName: MenuApiController
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

import com.zkt.common.core.context.UserContextHandler;
import com.zkt.common.core.util.TreeUtil;
import com.zkt.common.web.response.ApiResponse;
import com.zkt.project.admin.vo.MenuTree;
import com.zkt.project.admin.entity.SysMenu;
import com.zkt.project.admin.service.MenuService;
import com.zkt.project.admin.vo.MenuTreeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单权限controller
 *
 * @author liwei
 * @create 2018/10/16 0016
 */
@RestController
@Api(value="MenuApiController|菜单权限接口")
@RequestMapping("/api/menu")
public class MenuApiController {

    @Autowired
    private MenuService menuService;

    /**
     * 获取用户菜单树
     * @return
     */
    @GetMapping(value = "/all")
    @ApiOperation(value="获取所有菜单",tags = "获取所有菜单")
    public ApiResponse getAllMenus(){
        return new ApiResponse(menuService.selectListAll());
    }
}
