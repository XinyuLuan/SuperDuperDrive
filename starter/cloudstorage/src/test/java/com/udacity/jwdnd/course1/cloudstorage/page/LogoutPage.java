package com.udacity.jwdnd.course1.cloudstorage.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogoutPage {
    @FindBy(id="signUpClick")
    private WebElement signUpClickLink;

    @FindBy(id="inputUsername")
    private WebElement usernameInput;

    @FindBy(id="inputPassword")
    private WebElement passwordInput;

    @FindBy(id="loginBtn")
    private WebElement loginBtn;

    public LogoutPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
