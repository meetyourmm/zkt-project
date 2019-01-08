/*
 * Copyright(C), 2015-2018, Beifeng
 * FileName: ApiLogService
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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zkt.project.admin.entity.SysApiLog;
import com.zkt.project.admin.mapper.ApiLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author liwei
 * @create 2018/10/17 0017
 */
@Service
public class ApiLogService {

    @Autowired
    private ApiLogMapper apiLogMapper;

    public void saveLog(SysApiLog log){
        apiLogMapper.insertSelective(log);
    }

    public PageInfo<Map> getLogListPage(Integer page, Integer limit, String name, String startDate, String endDate) {
        PageHelper.startPage(page,limit);

        List<Map> list = apiLogMapper.getLogList(name,startDate,endDate);
        return new PageInfo<>(list);
    }

    public void removeAll() {
        apiLogMapper.removeAll();
    }
}
