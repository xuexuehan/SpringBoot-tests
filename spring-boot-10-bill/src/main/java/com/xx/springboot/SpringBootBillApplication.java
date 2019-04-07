package com.xx.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.xx.springboot.mapper")
@SpringBootApplication
public class SpringBootBillApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBillApplication.class, args);
	}

}
