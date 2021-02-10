package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import com.udacity.jwdnd.course1.cloudstorage.utils.TestConstant;
import net.bytebuddy.build.ToStringPlugin;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoteServiceTest {

    private static Note testNote;
    private static User testUser;

    @Autowired
    private NoteService noteService;
    @Autowired
    private NoteMapper noteMapper;
    @Autowired
    private UserService userService;

    @BeforeAll
    static void beforeAll() {

    }

    @BeforeEach
    public void beforeEach() {
        testUser = TestConstant.getUser();
        userService.CreateUser(testUser);
        testUser = userService.getUser(testUser.getUsername());
        testNote = TestConstant.getNote(testUser.getUserid());
    }

    @AfterEach
    public void afterEach(){
        noteService.deleteAll();
        userService.deleteAll();
    }

    @Test
    public void insertTest(){
        int expectingResult = 3;
        for(int i = 0; i < 3; i++){
            noteService.insert(testNote);
        }
        List<Note> notes = noteMapper.getAllNotes();
        Assertions.assertEquals(expectingResult, notes.size());
    }

    @Test
    public void getNoteTest(){
        int expectingResult = 1;
        noteMapper.insert(testNote);
        User user = userService.getUser(testUser.getUsername());
        List<Note> notes = noteService.getNote(user.getUserid());
        Assertions.assertEquals(expectingResult, notes.size());
    }
    @Test
    public void getNoteAllTest(){
        int expectingResult = 3;
        for(int i = 0; i < 3; i++){
            noteMapper.insert(testNote);
        }
        List<Note> notes = noteService.getAllNotes();
        Assertions.assertEquals(expectingResult, notes.size());
    }

    @Test
    public void deleteTest(){
        int expectingResult = 1;
        noteMapper.insert(testNote);
        int result = noteService.delete(testNote.getNoteId());
        Assertions.assertEquals(expectingResult, result);
//        List<Note> notes = noteMapper.getNote(user.getUserid());
//        Assertions.assertEquals(expectingResult, notes.size());
    }

    @Test
    public void deleteAllTest(){
        int expectingResult = 3;
        for(int i = 0; i < 3; i++){
            noteMapper.insert(testNote);
        }
        int result = noteService.deleteAll();
        Assertions.assertEquals(expectingResult, result);
    }

    @Test
    public void updateTest(){
        int expectingResult = 1;
        noteMapper.insert(testNote);

        List<Note> notes = noteMapper.getNote(testUser.getUserid());
        int result = noteService.update(new Note(notes.get(0).getNoteId(), "NOTE TITLE", "NOTEDESCRIPTION", notes.get(0).getUserId()));

        Assertions.assertEquals(expectingResult, result);
    }
}
