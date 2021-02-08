package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper extends IMapper{
    static final String GET_QUERY = "SELECT * FROM ";
    static final String INSERT_QUERY = "INSERT INTO ";
    static final String DELETE_QUERY = "DELETE FROM ";
    static final String TABLE = "NOTES ";
    static final String WHERE_CONDITION = "WHERE ";

    String insertNoteSql = "INSERT INTO NOTES (notetitle, notedescription, userid) VALUES(#{noteTitle}, #{noteDescription}, #{userId})";


    @Select(GET_QUERY + TABLE + WHERE_CONDITION + "notetitle = #{noteTitle}")
    Note getNote(String notetitle);

    @Override
//    @Insert(INSERT_QUERY + TABLE + "(notetitle, notedescription, userid) " +
//            "VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Insert(insertNoteSql)
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insert(Object o);

    @Delete(DELETE_QUERY + TABLE + WHERE_CONDITION + " noteid = #{noteId}")
    int delete(Integer noteId);

    @Delete(DELETE_QUERY + TABLE)
    int deleteAll();

    @Select(GET_QUERY + TABLE)
    List<Note> getAllNotes();
}
