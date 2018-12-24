/*
 * Copyright(C), 2015-2018, Beifeng
 * FileName: MenuTreeUtil
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
package com.zkt.project.admin.vo;

import com.zkt.common.core.util.TreeUtil;
import com.zkt.project.admin.entity.SysGroup;
import com.zkt.project.admin.entity.SysMenu;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liwei
 * @create 2018/10/16 0016
 */
public class MenuTreeUtil {
    /**
     * 根据根节点id获取树形菜单
     * @param menus 菜单列表
     * @param root 根节点id
     * @return
     */
    public static List<MenuTree> getMenuTree(List<SysMenu> menus, String root) {
        List<MenuTree> trees = new ArrayList<MenuTree>();
        MenuTree node = null;
        for (SysMenu menu : menus) {
            node = new MenuTree();
            BeanUtils.copyProperties(menu, node);
            node.setLabel(menu.getTitle());
            node.setAuthority(menu.getCode());
            trees.add(node);
        }
        return TreeUtil.bulid(trees,root) ;
    }

    public static List<MenuTree> getGroupTree(List<SysGroup> groups, String root) {
        List<MenuTree> trees = new ArrayList<MenuTree>();
        MenuTree node = null;
        for (SysGroup group : groups) {
            node = new MenuTree();
            BeanUtils.copyProperties(group, node);
            node.setLabel(group.getName());
            node.setAuthority(group.getCode());
            trees.add(node);
        }
        return TreeUtil.bulid(trees,root) ;
    }
}
