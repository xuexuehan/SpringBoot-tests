package com.xx.springboot.controller;

import com.xx.springboot.entities.User;
import com.xx.springboot.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    UserMapper userMapper;

    //User列表
    @GetMapping("/users")
    public String list(Map<String, Object> map, User user) {
        logger.info("查询用户信息" + user);
        List<User> users = userMapper.getUser(user);
        map.put("users", users);
        map.put("username", user.getUsername());
        return "user/list";
    }

    //查看单个用户信息 && 进入修改界面
    @GetMapping("/user/{id}")
    public String view(@PathVariable("id") Integer id,
                       Map<String, Object> map,
                       @RequestParam(value = "type", defaultValue = "view") String type) {
        logger.info("查询用户信息" + id);
        User user = userMapper.getUserById(id);
        map.put("user", user);
        //return "user/view";
        return "user/" + type;
    }

    //修改用户信息
    @PutMapping("/user")
    public String update(User user) {
        logger.info("更新用户操作---" + user);
        userMapper.updateUser(user);
        return "redirect:/users";
    }

    //前往添加界面
    @GetMapping("/user")
    public String toAddPage() {
        return "user/add";
    }

    //添加操作
    @PostMapping("/user")
    public String add(User user) {
        logger.info("添加用户操作---" + user);
        userMapper.addUser(user);
        return "redirect:/users";
    }

    //删除用户信息
    @DeleteMapping("/user/{id}")
    public String delete(@PathVariable("id") Integer id) {
        logger.info("删除用户操作---" + id);
        userMapper.deleteUserById(id);
        return "redirect:/users";
    }

    //进入修改密码界面
    @GetMapping("/user/pwd")
    public String toUpdatePwdPage() {
        return "main/password";
    }

    @ResponseBody
    @GetMapping("/user/pwd/{oldPwd}")
    public Boolean checkPwd(HttpSession session, @PathVariable("oldPwd") String oldPwd) {
        logger.info("旧密码：" + oldPwd);

        //从session获取当前登录用户user对象
        User user = (User) session.getAttribute("loginUser");
        if(user.getPassword().equals(oldPwd)) {
            //输入旧密码正确
            return true;
        }
        return false;
    }

    @PostMapping("/user/pwd")
    public String updatePwd(HttpSession session, String password) {
        //1、从session获取当前登录用户信息
        User user  = (User) session.getAttribute("loginUser");
        //2、更新密码
        user.setPassword(password);
        //3、提交到数据库
        userMapper.updateUser(user);
        //4、注销重新登录
        return "redirect:/logout";
    }
}
