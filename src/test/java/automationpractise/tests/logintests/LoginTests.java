package automationpractise.tests.logintests;

import com.automationpractise.pages.LandingPage;
import com.automationpractise.pages.LoginPage;
import com.framework.core.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class LoginTests extends BaseTests {


    @Test(dataProvider = "JsonDataProvider", dataProviderClass = DataProviderUtils.class)
    @DataProviderArguments("validateLoginPositiveFlow=username,password")
    public void validateLoginPositiveFlow(String username, String password) throws InterruptedException {
        LandingPage landingPage = new LandingPage();
        landingPage.openAutomationPractiseWebsite();
        LoginPage loginPage = landingPage.navigateToLogin();
        loginPage.loginToApplication(username, password);
        Thread.sleep(20000);
    }


}
