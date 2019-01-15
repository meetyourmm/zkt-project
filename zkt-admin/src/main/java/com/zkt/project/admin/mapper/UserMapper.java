/*
 * Copyright(C), 2015-2018, Beifeng
 * FileName: UserMapper
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
package com.zkt.project.admin.mapper;

import com.zkt.project.admin.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 用户mapper
 *
 * @author liwei
 * @create 2018/10/12 0012
 */
public interface UserMapper extends Mapper<SysUser> {

     List<Map> getUserPage(@Param("name")String name);

     int checkByUserName(@Param("userName")String userName);

     /**
      * 获取组下面的用户
      * @param groupId
      * @param name
      * @return
      */
     List<Map> getGroupUserPage(@Param("groupId")String groupId, @Param("name")String name);

     int checkUniqueUser(SysUser user);
}
