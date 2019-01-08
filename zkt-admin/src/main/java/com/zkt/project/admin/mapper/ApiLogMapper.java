/*
 * Copyright(C), 2015-2018, Beifeng
 * FileName: ApiLogMapper
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

import com.zkt.project.admin.entity.SysApiLog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author liwei
 * @create 2018/10/17 0017
 */
public interface ApiLogMapper extends Mapper<SysApiLog> {
    List<Map> getLogList(@Param("name")String name, @Param("startDate")String startDate, @Param("endDate")String endDate);

    @Delete("truncate table sys_api_log")
    void removeAll();
}
