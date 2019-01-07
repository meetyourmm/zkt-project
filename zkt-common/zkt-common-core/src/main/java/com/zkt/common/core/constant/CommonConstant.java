/*
 * Copyright(C), 2015-2018, Beifeng
 * FileName: CommonConstant
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
package com.zkt.common.core.constant;

/**
 * 公共常量
 *
 * @author liwei
 * @create 2018/10/12 0012
 */
public class CommonConstant {

    public static final String AUTH_HEADER = "Authorization";
    public static final String TOKEN_PRE = "bearer";
    public static final String ROOT_NODE = "-1";//所有顶级父级id

    public static final String CONTEXT_KEY_USER_ID = "currentUserId";
    public static final String CONTEXT_KEY_USER_NAME = "currentUserName";
    public static final String CONTEXT_KEY_USER_TYPE= "currentUserType";
    public static final String CONTEXT_KEY_USER_TOKEN = "currentUserToken";
    public static final String JWT_KEY_USER_ID = "userId";
    public static final String JWT_KEY_USER_NAME = "userName";
    public static final String JWT_KEY_USER_ROLE = "userRole";
}
