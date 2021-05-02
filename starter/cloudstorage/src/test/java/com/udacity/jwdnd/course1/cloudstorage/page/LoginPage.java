package com.udacity.jwdnd.course1.cloudstorage.page;

import com.udacity.jwdnd.course1.cloudstorage.utils.TestConstant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    /**
     * find all the elements in the html page so that I can control them
     * Remember to set ids to all of the components we need
     */
    @FindBy(id="signUpClick")
    private WebElement signUpClickLink;

    @FindBy(id="inputUsername")
    private WebElement usernameInput;

    @FindBy(id="inputPassword")
    private WebElement passwordInput;

    @FindBy(id="loginBtn")
    private WebElement loginBtn;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void signUp(WebDriver driver){
        signUpClickLink.click();
    }

    public void loginWith(String userName, String password){
        this.usernameInput.sendKeys(userName);
        this.passwordInput.sendKeys(password);
        this.loginBtn.click();
    }

    public boolean loginRun(WebDriver driver) throws InterruptedException{
        WebDriverWait wait = new WebDriverWait(driver, 5);

        WebElement loginErrorTxt = null;
        try {
            loginErrorTxt = wait.until(webDriver -> webDriver.findElement(By.id("loginError")));
        }catch(Exception e){ //find out what is exception...
            //doing if catch occured ... use log to print error statement.
        }
        return loginErrorTxt == null;
    }

}
