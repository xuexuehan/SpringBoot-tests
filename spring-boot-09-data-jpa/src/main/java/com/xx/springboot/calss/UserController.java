package com.xx.springboot.calss;

import com.xx.springboot.dao.UserRepository;
import com.xx.springboot.entity.User;
import com.xx.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.get();
        return user;
    }

    @GetMapping("/user")
    public User addUser(User user) {
        User user1 = userRepository.save(user);
        return user1;
    }

    @Autowired
    IUserService userService;
    @GetMapping("/user2")
    public User addUser2(User user) {
        userService.addUser(user);
        return user;
    }

}
