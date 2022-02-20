package automationpractise.tests.registrationtests;

import com.automationpracitise.datagenerator.RegistrationDataGenerator;
import com.automationpractise.pages.LandingPage;
import com.automationpractise.pages.RegistrationPage;
import com.framework.core.BaseTests;
import com.framework.core.BaseUtils;
import com.framework.core.TestAsserts;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationTests extends BaseTests {


    @Test(description = "Validating the registration flow for automation practise website. Ensuring everying works fine")
    public void validateRegistrationPositiveFlow() throws InterruptedException {
        LandingPage landingPage = new LandingPage();
        landingPage.openAutomationPractiseWebsite();
        landingPage.navigateToLogin();
        RegistrationPage registrationPage = landingPage.navigateToRegistration(RegistrationDataGenerator.getEmailAddress());
        registrationPage.enterRegistrationDetails(
                RegistrationDataGenerator.getFirstName(),
                RegistrationDataGenerator.getLastName(),
                RegistrationDataGenerator.getUniquePassword());

        Thread.sleep(4000);

    }


}
