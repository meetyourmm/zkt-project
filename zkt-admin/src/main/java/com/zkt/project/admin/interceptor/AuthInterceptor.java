/*
 * Copyright(C), 2015-2018, Beifeng
 * FileName: AuthInterceptor
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
package com.zkt.project.admin.interceptor;

import com.zkt.common.web.annotation.IgnoreUserToken;
import com.zkt.common.web.constant.ResponseConstant;
import com.zkt.common.web.exception.auth.UnAuthException;
import com.zkt.common.web.interceptor.UserAuthRestInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 资源权限验证
 * 在微服务中需要验证，单应用只验证页面权限
 * @author liwei
 * @create 2018/12/19 0019
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(UserAuthRestInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        String requestUri = request.getRequestURI();
        // 配置该注解，说明不进行用户拦截
        IgnoreUserToken annotation = handlerMethod.getBeanType().getAnnotation(IgnoreUserToken.class);
        if (annotation == null) {
            annotation = handlerMethod.getMethodAnnotation(IgnoreUserToken.class);
        }
        if (annotation != null) {
            return super.preHandle(request, response, handler);
        }
        if(checkPermission(requestUri)){
            throw new UnAuthException(ResponseConstant.EX_UNAUTH_MSG);
        }
        return super.preHandle(request, response, handler);
    }

    /**
     * 判断是否有改权限
     * @param uri
     * @return
     */
    private Boolean checkPermission(String uri){
        return true;
    }
}
