package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {
    @Autowired
    private final UserMapper userMapper;
    @Autowired
    private final CredentialMapper credentialMapper;
    @Autowired
    private EncryptionService encryptionService;

    public CredentialService(UserMapper userMapper, CredentialMapper credentialMapper) {
        this.userMapper = userMapper;
        this.credentialMapper = credentialMapper;
    }

    public List<Credential> getCredential(Integer userId){
        return credentialMapper.getCredentialById(userId);
    }

    public int insert(Credential credential){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String password = credential.getPassword();
        String encyptpassword = this.encryptionService.encryptValue(password, encodedSalt);
        credential.setKey(encodedSalt);
        credential.setPassword(encyptpassword);
        return this.credentialMapper.insert(credential);
    }

    public int delete(Integer creadentialId){
        return credentialMapper.delete(creadentialId);
    }

    public int deleteAll(){
        return credentialMapper.deleteAll();
    }

    public int update(Credential credential){
        if(credential.getKey()==null || credential.getKey().equals("")){
            String key = this.credentialMapper.getKey(credential.getCredentialId());
            credential.setKey(key);
            credential.setPassword(encryptionService.encryptValue(credential.getPassword(),key));
        }
        return this.credentialMapper.update(credential);
    }
}
