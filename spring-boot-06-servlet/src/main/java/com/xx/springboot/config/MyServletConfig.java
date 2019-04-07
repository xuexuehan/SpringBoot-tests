package com.xx.springboot.config;

import com.xx.springboot.filter.MyFilter;
import com.xx.springboot.listener.MyListener;
import com.xx.springboot.servlet.HelloServlet;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * 注册servlet相关组件
 * */
@Configuration
public class MyServletConfig {

    @Bean
    public WebServerFactoryCustomizer webServerFactoryCustomizer() {
        return new WebServerFactoryCustomizer() {
            @Override
            public void customize(WebServerFactory factory) {
                ConfigurableServletWebServerFactory factory1 =
                        (ConfigurableServletWebServerFactory)factory;
                //修改端口号，如果配置文件中与定制器中的配置冲突，默认采用定制器的配置
                factory1.setPort(8082);
                factory1.setContextPath("/servlet2");
            }
        };
    }


    /**
     * 注册servlet组件
     * */
    @Bean
    public ServletRegistrationBean helloServlet() {
        ServletRegistrationBean bean
                = new ServletRegistrationBean(new HelloServlet(), "/hello");
        bean.setLoadOnStartup(1);//设置sevlet组件参数
        return bean;
    }

    /**
     * 注册filtert组件
     * */

    @Bean
    public FilterRegistrationBean myFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        //设置自定义filter
        bean.setFilter(new MyFilter());
        //过滤哪一些请求
        bean.setUrlPatterns(Arrays.asList("/hello"));
        return bean;
    }

    /**
     * 注册listener组件
     * */
    @Bean
    public ServletListenerRegistrationBean myListener() {
        ServletListenerRegistrationBean bean = new ServletListenerRegistrationBean(new MyListener());
        return bean;
    }

}
