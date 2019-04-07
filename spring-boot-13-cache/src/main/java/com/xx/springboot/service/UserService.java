package com.xx.springboot.service;

import com.xx.springboot.entity.User;
import com.xx.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "user")
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Cacheable(/*cacheNames = "user"*/)
    public User getUserById(Integer id) {
        User user = userMapper.getUserById(id);
        return user;
    }

    @CachePut(/*cacheNames = "user",*/ key = "#user.id")
    public User updateUser(User user) {
        userMapper.updateUser(user);
        return user;
    }

    @CacheEvict(/*cacheNames = "user", */key = "#id", allEntries = true)
    public Integer deleteUserById(Integer id) {
        userMapper.deleteUser(id);
        return id;
    }
}
