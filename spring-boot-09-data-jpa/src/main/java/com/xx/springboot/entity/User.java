package com.xx.springboot.entity;

import javax.persistence.*;

/**
 * jpa采用的是orm模型
 * */

@Entity //说明是和数据表映射的类
@Table(name = "tal_user") //指定对应映射的表名，省略默认为类名，User
public class User {
    @Id //指定主键
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "user_name", length = 5)//与数据表对应的字段
    private String username;

    @Column//默认字段名为类属性名
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }
}
