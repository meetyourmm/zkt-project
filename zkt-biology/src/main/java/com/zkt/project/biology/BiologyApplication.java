package com.zkt.project.biology;

import com.zkt.common.web.EnableSoaClient;
import com.zkt.common.web.annotation.IgnoreUserToken;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableSwagger2
@MapperScan(basePackages ={"com.zkt.project.biology.mapper"} )
@ComponentScan(basePackages = {"com.zkt.project.biology","com.zkt.common.web"})
@EnableSoaClient
@EnableCaching
@IgnoreUserToken
public class BiologyApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiologyApplication.class, args);
	}
}
