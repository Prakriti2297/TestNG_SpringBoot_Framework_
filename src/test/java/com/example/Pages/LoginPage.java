/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.demo.AppConfig;
/**
 *
 * @author PRAKRITI
 */

 @Component
@Scope("prototype")  
public class LoginPage extends BasePage {
    
    // Using AppConfig to get the base URL
    @Autowired
    private AppConfig appConfig;
    
   //private final WebDriver driver;
    private final AppConfig config;
 private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

    @Autowired
    public LoginPage (WebDriver driver,AppConfig config) {
        super(driver);
        
        this.config = config;
        logger.info("LoginPage initialized with driver and config");
    }

    @FindBy(xpath="//div[@id='QuickLinks']//a[contains(@href,'FISH')]")
    private WebElement Fish_QuickLink; 

    public void navigateToLoginPage() {
        System.out.println("Navigating to Login Page: " + config.getBaseUrl());
        driver.get(config.getBaseUrl());
        driver.manage().window().maximize();
        logger.info("Navigated to Login Page");
    }

    public void accessQuickLinks(String linkName) {
        switch (linkName.toUpperCase()) {
            case "FISH":
                Fish_QuickLink.click();
                logger.info("Clicked on Fish Quick Link");
                break;
            default:
                logger.warn("Quick link not found: " + linkName);
        }
    }
}