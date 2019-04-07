package com.xx.springboot.controller;

import com.xx.springboot.dao.UserDao;
import com.xx.springboot.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@Controller
public class UserController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    UserDao userDao;

    //User列表
    @GetMapping("/users")
    public String list(Map<String, Object> map, @RequestParam(value = "username", required = false) String username) {
        logger.info("查询用户信息" + username);
        Collection<User> users = userDao.getAll(username);
        map.put("users", users);
        map.put("username", username);
        return "user/list";
    }

    //查看单个用户信息 && 进入修改界面
    @GetMapping("/user/{id}")
    public String view(@PathVariable("id") Integer id,
                       Map<String, Object> map,
                       @RequestParam(value = "type", defaultValue = "view") String type) {
        logger.info("查询用户信息" + id);
        User user = userDao.get(id);
        map.put("user", user);
        //return "user/view";
        return "user/" + type;
    }

    //修改用户信息
    @PutMapping("/user")
    public String update(User user) {
        logger.info("更新用户操作---" + user);
        userDao.save(user);
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
        userDao.save(user);
        return "redirect:/users";
    }

    //删除用户信息
    @DeleteMapping("/user/{id}")
    public String delete(@PathVariable("id") Integer id) {
        logger.info("删除用户操作---" + id);
        userDao.delete(id);
        return "redirect:/users";
    }
}
