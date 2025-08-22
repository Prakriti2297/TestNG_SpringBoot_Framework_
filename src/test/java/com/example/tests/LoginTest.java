/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.tests;



import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.beust.ah.A;
import com.example.Pages.CatalogPage;
import com.example.Pages.ProductDetailsPage;
import com.example.Pages.LoginPage;
import com.example.demo.AppConfig;
import com.example.demo.ExcelDataLoader;
import com.fasterxml.jackson.databind.JsonSerializable.Base;
/**
 *
 * @author PRAKRITI
 */

@SpringBootTest
public class LoginTest extends AbstractTestNGSpringContextTests {
   private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);
  @Autowired
    private LoginPage loginPage;

    @Autowired
    private CatalogPage catalog;

    @Autowired
    private ProductDetailsPage productDetails;
   
    

    @Autowired
    private WebDriver driver;
// Using AppConfig to get the base URL
    @Autowired
    private ApplicationContext context;

  



    @DataProvider(name = "FishData")
    public Object[][] getFishData() {
        return toDataProvider(ExcelDataLoader.loadData("Product_data.xlsx", "Product", "Fish"));
    }
    @DataProvider(name = "DogData")
    public Object[][] getDogData() {
        return toDataProvider(ExcelDataLoader.loadData("Product_data.xlsx", "Product", "Dogs"));
    }
    private Object[][] toDataProvider(List<Map<String, String>> list) {
        Object[][] result = new Object[list.size()][1];
        for (int i = 0; i < list.size(); i++) {
            result[i][0] = list.get(i);
        }
        return result;
    }
    @Test(priority = 1,dataProvider = "FishData",groups = {"smoke","regression"})
    public void addFishToCart(Map<String, String> data) throws InterruptedException {
        // Navigate to the login page
           loginPage.navigateToLoginPage();
            loginPage.accessQuickLinks(data.get("LinkName"));
            Thread.sleep(2000); // Wait for 2 seconds to see the page load
            logger.info("Fish page navigated successfully.");
            String product_id=catalog.clickFishProduct(data.get("ProductName"));
            productDetails.verifyProductDetails(product_id);
            String price = productDetails.getPrice(data.get("ItemId"));
            logger.info("Price of the product: " + price);
            productDetails.addToCart(data.get("ItemId"));
            logger.info("Product added to cart successfully.");
             productDetails.verifyCart(data.get("ItemId"),price);    
        
    }

      @Test(priority = 2,dataProvider = "FishData",groups = {"regression"})
    public void verifyReturnHomePage_fromCatalog(Map<String, String> data) throws InterruptedException {
           loginPage.navigateToLoginPage();
        loginPage.accessQuickLinks(data.get("LinkName"));
            Thread.sleep(2000); // Wait for 2 seconds to see the page load
            logger.info("Fish page navigated successfully.");
            catalog.navigateToHomePage();
            logger.info("Returned to home page successfully.");

    }
     @Test(priority = 3,dataProvider = "DogData",groups = {"smoke","regression"})
    public void addDogsToCart(Map<String, String> data) throws InterruptedException {
        // Navigate to the login page
           loginPage.navigateToLoginPage();
            loginPage.accessQuickLinks(data.get("LinkName"));
            Thread.sleep(2000); // Wait for 2 seconds to see the page load
            logger.info(data.get("LinkName")+data.get("LinkName")+" page navigated successfully.");
            String product_id=catalog.clickFishProduct(data.get("ProductName"));
            productDetails.verifyProductDetails(product_id);
            String price = productDetails.getPrice(data.get("ItemId"));
            logger.info("Price of the product: " + price);
            productDetails.addToCart(data.get("ItemId"));
            logger.info("Product added to cart successfully.");
             productDetails.verifyCart(data.get("ItemId"),price);    
        
    }



  /*   @Test
    public void verifyLoginPage() {
        // This is a placeholder for the actual test logic
        System.out.println("Login test executed!");
        // You can add assertions here to verify that the login page is displayed correctly
        // For example, you can check if the title contains "Login" or if certain elements are present
    }*/
  
    
}
