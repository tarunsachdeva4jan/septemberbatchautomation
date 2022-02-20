package com.framework.core;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class TestAsserts {

    public static void assertIfVisible(WebElement element) {
        Assert.assertTrue(element.isDisplayed());
    }

    public static void assertNotVisible(WebElement element) {
        Assert.assertFalse(element.isDisplayed());
    }

    public static void assertIfEquals(String value1, String value2) {
        Assert.assertEquals(value1, value2, "Values are not matching");
    }
}
