/*
 * Copyright(C), 2015-2018, Beifeng
 * FileName: MenuTree
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

import com.zkt.common.core.entity.TreeNode;

/**
 * 菜单树
 *
 * @author liwei
 * @create 2018/10/16 0016
 */
public class MenuTree extends TreeNode {
    String icon;
    String title;
    boolean spread = false;
    String path;
    String component;
    String authority;
    String redirect;
    String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    String label;

    public MenuTree() {
    }

    public MenuTree(String id, String name, String parentId) {
        this.id = id;
        this.parentId = parentId;
        this.title = name;
        this.label = name;
    }
    public MenuTree(String id, String name, MenuTree parent) {
        this.id = id;
        this.parentId = parent.getId();
        this.title = name;
        this.label = name;
    }
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public boolean isSpread() {
        return spread;
    }

    public void setSpread(boolean spread) {
        this.spread = spread;
    }
}
