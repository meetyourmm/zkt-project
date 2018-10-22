/*
 * Copyright(C), 2015-2018, Beifeng
 * FileName: AccessToken
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
package com.zkt.common.web.jwt;

/**
 * token信息
 *
 * @author liwei
 * @create 2018/10/12 0012
 */
public class AccessToken {
    private String access_token;
    private String token_type;
    private long expires_in;

    public AccessToken(String token,String type,long expires) {
        this.access_token = token;
        this.token_type = type;
        this.expires_in = expires;
    }

    public String getAccess_token() {
        return this.access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return this.token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public long getExpires_in() {
        return this.expires_in;
    }

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }
}
