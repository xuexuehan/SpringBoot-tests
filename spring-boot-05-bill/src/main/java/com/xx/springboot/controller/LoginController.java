package com.xx.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    @PostMapping("/login")
    public String login(HttpSession session, String username, String password, Map<String, Object> map) {
        //判断用户名不为空，且密码为123，则登录成功
        if(!StringUtils.isEmpty(username) && "123".equals(password)) {
            session.setAttribute("loginUser", username);
            //登录成功
            //重定向 redirect：可以重定向到任意一个请求中（包括其他项目），地址栏改变
            return "redirect:/main.html";
        }
        //登录失败
        map.put("msg", "用户名密码错误");
        return "main/login";
    }


    /**
     * 退出登录
     * */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        //1、清空session中用户信息
        session.removeAttribute("loginUser");
        //2、再将session进行注销
        session.invalidate();
        //3、返回登录页面
        return "redirect:/index.html";
    }
}
