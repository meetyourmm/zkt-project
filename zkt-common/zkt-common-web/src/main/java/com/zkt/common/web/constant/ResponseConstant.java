/*
 * Copyright(C), 2015-2018, Beifeng
 * FileName: AuthConstant
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
package com.zkt.common.web.constant;

/**
 * 接口返回代码以及信息
 *
 * @author liwei
 * @create 2018/10/11 0011
 */
public class ResponseConstant {

    //返回代码
    public static final Integer EX_TOKEN_INVALID_CODE = 40101;// 用户token异常code
    public static final Integer EX_USER_INVALID_CODE = 40001;// 用户名异常code
    public static final Integer EX_UNAUTH_CODE = 40401;//无权限访问
    public static final Integer EX_COMMON_CODE = 500;
    public static final Integer SUCCESS_CODE = 200;

    //返回信息
    public static final String OK = "操作成功！";
    public static final String ERROR = "内部异常！";

    public static final String EX_USER_PASS_INVALID_MSG = "用户名或密码错误";
    public static final String EX_USER_NON_MSG = "用户不存在";
    public static final String EX_USER_EXIST_MSG = "该信息已被注册";

    public static final String EX_SQL_MSG = "SQL语句执行出错";

    public static final String EX_TOKEN_NULL_MSG = "token不能为空";
    public static final String EX_TOKEN_ERROR_MSG = "token验证错误";
    public static final String EX_TOKEN_EXPIRE_MSG = "token过期";
    public static final String EX_UNAUTH_MSG = "请求资源无权限";
}
