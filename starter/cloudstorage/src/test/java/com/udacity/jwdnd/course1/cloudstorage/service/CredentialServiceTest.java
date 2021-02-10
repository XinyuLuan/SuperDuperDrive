package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import com.udacity.jwdnd.course1.cloudstorage.utils.TestConstant;
import org.h2.command.ddl.CreateAggregate;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CredentialServiceTest {

    private static Credential testCredential;
    private static User testUser;

    @Autowired
    private CredentialService credentialService;
    @Autowired
    private CredentialMapper credentialMapper;
    @Autowired
    private UserService userService;

    @BeforeAll
    static void beforeAll(){

    }

    @BeforeEach
    public void beforeEach(){
        testUser = TestConstant.getUser();
        userService.CreateUser(testUser);
        testUser = userService.getUser(testUser.getUsername());
        testCredential = TestConstant.getCredential(testUser.getUserid());
    }

    @AfterEach
    public void afterEach(){
        credentialService.deleteAll();
        userService.deleteAll();
    }

    @Test
    public void getCredentialTest(){
        int expectedResult = 1;
        credentialMapper.insert(testCredential);
        List<Credential> credentials = credentialService.getCredential(testCredential.getUserId());
        Assertions.assertEquals(expectedResult, credentials.size());
    }

    @Test
    public void insertTest(){
        int expectedResult = 3;
        for(int i = 0; i < expectedResult; i++){
            credentialService.insert(testCredential);
        }
        List<Credential> credentials = credentialMapper.getCredentialById(testCredential.getUserId());
        Assertions.assertEquals(expectedResult, credentials.size());
    }

    @Test
    public void deleteTest(){
        int expectedResult = 2;
        for(int i = 0; i < expectedResult + 1; i++){
            credentialMapper.insert(testCredential);
        }
        List<Credential> credentials = credentialMapper.getCredentialById(testCredential.getUserId());
        credentialService.delete(credentials.get(0).getCredentialId());
        credentials = credentialMapper.getCredentialById(testCredential.getUserId());
        Assertions.assertEquals(expectedResult, credentials.size());
    }

    @Test
    public void deleteAllTest(){
        int expectedResult = 3;
        for(int i = 0; i < expectedResult; i++){
            credentialMapper.insert(testCredential);
        }
        int result = credentialService.deleteAll();
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void testUpdateCredential(){
        int expectingResult = 1;
        credentialMapper.insert(testCredential);

        List<Credential> credentials = credentialMapper.getCredentialById(testUser.getUserid());
        int result = credentialService.update(
                new Credential(credentials.get(0).getCredentialId(),
                        "NEW URL",
                        "NEW KEY",
                        "NEW PASSWORD",
                        credentials.get(0).getUserId()));
        Assertions.assertEquals(expectingResult, result);
    }


}
