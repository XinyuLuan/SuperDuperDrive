package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper extends IMapper{
    static final String GET_QUERY = "SELECT * FROM ";
    static final String INSERT_QUERY = "INSERT INTO ";
    static final String DELETE_QUERY = "DELETE FROM ";
    static final String TABLE = "FILES ";
    static final String WHERE_CONDITION = "WHERE ";

    @Select(GET_QUERY + TABLE + WHERE_CONDITION + "filename = #{fileName}")
    File getFile(String filename);

    @Insert(INSERT_QUERY + TABLE + "(filename, contenttype, filesize, userid, filedata) " +
            "VALUES(#{fileName}, #{contentType}, #{filesize}, #{userId}, #{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(File o);

    @Delete(DELETE_QUERY + TABLE + WHERE_CONDITION + " fileId = #{fileId}")
    int delete(Integer fileId);

    @Delete(DELETE_QUERY + TABLE)
    int deleteAll();

    @Select(GET_QUERY + TABLE)
    List<File> getAll();

    @Select(GET_QUERY + TABLE + WHERE_CONDITION + " fileId = #{fileId}")
    File getItemById(Integer fileId);
}
