/*
 * Copyright(C), 2015-2018, Beifeng
 * FileName: MenuService
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

import com.zkt.common.core.constant.EnumUtil;
import com.zkt.common.core.context.UserContextHandler;
import com.zkt.project.admin.entity.SysMenu;
import com.zkt.project.admin.mapper.MenuMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author liwei
 * @create 2018/10/16 0016
 */
@Service
@Transactional
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 获取所有菜单
     * @return
     */
    @Cacheable(value="permission",key="'menus'")
    public List<SysMenu> selectListAll() {
        Example example = new Example(SysMenu.class);
        example.createCriteria().andEqualTo("status", EnumUtil.DataStatus.ENABLE);
        example.setOrderByClause("order_num asc");
        return menuMapper.selectByExample(example);
    }

    /**
     * 获取用户可以访问的菜单
     *
     * @param userId
     * @return
     */
    @Cacheable(value="permission",key="#userId")
    public List<SysMenu> getUserAuthorityMenuByUserId(String userId) {
        return menuMapper.selectAuthorityMenuByUserId(userId);
    }

    public List<SysMenu> getAllMenusByTitle(String title){
        Example example = new Example(SysMenu.class);
        if (!StringUtils.isEmpty(title)) {
            example.createCriteria().andLike("title", "%" + title + "%");
        }
        example.setOrderByClause("order_num asc");
        return menuMapper.selectByExample(example);
    }

    public SysMenu getMenuById(String menuId){
        return menuMapper.selectByPrimaryKey(menuId);
    }

    @CacheEvict(value="permission",allEntries=true)
    public void addMenu(SysMenu menu){
        String operatorUserId = UserContextHandler.getUserID();
        menu.setCreateTime(new Date());
        menu.setCreateUser(operatorUserId);
        menu.setUpdateTime(new Date());
        menu.setUpdateUser(operatorUserId);

        menuMapper.insertSelective(menu);
    }

    @CacheEvict(value="permission",allEntries=true)
    public void updateMenu(SysMenu menu){
        String operatorUserId = UserContextHandler.getUserID();
        menu.setUpdateTime(new Date());
        menu.setUpdateUser(operatorUserId);
        menuMapper.updateByPrimaryKeySelective(menu);
    }

    @CacheEvict(value="permission",allEntries=true)
    public void deleteMenu(String menuId){
//        SysMenu menu = menuMapper.selectByPrimaryKey(menuId);
//        menu.setStatus(EnumUtil.DataStatus.DELETE.getStatus());
        menuMapper.deleteByPrimaryKey(menuId);
    }


    public Boolean validateCode(String code) {
        return menuMapper.checkByCode(code)==0?false:true;
    }

    public List<SysMenu> getMenuElementList(String parentId, String name) {
        Example example = new Example(SysMenu.class);
        Example.Criteria criteria =  example.createCriteria();
        criteria.andEqualTo("parentId",parentId).andNotEqualTo("type",EnumUtil.MenuType.MENU.getType());
        if (!StringUtils.isEmpty(name)) {
            criteria.andLike("title", "%" + name + "%");
        }
        example.setOrderByClause("order_num asc");
        return menuMapper.selectByExample(example);
    }
}
