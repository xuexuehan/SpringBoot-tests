package com.xx.springboot.mapper;


import com.xx.springboot.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {

    @Select("select * from users where id = #{id}")
    User getUserById(Integer id);


    @Update("UPDATE `users` SET `username` = #{username}, `real_name` = #{realName}, " +
            "`password` = #{password}, `gender` = #{gender}, `birthday` = #{birthday}, `user_type` = #{userType} WHERE id = #{id}")
    int updateUser(User user);

    @Insert("INSERT INTO `users`(`username`, `real_name`, `password`, `gender`, `birthday`, `user_type`) " +
            "VALUES (#{username}, #{realName}, #{password}, #{gender}, #{birthday}, #{userType})")
    int addUser(User user);


    @Delete("delete from users where id = #{id}")
    int deleteUser(Integer id);

}
