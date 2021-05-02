package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.page.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.page.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.page.SignupPage;
import com.udacity.jwdnd.course1.cloudstorage.utils.TestConstant;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.Locale;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SignupAndLogoutTest {

    final Integer SLEEPTIME = 1000;


    @LocalServerPort
    private int port;

    private WebDriver driver;

    public static String BASEURL;

    @BeforeAll
    static void beforeAll() {
        Locale.setDefault(new Locale("en","US"));
        WebDriverManager.chromedriver().setup();

    }

    @BeforeEach
    public void beforeEach() {
        this.driver = new ChromeDriver();
        BASEURL = TestConstant.LOCALHOST + port;
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }
    @AfterAll
    static void aferAll(){

    }

    @Test
    @Order(1)
    public void testLoginToSignup() throws InterruptedException{
        driver.get(SignupAndLogoutTest.BASEURL + TestConstant.LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.signUp(driver);
        Thread.sleep(SLEEPTIME);
        SignupPage signupPage = new SignupPage(driver);
        signupPage.signup("j", "j", "j", "j");
//        Assertions.assertTrue(signupPage.signupRun(driver));
        Assertions.assertFalse(signupPage.signupRun(driver));
    }

    @Test
    @Order(2)
    public void testLoginToSignup2() throws InterruptedException{
        driver.get(SignupAndLogoutTest.BASEURL + TestConstant.LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.signUp(driver);
        Thread.sleep(SLEEPTIME);
        SignupPage signupPage = new SignupPage(driver);
        signupPage.signup("j", "j", "j", "j");
        Assertions.assertFalse(signupPage.signupRun(driver));
    }

    @Test
    @Order(3)
    public void testLoginWithCorrectUsernameAndPassword() throws InterruptedException{
        driver.get(SignupAndLogoutTest.BASEURL + TestConstant.LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver);
        Thread.sleep(SLEEPTIME);
        loginPage.loginWith("j", "j");
        Assertions.assertTrue(loginPage.loginRun(driver));
    }

    @Test
    @Order(4)
    public void testLoginWithIncorrectUsernameAndPassword() throws InterruptedException{
        driver.get(SignupAndLogoutTest.BASEURL + TestConstant.LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver);
        Thread.sleep(SLEEPTIME);
        loginPage.loginWith("j", "i");
        Assertions.assertFalse(loginPage.loginRun(driver));
    }

    @Test
    @Order(5)
    public void testHomePageNotAccessibleWithoutLogin() throws InterruptedException{
        driver.get(SignupAndLogoutTest.BASEURL + TestConstant.HOME_URL);

        Thread.sleep(SLEEPTIME);
        Assertions.assertEquals(SignupAndLogoutTest.BASEURL + TestConstant.LOGIN_URL, driver.getCurrentUrl());
    }

    @Test
    @Order(6)
    public void testLogout() throws  InterruptedException{
        driver.get(SignupAndLogoutTest.BASEURL + TestConstant.SIGNUP_URL);
        SignupPage signupPage = new SignupPage(driver);
        signupPage.signup("first", "last", "username", "password");

        Thread.sleep(SLEEPTIME);
        driver.get(SignupAndLogoutTest.BASEURL + TestConstant.LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWith("username", "password");
        Thread.sleep(SLEEPTIME);

        driver.get(SignupAndLogoutTest.BASEURL + TestConstant.HOME_URL);
        Assertions.assertEquals(SignupAndLogoutTest.BASEURL + TestConstant.HOME_URL, driver.getCurrentUrl());
        Thread.sleep(SLEEPTIME);

        HomePage homePage = new HomePage(driver);
        homePage.logout();
        Thread.sleep(SLEEPTIME);

        driver.get(SignupAndLogoutTest.BASEURL + TestConstant.HOME_URL);
        Assertions.assertEquals(SignupAndLogoutTest.BASEURL + TestConstant.LOGIN_URL, driver.getCurrentUrl());
    }
}
