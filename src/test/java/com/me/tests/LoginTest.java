package com.me.tests;

import com.me.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest {
    private WebDriver chromeDriver;

    @BeforeClass
    public void setupDriver(){
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
    }

    @Test
    public void successfulLogin(){

        LoginPage loginPage = new LoginPage(chromeDriver);

        String actualURL = loginPage
                .navigateUrl("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login")
                .enterUsername("Admin")
                .enterPassword("admin123")
                .clickOnLogin().getUrl();

        String expectedURL = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";

        Assert.assertEquals(actualURL,expectedURL);

    }

    @Test()
    public void unsuccessfulLogin() throws InterruptedException {
        LoginPage loginPage = new LoginPage(chromeDriver);

        loginPage
                .navigateUrl("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login")
                .enterUsername("Admins")
                .enterPassword("admin123")
                .clickOnLogin();

        String expectedMsg = "Invalid credentials";
        String actualMsg = loginPage.getErrorMsg().getText();

        Assert.assertEquals(actualMsg,expectedMsg);

    }

    @AfterMethod
    public void clearDriverData(){
        chromeDriver.manage().deleteAllCookies();
    }

    @AfterClass
    public void destroy(){
        chromeDriver.close();
    }
}
