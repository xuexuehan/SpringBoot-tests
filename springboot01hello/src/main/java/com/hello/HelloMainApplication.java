package com.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @SpringBootApplication  用于标识为引导类,说明当前是一个SpringBoot项目
 *      @SpringBootConfiguration
 *          @Configuration 它属于spring中的一个注解，定义配置类，等价于配置文件
 *              @Component 添加到spring容器中，表示是一个组件
 *      @EnableAutoConfiguration
 *          @AutoConfigurationPackage 将引导类所在包及其子包下面所有的组件添加到spring容器中
 *          @Import({AutoConfigurationImportSelector.class})
 *               1、将所有组件以全类名的方式返回，并且添加到spring容器中
 *               2、会给容器中导入非常多的自动配置类（*****AutoiConfiguration），就是导入并配置好很多当前项目中所需要的组件
 *               省去我们手动编写配置然后注入到组件中
 *      @ComponentScan 被该注解标识的类，会被spring容器进行管理
 *
 *
 **/
@SpringBootApplication//标识为引导类,说明当前是一个SpringBoot项目
public class HelloMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloMainApplication.class, args);
    }
}
