package com.me.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage{
    public WebDriver driver;
    private WebDriverWait wait;

    public BasePage(WebDriver driver){
        this.driver=driver;
    }

    public WebDriver getDriver(){
        return this.driver;
    }

    public void waitUntilVisibilityOfElement(By by){
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
}
