package com.zkt.common.web.interceptor;


import com.zkt.common.core.constant.CommonConstant;
import com.zkt.common.core.context.UserContextHandler;
import com.zkt.common.web.constant.ResponseConstant;
import com.zkt.common.web.exception.auth.UserTokenException;
import com.zkt.common.web.jwt.Audience;
import com.zkt.common.core.util.JwtHelper;
import com.zkt.common.web.annotation.IgnoreUserToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ace on 2017/9/10.
 */
public class UserAuthRestInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(UserAuthRestInterceptor.class);

    @Autowired
    private Audience audience;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 配置该注解，说明不进行用户拦截
        IgnoreUserToken annotation = handlerMethod.getBeanType().getAnnotation(IgnoreUserToken.class);
        if (annotation == null) {
            annotation = handlerMethod.getMethodAnnotation(IgnoreUserToken.class);
        }
        if (annotation != null) {
            return super.preHandle(request, response, handler);
        }
        String auth = request.getHeader(CommonConstant.AUTH_HEADER);
        if (StringUtils.isEmpty(auth)) {
            if (request.getCookies() != null) {
                for (Cookie cookie : request.getCookies()) {
                    if (cookie.getName().equals(CommonConstant.AUTH_HEADER)) {
                        auth = cookie.getValue();
                    }
                }
            }
        }
        //token验证
        if ((auth != null) && (auth.length() > 7))
        {
            String headStr = auth.substring(0, 6).toLowerCase();
            if (headStr.compareTo("bearer") == 0)
            {

                auth = auth.substring(7, auth.length());
                try{
                    Claims claims = JwtHelper.parseJWT(auth, audience.getBase64Secret());
                    UserContextHandler.setUserID(claims.get(CommonConstant.JWT_KEY_USER_ID).toString());
                    UserContextHandler.setUsername(claims.get(CommonConstant.JWT_KEY_USER_NAME).toString());
                    return super.preHandle(request, response, handler);
                }catch (ExpiredJwtException ex) {
                    throw new UserTokenException(ResponseConstant.EX_TOKEN_EXPIRE_MSG);
                }catch (Exception ex) {
                    logger.error("内部错误，原因：{}", ex.getMessage());
                    throw new UserTokenException(ResponseConstant.EX_TOKEN_ERROR_MSG);
                }

            }else{
                throw new UserTokenException(ResponseConstant.EX_TOKEN_ERROR_MSG);
            }
        }else{
            throw new UserTokenException(ResponseConstant.EX_TOKEN_NULL_MSG);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContextHandler.remove();
        super.afterCompletion(request, response, handler, ex);
    }
}
