package com.xx.springboot.config;

import com.xx.springboot.service.EmpService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
*@Configuration: 它属于spring中的一个注解,用于标识当前类是一个配置类，来表示对应spring配置文件
**/
@Configuration
public class EmpConfig {
    /**
     * @Bean 标识的方法用于向容器中注入组件
     * 1、返回值就是注入容器中组件对象
     * 2、方法名就是这个组件的 id 值
     *
     * */
    @Bean
    public EmpService empService2() {
        System.out.println("EmpService 组件注入成功");
        return new EmpService();
    }

}
