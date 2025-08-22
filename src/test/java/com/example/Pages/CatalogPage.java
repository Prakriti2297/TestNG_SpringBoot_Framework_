/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author PRAKRITI
 */



@Component
@Scope("prototype")  
public class CatalogPage extends BasePage {
    
     @Autowired
    public CatalogPage(WebDriver driver) {
        super(driver);
    }
 private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

     @FindBy(xpath="//div[@id='BackLink']//a")
    private WebElement HomePageLink ;

    public String clickFishProduct(String fishName) {
        By fishProduct = By.xpath("//td[text()='" + fishName + "']/..//td//a");
        String productId = driver.findElement(fishProduct).getText();
        driver.findElement(fishProduct).click();
        System.out.println("Clicked on Fish Product: " + fishName);

        String currentUrl = driver.getCurrentUrl();
        if(currentUrl.contains(productId)) {
            System.out.println("Fish Product page opened successfully for: " + fishName);
        } else {
            System.out.println("Failed to open Fish Product page for: " + fishName);
        }
        return productId;
    }

    public void navigateToHomePage() {
        HomePageLink.click();
        String home_title=driver.getTitle();
        logger.info("Navigated back to Home Page"+ home_title);
    }
}