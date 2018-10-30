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

import com.github.pagehelper.PageInfo;
import com.zkt.common.core.constant.CommonConstant;
import com.zkt.common.core.context.UserContextHandler;
import com.zkt.common.core.util.TreeUtil;
import com.zkt.common.web.response.ApiResponse;
import com.zkt.project.admin.entity.SysUser;
import com.zkt.project.admin.vo.FrontUser;
import com.zkt.project.admin.vo.MenuTree;
import com.zkt.project.admin.entity.SysMenu;
import com.zkt.project.admin.service.MenuService;
import com.zkt.project.admin.vo.MenuTreeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
     * 获取所有菜单树
     * @return
     */
    @GetMapping(value = "/all")
    @ApiOperation(value="获取所有菜单",tags = "获取所有菜单")
    public ApiResponse getAllMenus(){
        return new ApiResponse(menuService.selectListAll());
    }

    /**
     * 根据查询获取根节点树
     * @return
     */
    @GetMapping(value = "/tree")
    @ApiOperation(value="获取所有菜单",tags = "获取所有菜单")
    public ApiResponse getAllMenusByTitle(@ApiParam(name="title",value="节点名称") String title){
        return new ApiResponse( MenuTreeUtil.getMenuTree(menuService.getAllMenusByTitle(title),CommonConstant.ROOT_NODE));
    }

    @PostMapping(value = "/addObj")
    @ApiOperation(value="添加菜单",tags = "添加菜单")
    public ApiResponse addMenu(@RequestBody @ApiParam(name="菜单对象",value="传入json格式",required=true) SysMenu menu){
        menuService.addMenu(menu);
        return new ApiResponse();
    }

    @PostMapping(value = "/getObj")
    @ApiOperation(value="获取菜单信息",tags = "获取菜单信息")
    public ApiResponse getMenuById(@ApiParam(name="id",value="菜单id",required=true) String id){
        return new ApiResponse(menuService.getMenuById(id));
    }

    @PostMapping(value = "/putObj")
    @ApiOperation(value="修改菜单",tags = "修改菜单")
    public ApiResponse updateMenu(@RequestBody @ApiParam(name="菜单对象",value="传入json格式",required=true) SysMenu menu){
        menuService.updateMenu(menu);
        return new ApiResponse();
    }

    @PostMapping(value = "/delObj")
    @ApiOperation(value="删除菜单",tags = "删除菜单")
    public ApiResponse deleteMenu(@ApiParam(name="id",value="菜单id",required=true) String id){
        menuService.deleteMenu(id);
        return new ApiResponse();
    }

    @PostMapping(value = "/validateCode")
    @ApiOperation(value="验证菜单编码是否存在",tags = "验证菜单编码是否存在")
    public ApiResponse validateCode(@ApiParam(name="code",value="菜单编码",required=true) String code){
        return new ApiResponse(menuService.validateCode(code));
    }

    @GetMapping(value = "/getElementList")
    @ApiOperation(value="获取菜单下所有资源",tags = "获取菜单下所有资源")
    public ApiResponse getMenuElementList(@ApiParam(name="parentId",value="菜单id",required=true) String parentId, @ApiParam(name="name",value="资源名称") String name){
        return new ApiResponse(menuService.getMenuElementList(parentId,name));
    }
}
