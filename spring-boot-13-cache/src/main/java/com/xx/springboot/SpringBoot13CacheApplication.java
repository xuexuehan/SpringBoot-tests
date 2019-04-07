package com.xx.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching//开启注解版的缓存
@MapperScan("com.xx.springboot.mapper")
@SpringBootApplication
public class SpringBoot13CacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot13CacheApplication.class, args);
	}

}
