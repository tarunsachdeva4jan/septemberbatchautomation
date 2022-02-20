package com.automationpractise.pages;

import com.framework.core.BaseUtils;
import com.framework.core.EnvPropertiesLoader;
import com.framework.core.ExtentTestReporter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage extends BaseUtils {

    Wait wait;

    @FindBy(id = "customer_firstname")
    WebElement tbx_FirstName;

    @FindBy(id = "customer_lastname")
    WebElement tbx_LastName;

    @FindBy(id = "passwd")
    WebElement tbx_Password;

    public RegistrationPage() {
        wait = new WebDriverWait(baseDriver.get(), EnvPropertiesLoader.standardWait);
        PageFactory.initElements(baseDriver.get(), this);
    }


    public void enterRegistrationDetails(String firstName, String lastName, String password) {
        try {
            wait.until(ExpectedConditions.visibilityOf(tbx_FirstName));
            tbx_FirstName.sendKeys(firstName);
            tbx_LastName.sendKeys(lastName);
            tbx_Password.sendKeys(password);
            ExtentTestReporter.logPassStep("Entered " + firstName + ", " + lastName + ", " + password);

        } catch (Exception e) {
            ExtentTestReporter.logFailStep("Unable to enter registration details");
        }
    }

}
