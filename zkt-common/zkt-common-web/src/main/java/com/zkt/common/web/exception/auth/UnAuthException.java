/*
 * Copyright(C), 2015-2018, Beifeng
 * FileName: NoAuthException
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
package com.zkt.common.web.exception.auth;

import com.zkt.common.core.exception.BaseException;
import com.zkt.common.web.constant.ResponseConstant;

/**
 * 无权限访问错误
 *
 * @author liwei
 * @create 2018/12/19 0019
 */
public class UnAuthException extends BaseException {

    public UnAuthException(String message) {
        super(message, ResponseConstant.EX_UNAUTH_CODE);
    }
}
