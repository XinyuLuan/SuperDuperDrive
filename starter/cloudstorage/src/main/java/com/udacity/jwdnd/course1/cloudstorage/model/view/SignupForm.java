package com.udacity.jwdnd.course1.cloudstorage.model.view;

public class SignupForm {
    private String firstName;
    private String lastName;
    private String pwd;
    private String username;

    public SignupForm(String firstName, String lastName, String pwd, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pwd = pwd;
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString(){
        return "SignupForm: [firstName=" + firstName +", lastName=" + lastName + ", username=" + username + ", pwd=" + pwd + "]";
    }
}
