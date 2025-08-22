/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.Pages;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
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
public class ProductDetailsPage extends BasePage {
    
     @Autowired
    public ProductDetailsPage(WebDriver driver) {
       
        super(driver);
    }
    
     private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

    @FindBy(xpath="//table//tbody//tr//td[2]")
    private List<WebElement>  product_row ;
     @FindBy(xpath="//table//tbody//tr//td[1]//a")
    private List<WebElement>  cart_row ;


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

    public void verifyCart(String itemid, String price) {
        // TODO Auto-generated method stub
        List<WebElement> rows = cart_row;
        boolean found = false;
        for(int i=1;i<=rows.size();i++) {
            String rowText = rows.get(i-1).getText();
            if (rowText.contains(itemid)) {
                found = true;
                logger.info("Product found in cart: " + itemid);
                By cart_quantity=  (By.xpath("//table//tbody//tr["+(i+1)+"]//td[5]//input"));
                String quantity = driver.findElement(cart_quantity).getDomAttribute("value");
                logger.info("Quantity in cart: " + quantity);
                String list_price=driver.findElement(By.xpath("//table//tbody//tr["+(i+1)+"]//td[6]")).getText();
                System.out.println("List Price in cart: " + list_price);
                String price_cleaned = price.replace("$", "").trim(); price_cleaned = price.replace("$", "").trim();
                Assert.assertEquals(price, list_price, "Price in cart does match expected price.");
                String total_cost=driver.findElement(By.xpath("//table//tbody//tr["+(i+1)+"]//td[7]")).getText();
                total_cost=total_cost.replace("$", "").trim();
                Double expected_total_cost = (Double.parseDouble(price_cleaned) * Double.parseDouble(quantity));
                DecimalFormat df = new DecimalFormat("#.00");
                String formatted_expected_total_cost = df.format(expected_total_cost);
                Assert.assertEquals(total_cost, formatted_expected_total_cost, "Total cost in cart does match expected total cost.");
                
                  break;
            }
        }
        Assert.assertTrue(found, "Product not found in cart: " + itemid);


        
    }
}