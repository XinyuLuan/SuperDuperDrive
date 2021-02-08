package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper extends IMapper{
    static final String GET_QUERY = "SELECT * FROM ";
    static final String INSERT_QUERY = "INSERT INTO ";
    static final String DELETE_QUERY = "DELETE FROM ";
    static final String TABLE = "CREDENTIALS ";
    static final String WHERE_CONDITION = "WHERE ";


    @Select(GET_QUERY + TABLE + WHERE_CONDITION + "username = #{username}")
    Credential getCredential(String username);


    @Insert(INSERT_QUERY + TABLE + "(url, username, key, password, userid) " +
            "VALUES(#{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insert(Credential credential);

    @Delete(DELETE_QUERY + TABLE + WHERE_CONDITION + " credentialid = #{credentiald}")
    int delete(Integer credentialid);

    @Delete(DELETE_QUERY + TABLE)
    int deleteAll();

    @Select(GET_QUERY + TABLE)
    List<Credential> getAllCredential();
}
