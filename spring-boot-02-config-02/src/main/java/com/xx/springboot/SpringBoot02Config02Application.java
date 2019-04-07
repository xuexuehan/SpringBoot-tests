package com.xx.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBoot02Config02Application {
	public static void main(String[] args) {
		//返回module的路径 E:\Study-hx\技术总结_2019\SpringBoot\springboot01hello
		System.out.println(System.getProperty("user.dir"));
		SpringApplication.run(SpringBoot02Config02Application.class, args);
	}

}
