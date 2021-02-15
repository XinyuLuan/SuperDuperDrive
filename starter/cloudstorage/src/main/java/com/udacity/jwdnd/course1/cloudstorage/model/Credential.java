package com.udacity.jwdnd.course1.cloudstorage.model;

import com.udacity.jwdnd.course1.cloudstorage.model.view.CredentialForm;

public class Credential {
    private int credentialId;
    private String url;
    private String username;
    private String key;
    private String password;
    private int userId;

    public Credential(CredentialForm credentialForm, Integer userId){
        if(credentialForm.getCredentialId() == null || credentialForm.getCredentialId().length() == 0){
            this.credentialId = 0;
        }
        else{
            this.credentialId = Integer.parseInt(credentialForm.getCredentialId());
        }
        this.url = credentialForm.getUrl();
        this.username = credentialForm.getUsername();
        this.password = credentialForm.getPassword();
        this.userId = userId;
    }

    public Credential(int credentialId, String url, String username, String key, String password, int userId) {
        this.credentialId = credentialId;
        this.url = url;
        this.username = username;
        this.key = key;
        this.password = password;
        this.userId = userId;
    }

    public Credential(String url, String username, String key, String password, int userId) {
        this.url = url;
        this.username = username;
        this.key = key;
        this.password = password;
        this.userId = userId;
    }

    public Credential(int credentialId, String url, String username, String password, int userId) {
        this.credentialId = credentialId;
        this.url = url;
        this.username = username;
        this.password = password;
        this.userId = userId;
    }

    public Credential(String url, String username, String password, int userId) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.userId = userId;
    }

    public int getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(int credentialId) {
        this.credentialId = credentialId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "\ncredentialId: " + credentialId + " url: " + url + "\n username " + username + " key " + key + "\n password " + password + " userId " + userId;
    }
}
