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

public class LandingPage extends BaseUtils {
    Wait wait;


    @FindBy(xpath = "//a[contains(text(),'Sign in')]")
    WebElement btn_signIn;

    @FindBy(id = "SubmitCreate")
    WebElement btn_CreateAnAccount;

    @FindBy(id = "email_create")
    WebElement tbx_EmailAddress;

    public LandingPage() {
        wait = new WebDriverWait(baseDriver.get(), EnvPropertiesLoader.standardWait);
        PageFactory.initElements(baseDriver.get(), this);
    }


    public void openAutomationPractiseWebsite() {
        launchUrl(EnvPropertiesLoader.applicationUrl);
    }


    public LoginPage navigateToLogin() {
        try {
            btn_signIn.click();
            ExtentTestReporter.logPassStep("Sign IN button clicked");
        } catch (Exception e) {
            ExtentTestReporter.logFailStep("Unable to click on Sign In button");

        }
        return new LoginPage();
    }


    public RegistrationPage navigateToRegistration(String emailAddress) {
        try {
            tbx_EmailAddress.sendKeys(emailAddress);
            btn_CreateAnAccount.click();
            ExtentTestReporter.logPassStep("Successfully Navigated to the registration Page");

        } catch (Exception e) {
            ExtentTestReporter.logFailStep("Unable to navigate to registration page");
        }
        return new RegistrationPage();
    }

}
