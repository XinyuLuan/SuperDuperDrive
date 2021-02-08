package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    static final String GET_QUERY = "SELECT * FROM ";
    static final String INSERT_QUERY = "INSERT INTO ";
    static final String DELETE_QUERY = "DELETE FROM ";
    static final String TABLE = "USERS ";
    static final String WHERE_CONDITION = "WHERE ";


    @Select(GET_QUERY + TABLE + WHERE_CONDITION + "username = #{name}")
    User getUser(String name);

    @Insert(INSERT_QUERY + TABLE + "(username, salt, password, firstname, lastname) " +
            "VALUES (#{username}, #{salt}, #{password}, #{firstname}, #{lastname})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insert(Object o);

    @Delete(DELETE_QUERY + TABLE + WHERE_CONDITION + " userid = #{userid}")
    int delete(Integer userid);

    @Delete(DELETE_QUERY + TABLE)
    int deleteAll();

    @Select(GET_QUERY + TABLE)
    List<User> getAllUser();

    @Select(GET_QUERY + TABLE + WHERE_CONDITION + " userid = #{userId}")
    User getUserByID(Integer userId);
}
