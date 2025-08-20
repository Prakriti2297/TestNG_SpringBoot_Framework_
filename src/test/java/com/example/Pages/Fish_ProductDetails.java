/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.Pages;

import java.util.List;

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
import org.testng.Assert;



/**
 *
 * @author PRAKRITI
 */
@Component
@Scope("prototype")  
public class Fish_ProductDetails extends BasePage {
    
     @Autowired
    public Fish_ProductDetails(WebDriver driver) {
       
        super(driver);
    }
    
     private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

    @FindBy(xpath="//table//tbody//tr//td[2]")
    private List<WebElement>  product_row ;

    public void verifyProductDetails(String productId) {
        List<WebElement> rows = product_row;
        for (WebElement row : rows) {
            String rowText = row.getText();
            if (rowText.contains(productId)) {
                System.out.println("Product details verified for ID: " + productId);
                Assert.assertTrue(rowText.contains(productId), "Product ID found in row: " + rowText);
            }
        }
    }

    public String getPrice(String itemId) {
        By priceLocator = By.xpath("//a[text()='" + itemId + "']//ancestor::tr//td[4]");
        String price = driver.findElement(priceLocator).getText();
        System.out.println("Product Price: " + price);
        return price;
    }

    public void addToCart(String itemId) {
        By addTocart = By.xpath("//a[text()='" + itemId + "']//ancestor::tr//td[5]//a[@class='Button']");
        driver.findElement(addTocart).click();
        System.out.println("Added product to cart: " + itemId);
    }
}