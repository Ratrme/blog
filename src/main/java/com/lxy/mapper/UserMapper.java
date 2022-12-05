package com.lxy.mapper;

import com.lxy.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    @Select("select id, nickname, username, email, avatar, type, create_time, update_time from t_user where username=#{username} AND password=#{password}")
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Select("select id,nickname,email,avatar from t_user where type = 1")
    User getUser();

}
