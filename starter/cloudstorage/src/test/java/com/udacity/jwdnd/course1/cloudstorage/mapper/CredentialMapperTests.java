package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.utils.TestConstant;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;
import java.util.Locale;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CredentialMapperTests {
    @LocalServerPort
    private int port;

    private static Logger logger = LoggerFactory.getLogger(CredentialMapperTests.class);
    private static Credential testCredential;
    private static User testUser;

    @Autowired
    CredentialMapper credentialMapper;
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
        testCredential = TestConstant.getCredential(user.getUserid());
    }

    @AfterEach
    public void afterEach() {
        credentialMapper.deleteAll();
        userMapper.deleteAll();
    }

    @Test
    public void testInsertCredential(){
        int expectingResult = 1;
        int result = credentialMapper.insert(testCredential);

        Assertions.assertEquals(expectingResult, result);
    }

    @Test
    public void testGetCredentials(){

        int expectingResult = 1;
        int result = credentialMapper.insert(testCredential);
        Credential credential = credentialMapper.getCredential("username");

        Assertions.assertEquals(expectingResult, credential.getCredentialId());
    }

    @Test
    public void testDeleteCredential(){
        int expectResult = 2;

        for(int i = 0; i < expectResult + 1; i++){
            int addRow = credentialMapper.insert(testCredential);
        }
        List<Credential> credentials = credentialMapper.getAllCredential();
        int deleteRow = credentialMapper.delete(credentials.get(0).getCredentialId());

        List<Credential> resultCredentials = credentialMapper.getAllCredential();
        Assertions.assertEquals(expectResult, resultCredentials.size());
    }

    @Test
    public void testGetAllCredentials(){
        int expectResult = 5;
        for(int i = 0; i < expectResult; i++){
            credentialMapper.insert(testCredential);
        }

        List<Credential> resultCredentials = credentialMapper.getAllCredential();
        Assertions.assertEquals(expectResult, resultCredentials.size());
    }
}
