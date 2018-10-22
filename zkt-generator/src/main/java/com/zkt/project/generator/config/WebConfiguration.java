package com.zkt.project.generator.config;


import com.zkt.common.web.handler.ApiExceptionHandler;
import com.zkt.common.web.interceptor.UserAuthRestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Collections;

/**
 * generator项目配置
 */
@Configuration("generatorWebConfig")
@Primary
public class WebConfiguration implements WebMvcConfigurer {
    @Bean
    ApiExceptionHandler getApiExceptionHandler() {
        return new ApiExceptionHandler();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getUserAuthRestInterceptor());
    }

    @Bean
    UserAuthRestInterceptor getUserAuthRestInterceptor() {
        return new UserAuthRestInterceptor();
    }


}
