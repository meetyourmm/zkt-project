/*
 * Copyright(C), 2015-2019, Beifeng
 * FileName: DictionaryService
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

import com.zkt.project.admin.entity.SysDictionary;
import com.zkt.project.admin.mapper.DictionaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author liwei
 * @create 2019/1/9 0009
 */
@Service
public class DictionaryService {

    @Autowired
    private DictionaryMapper dictionaryMapper;

    public List<SysDictionary> getDictListByParentId(String parentId,String name) {
        return dictionaryMapper.getDictListByParentId(parentId,name);
    }

    @Cacheable(value="dictionary",key="#ctype")
    public List<Map> getDictChildren(String parentId, String ctype) {
        List<Map> all = dictionaryMapper.getDictListByType(parentId,ctype);
        return getNodeChildren(parentId,all);
    }

    private static List<Map> getNodeChildren(String parentId, List<Map> list){
        List<Map> res = new ArrayList<>();
        if(!CollectionUtils.isEmpty(list)){
            for(Map map:list){
                if(map.get("parent_id")!=null&&map.get("parent_id").toString().equals(parentId)){
                    map.put("children",getNodeChildren(map.get("id").toString(),list));
                    res.add(map);
                }
            }
        }
        return res;
    }
}
