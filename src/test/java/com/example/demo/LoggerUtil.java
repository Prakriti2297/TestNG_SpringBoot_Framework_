/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author PRAKRITI
 */
public class LoggerUtil {
private static final Logger logger = LoggerFactory.getLogger(LoggerUtil.class);

    public static void info(String message) {
        logger.info(message);
    }

    public static void error(String message, Throwable t) {
        logger.error(message, t);
    }

    public static void debug(String message) {
        logger.debug(message);
    }

}
