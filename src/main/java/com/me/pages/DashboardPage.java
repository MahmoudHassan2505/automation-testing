package com.me.pages;

import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage{
    public DashboardPage(WebDriver driver){
        super(driver);
    }

    public String getUrl(){
        return driver.getCurrentUrl();
    }
}
