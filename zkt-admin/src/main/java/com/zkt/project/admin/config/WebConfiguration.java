package com.zkt.project.admin.config;


import com.zkt.common.web.handler.ApiExceptionHandler;
import com.zkt.common.web.interceptor.UserAuthRestInterceptor;
import com.zkt.project.admin.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Collections;

/**
 * admin项目配置
 */
@Configuration("admimWebConfig")
@Primary
public class WebConfiguration implements WebMvcConfigurer {
    @Bean
    ApiExceptionHandler getApiExceptionHandler() {
        return new ApiExceptionHandler();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getUserAuthRestInterceptor()).
                addPathPatterns(getIncludePathPatterns()).excludePathPatterns("/auth/*")
                ;
        //大部分系统不需要再去检验用户的接口权限 已经做了用户验证和菜单权限控制了
        //如果你需要，则要求在菜单管理中 将所有资源的请求纳入管理，并分配权限。下行可以注释
        //registry.addInterceptor(getAuthInterceptor()).addPathPatterns(getIncludePathPatterns()).excludePathPatterns("/auth/*");
    }

    @Bean
    UserAuthRestInterceptor getUserAuthRestInterceptor() {
        return new UserAuthRestInterceptor();
    }

    @Bean
    AuthInterceptor getAuthInterceptor(){
        return new AuthInterceptor();
    }


    /**
     * 需要用户认证判断的路径
     * @return
     */
    private ArrayList<String> getIncludePathPatterns() {
        ArrayList<String> list = new ArrayList<>();
        String[] urls = {
                "/api/**"
        };
        Collections.addAll(list, urls);
        return list;
    }

}
