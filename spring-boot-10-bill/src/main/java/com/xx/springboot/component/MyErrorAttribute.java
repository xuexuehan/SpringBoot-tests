package com.xx.springboot.component;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Component//向容器添加该组件
public class MyErrorAttribute extends DefaultErrorAttributes {

    /**
     * 自定义数据进行响应
     * */

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
        map.put("name", "xuexuehan");
        return map;
    }
}
