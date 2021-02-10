package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {
    @Autowired
    private final UserMapper userMapper;
    @Autowired
    private final CredentialMapper credentialMapper;

    public CredentialService(UserMapper userMapper, CredentialMapper credentialMapper) {
        this.userMapper = userMapper;
        this.credentialMapper = credentialMapper;
    }

    public List<Credential> getCredential(Integer userId){
        return credentialMapper.getCredentialById(userId);
    }

    public int insert(Credential credential){
        return credentialMapper.insert(credential);
    }

    public int delete(Integer creadentialId){
        return credentialMapper.delete(creadentialId);
    }

    public int deleteAll(){
        return credentialMapper.deleteAll();
    }

    public int update(Credential credential){
        return credentialMapper.update(credential);
    }
}
