package com.me.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage{

    private final By username = By.name("username");

    private final By password = By.name("password");
    private final By login = By.cssSelector("[type='submit']");

    private final By errorMsg = By.cssSelector("p.oxd-text.oxd-text--p.oxd-alert-content-text");

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public LoginPage navigateUrl(String url) {
        driver.get(url);
        return this;
    }

    public LoginPage enterUsername(String username) {
        waitUntilVisibilityOfElement(this.username);
        driver.findElement(this.username).sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        waitUntilVisibilityOfElement(this.password);
        driver.findElement(this.password).sendKeys(password);
        return this;
    }

    public WebElement getErrorMsg() {
        waitUntilVisibilityOfElement(this.errorMsg);
        return driver.findElement(this.errorMsg);
    }

    public DashboardPage clickOnLogin() {
        driver.findElement(login).click();
        return new DashboardPage(driver);
    }

}
