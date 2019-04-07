package com.xx.springboot.controller;

import com.xx.springboot.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping("/success")
    public String execute() {
        return "hello, xuexue";
    }

    @RequestMapping("/execute")
    public String success(Map<String, Object> map) {
        map.put("name", "雪雪");
        //classpath:/templates/success.html
        return "success";
    }

    @RequestMapping("/study")
    public String study(Map<String, Object> map, HttpServletRequest request) {
        List<User> userList = new ArrayList<User>();
        userList.add(new User("阿宝", 2));
        userList.add(new User("阿雪", 1));
        userList.add(new User("阿源", 2));
        map.put("userList", userList);

        map.put("sex", 1);
        map.put("man", 1);

        map.put("desc", "欢迎<h1>雪雪</h1>呀");

        request.getSession().setAttribute("user", new User("冲冲", 1));
        return "study";
    }
}
