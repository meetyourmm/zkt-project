/*
 * Copyright(C), 2015-2018, Beifeng
 * FileName: PropConfiguration
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
package com.zkt.project.biology.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

/**
 * 自定义配置
 *
 * @author liwei
 * @create 2018/10/30 0030
 */
@Configuration
@PropertySource("classpath:conf.properties")
public class PropConfiguration {
    @Value("${file.upload.base}")
    private String fileUpload;

    @Value("${redis.valid.time}")
    private String validTime;

    @Value("${page.aop.noauthority}")
    private String noAuthority;

    @Value("${page.aop.noerror}")
    private String noerror;

    public String getFileUpload() {
        return fileUpload;
    }

    public String getValidTime() {
        return validTime;
    }

    public String getNoAuthority() {
        return noAuthority;
    }

    public String getNoerror() {
        return noerror;
    }
}
