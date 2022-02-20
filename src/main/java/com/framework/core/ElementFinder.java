package com.framework.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementFinder {

    WebDriver driver;

    ElementFinder(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement findElementBy(String by, String value) {
        WebElement element = null;
        if (by.equalsIgnoreCase("xpath")) {
            element = driver.findElement(By.xpath(value));
        } else if (by.equalsIgnoreCase("id")) {
            element = driver.findElement(By.id(value));
        } else if (by.equalsIgnoreCase("name")) {
            element = driver.findElement(By.name(value));
        } else if (by.equalsIgnoreCase("link")) {
            element = driver.findElement(By.linkText(value));
        } else if (by.equalsIgnoreCase("partialLink")) {
            element = driver.findElement(By.partialLinkText(value));

        } else if (by.equalsIgnoreCase("css")) {
            element = driver.findElement(By.cssSelector(value));

        } else if (by.equalsIgnoreCase("className")) {
            element = driver.findElement(By.className(value));
        } else if (by.equalsIgnoreCase("tag")) {
            element = driver.findElement(By.tagName(value));
        } else if (by.isEmpty()) {
            element = driver.findElement(By.xpath(value));
        }

        return element;
    }
}
