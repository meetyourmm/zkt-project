/*
 * Copyright(C), 2015-2018, Beifeng
 * FileName: UserContext
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
package com.zkt.common.core.context;

import com.zkt.common.core.constant.CommonConstant;
import com.zkt.common.core.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户信息上下文
 *
 * @author liwei
 * @create 2018/10/16 0016
 */
public class UserContextHandler {

    public static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>();

    public static void set(String key, Object value) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<String, Object>();
            threadLocal.set(map);
        }
        map.put(key, value);
    }

    public static Object get(String key){
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<String, Object>();
            threadLocal.set(map);
        }
        return map.get(key);
    }

    public static String getUserID(){
        Object value = get(CommonConstant.CONTEXT_KEY_USER_ID);
        return StringUtil.getObjectValue(value);
    }

    public static String getUserName(){
        Object value = get(CommonConstant.CONTEXT_KEY_USERNAME);
        return StringUtil.getObjectValue(value);
    }


    public static String getToken(){
        Object value = get(CommonConstant.CONTEXT_KEY_USER_TOKEN);
        return StringUtil.getObjectValue(value);
    }
    public static void setToken(String token){set(CommonConstant.CONTEXT_KEY_USER_TOKEN,token);}

    public static void setUserID(String userID){
        set(CommonConstant.CONTEXT_KEY_USER_ID,userID);
    }

    public static void setUsername(String username){
        set(CommonConstant.CONTEXT_KEY_USERNAME,username);
    }

    public static void remove(){
        threadLocal.remove();
    }

}
