/*
 * Copyright(C), 2015-2019, Beifeng
 * FileName: DictionaryMapper
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

import com.zkt.project.admin.entity.SysDictionary;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author liwei
 * @create 2019/1/9 0009
 */
public interface DictionaryMapper extends Mapper<SysDictionary> {

    List<SysDictionary> getDictListByParentId(@Param("parentId")String parentId, @Param("name")String name);

    List<Map> getDictListByType(@Param("parentId")String parentId, @Param("ctype")String ctype);
}
