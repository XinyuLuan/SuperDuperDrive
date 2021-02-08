package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.utils.TestConstant;
import net.bytebuddy.implementation.bytecode.constant.TextConstant;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.expression.spel.ast.Assign;

import java.util.List;
import java.util.Locale;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoteMapperTests {

    @LocalServerPort
    private int port;

    private static Logger logger = LoggerFactory.getLogger(NoteMapperTests.class);
    private static Note testNote;
    private static User testUser;

    @Autowired
    NoteMapper noteMapper;
    @Autowired
    UserMapper userMapper;

    @BeforeAll
    static void beforeAll() {
        Locale.setDefault(new Locale("en","US"));
    }

    @BeforeEach
    public void beforeEach() {
        testUser = TestConstant.getUser();
        userMapper.insert(testUser);
        User user = userMapper.getUser(testUser.getUsername());
        testNote = TestConstant.getNote(user.getUserid());
    }

    @AfterEach
    public void afterEach() {
        noteMapper.deleteAll();
        userMapper.deleteAll();
    }

    @Test
    public void testInsertNote(){
        int expectingResult = 1;
        int result = noteMapper.insert(testNote);

        Assertions.assertEquals(expectingResult, result);
    }

    @Test
    public void testGetNotes(){
//        for(int i = 0; i < 3; i++) {
//            userMapper.insert(testUser);
//        }
//
//        for(int i = 0; i < 3; i++) {
//            testNote = TestConstant.getNote(userMapper.getUserByID(i + 1).getUserid());
//            noteMapper.insert(testNote);
//        }
        int expectingResult = 1;
        int result = noteMapper.insert(testNote);
        Note note = noteMapper.getNote("title");

        Assertions.assertEquals(expectingResult, note.getNoteId());
    }

    @Test
    public void testDeleteNote(){
        int expectResult = 2;

        for(int i = 0; i < expectResult + 1; i++){
            int addRow = noteMapper.insert(testNote);
        }
        List<Note> notes = noteMapper.getAllNotes();
        int deleteRow = noteMapper.delete(notes.get(0).getNoteId());

        List<Note> resultNotes = noteMapper.getAllNotes();
        Assertions.assertEquals(expectResult, resultNotes.size());
    }

    @Test
    public void testGetAllNotes(){
        int expectResult = 5;
        for(int i = 0; i < expectResult; i++){
            noteMapper.insert(testNote);
        }

        List<Note> resultNotes = noteMapper.getAllNotes();
        Assertions.assertEquals(expectResult, resultNotes.size());
    }

}
