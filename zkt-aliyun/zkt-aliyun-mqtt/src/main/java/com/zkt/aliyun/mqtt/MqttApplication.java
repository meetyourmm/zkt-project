/*
 * Copyright(C), 2015-2018, Beifeng
 * FileName: MqttApplication
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
package com.zkt.aliyun.mqtt;

import com.zkt.common.web.EnableSoaClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * aliyun aqtt接口
 *
 * @author liwei
 * @create 2018/11/1 0001
 */
@SpringBootApplication
@EnableSwagger2
@MapperScan(basePackages ={"com.zkt.aliyun.mqtt.mapper"} )
@ComponentScan(basePackages = {"com.zkt.aliyun.mqtt","com.zkt.common.web"})
@EnableSoaClient
public class MqttApplication {
    public static void main(String[] args) {
        SpringApplication.run(MqttApplication.class, args);
    }
}
