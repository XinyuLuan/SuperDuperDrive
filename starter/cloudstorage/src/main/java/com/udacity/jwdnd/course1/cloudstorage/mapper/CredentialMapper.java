package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper extends MapperInterface {
    static final String GET_QUERY = "SELECT * FROM ";
    static final String INSERT_QUERY = "INSERT INTO ";
    static final String DELETE_QUERY = "DELETE FROM ";
    static final String TABLE = "CREDENTIALS ";
    static final String WHERE_CONDITION = "WHERE ";
    static final String UPDATE_QUERY = "UPDATE CREDENTIALS SET ";
    static final String SELECTKEY = "SELECT key FROM CREDENTIALS WHERE ";

    @Select(GET_QUERY + TABLE + WHERE_CONDITION + "userid = #{userId}")
    List<Credential> getCredentialById(Integer userId);

    @Insert(INSERT_QUERY + TABLE + "(url, username, key, password, userid) " +
            "VALUES(#{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insert(Credential credential);

    @Delete(DELETE_QUERY + TABLE + WHERE_CONDITION + " credentialid = #{credentialId}")
    int delete(Integer credentialId);

    @Delete(DELETE_QUERY + TABLE)
    int deleteAll();

    @Select(GET_QUERY + TABLE)
    List<Credential> getAllCredential();

    @Update(UPDATE_QUERY +
            "url = #{url}, " +
            "username = #{username}, " +
            "password = #{password} " +
            "WHERE credentialid = #{credentialId}")
    int update(Object object);

    @Select(SELECTKEY + " credentialid= #{credentialId}")
    String getKey(int credetnialId);
}
