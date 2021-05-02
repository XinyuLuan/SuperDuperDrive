package com.udacity.jwdnd.course1.cloudstorage.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SignupPage {

    private static final Logger log = LoggerFactory.getLogger(SignupPage.class);
    @FindBy(id = "inputFirstName")
    private WebElement firstNameInput;

    @FindBy(id = "inputLastName")
    private WebElement lastNameInput;

    @FindBy(id = "inputUsername")
    private WebElement usernameInput;

    @FindBy(id = "inputPassword")
    private WebElement passwordInput;

    @FindBy(id = "signupBtn")
    private WebElement signUpBtn;

    public SignupPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void signup(String firstName, String lastName, String username, String password) {
        this.firstNameInput.sendKeys(firstName);
        this.lastNameInput.sendKeys(lastName);
        this.usernameInput.sendKeys(username);
        this.passwordInput.sendKeys(password);
        signUpBtn.click();
    }

    public boolean signupRun(WebDriver driver) throws InterruptedException{

        WebDriverWait wait = new WebDriverWait(driver, 5);
//        WebElement signUpErrorTxt = null;
        String signupSuccessId = "signupSuccess";
        WebElement signupSuccessTxt = null;
        try {
            signupSuccessTxt = wait.until(webDriver -> webDriver.findElement(By.id(signupSuccessId)));
        }catch(Exception e){ //find out what is exception...
            //doing if catch occured ... use log to print error statement.
            log.info("no id " + signupSuccessId);

        }
        return signupSuccessTxt != null;
//        signupSuccessTxt = wait.until(webDriver -> webDriver.findElement(By.id("signupSuccess")));
//        return signupSuccessTxt != null;
    }
}
