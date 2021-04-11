package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper extends MapperInterface {
    static final String GET_QUERY = "SELECT * FROM ";
    static final String INSERT_QUERY = "INSERT INTO ";
    static final String DELETE_QUERY = "DELETE FROM ";
    static final String TABLE = "FILES ";
    static final String WHERE_CONDITION = "WHERE ";
    static final String UPDATE_QUERY =  "UPDATE FILES SET ";


    @Select(GET_QUERY + TABLE + WHERE_CONDITION + "filename = #{fileName}")
    File getFile(String filename);

    @Insert(INSERT_QUERY + TABLE + "(filename, contenttype, filesize, userid, filedata) " +
            "VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(File o);

    @Select(GET_QUERY + TABLE + WHERE_CONDITION + "userId = #{userId} AND filename = #{filename}" )
    File hasDuplicateFiles(int userId, String filename);

    @Delete(DELETE_QUERY + TABLE + WHERE_CONDITION + " fileId = #{fileId}")
    int delete(Integer fileId);

    @Delete(DELETE_QUERY + TABLE)
    int deleteAll();

    @Select(GET_QUERY + TABLE)
    List<File> getAll();

    @Select(GET_QUERY + TABLE + WHERE_CONDITION + " fileId = #{fileId}")
    File getItemById(Integer fileId);

    @Override
    @Update(UPDATE_QUERY + "filename = #{filename}, " +
            "filedata = #{fileData}, " +
            "contenttype = #{contentType}, " +
            "filesize = #{fileSize} , " +
            "userid = #{userId} " +
            "WHERE fileId = #{fileId}")
    int update(Object object);
}
