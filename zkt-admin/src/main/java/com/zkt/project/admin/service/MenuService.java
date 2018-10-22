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

import com.zkt.project.admin.entity.SysMenu;
import com.zkt.project.admin.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Cacheable(value="permission",key="'menu'")
    public List<SysMenu> selectListAll() {
        return menuMapper.selectAll();
    }

    /**
     * 获取用户可以访问的菜单
     *
     * @param userId
     * @return
     */
    @Cacheable(value="permission",key="'menu::'+#userId")
    public List<SysMenu> getUserAuthorityMenuByUserId(String userId) {
        return menuMapper.selectAuthorityMenuByUserId(userId);
    }
}
