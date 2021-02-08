package com.udacity.jwdnd.course1.cloudstorage.mapper;


import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.utils.TestConstant;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserMapperTests {

    private static User testUser;
    private static Logger logger = LoggerFactory.getLogger(UserMapperTests.class);

    @Autowired
    UserMapper userMapper;

    @BeforeAll
    static void beforeAll() {

    }

    @BeforeEach
    public void beforeEach() {
        testUser = TestConstant.getUser();
    }

    @AfterEach
    public void afterEach() {
        userMapper.deleteAll();
    }


    @Test
    @Order(1)
    public void testInsertUser() {
        int expectingResult = 1;
        int success = userMapper.insert(testUser);

        List<User> users = userMapper.getAllUser();
        for(User user: users){
            logger.info("user in zero test " + user.toString());
        }
        Assertions.assertEquals(expectingResult, success);
    }

    @Test
    @Order(2)
    public void testInsertThree(){
        int expectResult = 3;
        int successes = 0;
        for(int i = 0; i < 3; i++){
            successes += userMapper.insert(testUser);

//            logger.info(("user in first test" + String.valueOf(testUser.getUserid())));
        }
        List<User> users = userMapper.getAllUser();
        for(User user: users){
            logger.info("user in first test " + user.toString());
        }
        Assertions.assertEquals(expectResult, successes);
    }

    @Test
    @Order(3)
    public void testGetUser(){

        int expectResult = 3;
        int successes = 0;
        for(int i = 0; i < 3; i++){
            successes += userMapper.insert(testUser);
        }

        int deleteResult = userMapper.delete(2);

        List<User> users = userMapper.getAllUser();
        for(User user: users){
            logger.info("user in second test " + user.toString());
        }

        Assertions.assertEquals(expectResult, successes);
    }

    @Test
    public void testGetUserByID(){
        int expectingResult = 2;

        for(int i = 0; i < 3; i++){
            userMapper.insert(testUser);
        }
        User user = userMapper.getUserByID(2);

        Assertions.assertEquals(expectingResult, user.getUserid());
    }
}
