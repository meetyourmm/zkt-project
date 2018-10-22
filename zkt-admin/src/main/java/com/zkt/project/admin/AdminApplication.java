package com.zkt.project.admin;

import com.zkt.common.web.EnableSoaClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableSwagger2
@MapperScan(basePackages ={"com.zkt.project.admin.mapper"} )
@ComponentScan(basePackages = {"com.zkt.project.admin","com.zkt.common.web"})
@EnableSoaClient
@EnableCaching
public class AdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}
}
