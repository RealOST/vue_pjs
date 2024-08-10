package com.bd.mapper;

import com.bd.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select * from user where username = #{username} and password = #{password}")
    User select(User user);

    //根据用户名查询用户对象
    @Select("select * from user where username = #{username}")
    User selectByUsername(String username);

    //添加用户
    @Insert("insert into user values(null,#{username},#{password})")
    void add(User user);
}
