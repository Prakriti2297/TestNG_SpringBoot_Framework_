package com.example.demo;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import io.github.bonigarcia.wdm.WebDriverManager;
import jakarta.annotation.PreDestroy;



@Configuration
//tells spring this is a configuration class and it can be used to create beans
public class DriverConfig {
     @Autowired
    private AppConfig appConfig;

    @Bean
 // Each test method gets a fresh WebDriver
    public WebDriver webDriver() {
        if (appConfig.getBrowser().equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        } else if (appConfig.getBrowser().equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Browser not supported: " + appConfig.getBrowser());
        }
    }
}