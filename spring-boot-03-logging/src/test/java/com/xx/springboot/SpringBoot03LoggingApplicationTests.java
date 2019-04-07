package com.xx.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBoot03LoggingApplicationTests {

	//日志记录器
	Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void contextLoads() {
		//1、下面定义的都是日志级别，由低到高：trace < debug < info < warn < error
		//2、spring boot默认设定的是 info 级别日志（日志默认级别也称为root级别）
		//3、可以通过配置文件进行修改 日志级别，设置某一个级别后，就只打印这个级别及后面更高级别的日志信息
		//   没有指定级别的就用springBoot默认规定的级别，root级别
		//跟踪运行信息
		logger.trace("这是 trace 日志信息");
		//调试信息
		logger.debug("这是 debug 日志信息");
		//自定义信息
		logger.info("这是 info 日志信息");
		//警告信息
		logger.warn("这是 warn 日志信息");
		//错误信息
		logger.error("这是 error 日志信息");
	}

}
