package com.xx.springboot.dao;

import com.xx.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 自定义接口继承JpaRepository，就会有crud及分页等基本功能
 *
 */
//指定的泛型<操作的实体类，主键的类型>
public interface UserRepository extends JpaRepository<User, Integer> {

}
