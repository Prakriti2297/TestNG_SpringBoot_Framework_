/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.Pages.LoginPage;

/**
 *
 * @author PRAKRITI
 */

 @SpringBootApplication
public class SpringBootMain {
    public static void main(String[] args) {
     ApplicationContext context =org.springframework.boot.SpringApplication.run(SpringBootMain.class, args);
        // You can use the context to retrieve beans or perform other operatio
        LoginPage loginPage = context.getBean(LoginPage.class);
        loginPage.navigateToLoginPage();
        System.out.println("Application started successfully and navigated to login page.");
    }

}
