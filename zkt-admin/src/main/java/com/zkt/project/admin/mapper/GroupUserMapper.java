/*
 * Copyright(C), 2015-2018, Beifeng
 * FileName: GroupMapper
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

import com.zkt.project.admin.entity.SysGroupUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author liwei
 * @create 2018/10/17 0017
 */
public interface GroupUserMapper extends Mapper<SysGroupUser> {

    @Delete("delete from sys_group_user where user_id in (#{ids}) and group_id = #{groupId}")
    void deleteGroupUsers(@Param("ids")String ids, @Param("groupId")String groupId);

    @Delete("delete from sys_group_user where group_id = #{groupId}")
    void deleteGroupAllUsers(@Param("groupId")String groupId);
}
