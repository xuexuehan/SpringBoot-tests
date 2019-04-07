package com.xx.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 1、创建一个WebMvcConfigurer类型的子类
 * 2、类上用@Configuration标识它是一个配置类
 * 3、不能@EnableWebMvc标识
 * 原理：
 *  1、@Import({WebMvcAutoConfiguration.EnableWebMvcConfiguration.class})
 *  2、public static class EnableWebMvcConfiguration extends DelegatingWebMvcConfiguration
 *
 * */


@EnableWebMvc//完全掌控springmvc
@Configuration
public class MySpringConfigurer implements WebMvcConfigurer {

    //增加视图控制
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //发送 /xuexue 请求来到 success.html
        registry.addViewController("/xuexue").setViewName("success");
    }
}
