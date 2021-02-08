package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import com.udacity.jwdnd.course1.cloudstorage.utils.TestConstant;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceTest {
    @LocalServerPort
    private int port;
    private static User testUser;
    private static int count = 0;
    private static final Logger log = LoggerFactory.getLogger(com.udacity.jwdnd.course1.cloudstorage.service.UserServiceTest.class);

    @Autowired
    private UserService service;
    @Autowired
    private UserMapper mapper;

    @BeforeAll
    static void beforeAll() {

    }

    @BeforeEach
    public void beforeEach() {
        testUser = TestConstant.getUser();
    }

    @AfterEach
    public void afterEach() {
        mapper.deleteAll();
    }

    @Test
    public void isUsernameAvailableTest(){
        int expectResult = 1;
        int result = mapper.insert(testUser);
        Assertions.assertEquals(expectResult, result);
        boolean checkResult = service.isUsernameAvailable(testUser.getUsername());
        Assertions.assertFalse(checkResult);
    }

    @Test
    public void CreateUserTest(){
        int expectedResult = 3;
        for(int i = 0; i < expectedResult; i++){
            service.CreateUser(testUser);
        }
        List<User> users = mapper.getAllUser();
        Assertions.assertEquals(expectedResult, users.size());
    }

    @Test
    public void deleteUserTest(){
        int expectResult = 2;
        for(int i = 0; i < expectResult + 1; i++){
            mapper.insert(testUser);
        }
        service.deleteUser(testUser);
        List<User> users = mapper.getAllUser();
        Assertions.assertEquals(expectResult, users.size());
    }

    @Test
    public void deleteAllTest(){
        int expectResult = 3;
        for(int i = 0; i < expectResult; i++){
            mapper.insert(testUser);
        }
        int result = service.deleteAll();

        Assertions.assertEquals(expectResult, result);
    }

    @Test
    public void getAllTest(){
        int expectResult = 3;
        for(int i = 0; i < expectResult; i++){
            mapper.insert(testUser);
        }
        List<User> users = service.getAllUser();

        Assertions.assertEquals(expectResult, users.size());
    }
}
