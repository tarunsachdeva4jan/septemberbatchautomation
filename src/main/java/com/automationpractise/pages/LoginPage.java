package com.automationpractise.pages;

import com.framework.core.BaseUtils;
import com.framework.core.EnvPropertiesLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BaseUtils {

    Wait wait;


    @FindBy(id = "email")
    WebElement tbx_EmailAddress;

    @FindBy(id = "passwd")
    WebElement tbx_Password;

    @FindBy(id = "SubmitLogin")
    WebElement btn_signIn;

    public LoginPage() {
        wait = new WebDriverWait(baseDriver.get(), EnvPropertiesLoader.standardWait);
        PageFactory.initElements(baseDriver.get(), this);
    }

    public void loginToApplication(String username, String password) {
        wait.until(ExpectedConditions.visibilityOf(tbx_EmailAddress));
        tbx_EmailAddress.sendKeys(username);
        tbx_Password.sendKeys(password);
        btn_signIn.click();
    }
}
