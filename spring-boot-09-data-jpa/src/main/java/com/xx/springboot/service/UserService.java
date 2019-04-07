package com.xx.springboot.service;

import com.xx.springboot.dao.UserRepository;
import com.xx.springboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 模拟事务管理
 * */


@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    //isolation指定隔离级别，propagation指定传播行为
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)//开启事务管理
    @Override
    public Boolean addUser(User user) {

        //模拟转账 转出
        userRepository.save(new User("1", "1"));
        userRepository.save(new User("2", "2"));
        userRepository.save(new User("3", "3"));
        userRepository.save(new User("4", "4"));
        userRepository.save(new User("55555", "5"));

        //转入：转入失败
//        userRepository.save(new User("556666", "4"));
//        userRepository.save(new User("5555588", "5"));
        userRepository.save(user);
        return null;
    }
}
