package com.xx.springboot.mapper;


import com.xx.springboot.entities.User;

import java.util.List;

public interface UserMapper {

    User getUserByUsername(String username);

    List<User> getUser(User user);

    User getUserById(Integer id);

    int addUser(User user);

    int deleteUserById(Integer id);

    int updateUser(User user);
}
