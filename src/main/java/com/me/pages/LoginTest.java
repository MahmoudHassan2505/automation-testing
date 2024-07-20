package com.me.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class LoginTest {
    private WebDriver chromeDriver;

    @BeforeMethod
    public void setupDriver(){
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
    }

    @Test
    public void successfulLogin(){
        // fetching Page and validate Title
        chromeDriver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Assert.assertEquals(chromeDriver.getTitle(),"OrangeHRM");

        //login
        login("Admin","admin123");

        //Validate successfully login
        Assert.assertEquals(chromeDriver.getCurrentUrl(),"https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
    }

    @Test
    public void unsuccessfulLogin(){
        // fetching Page and validate Title
        chromeDriver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Assert.assertEquals(chromeDriver.getTitle(),"OrangeHRM");

        //login
        login("admin","me");

        //get Error Message
        WebDriverWait waitForError = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));
        WebElement errorMessage = waitForError.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("p.oxd-text.oxd-text--p.oxd-alert-content-text")
        ));


        //Validate Unsuccessfully login
        Assert.assertEquals(chromeDriver.getCurrentUrl(),"https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Assert.assertEquals(errorMessage.getText(),"Invalid credentials");
    }

    void login(String username, String password){
        //wait until elements load
        WebDriverWait wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']")));
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='password']")));
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));

        //Setting username and Password with correct credentials
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }

    @AfterMethod
    public void destroy(){
        chromeDriver.close();
    }
}
