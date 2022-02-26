package com.framework.core;

import com.relevantcodes.extentreports.LogStatus;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;

public class BaseTests implements ITestListener {

    static BaseUtils basePage;

    @BeforeSuite
    public void initializeEnvironment() throws IOException {
        EnvPropertiesLoader.loadEnvProperties();
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws IOException {
        basePage = new BaseUtils();
        basePage.launchBrowser(EnvPropertiesLoader.browser);
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult context) throws IOException {
        //basePage.captureScreenshot(context.getTestName());
        // basePage.captureScreenshotAsBase64();
        ExtentTestReporter.stopTest();
        basePage.closeBrowser();


    }

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName(); // ValidateShoppingCart();
    }

    public void onTestStart(ITestResult result) {
        ExtentTestReporter.startTest(getTestMethodName(result), result.getMethod().getDescription());
        //ExtentTestReporter.getTest().setDescription(result.getMethod().getDescription());
    }

    public void onTestSuccess(ITestResult result) {
        try {
            String base64 = basePage.captureScreenshotAsBase64();
            ExtentTestReporter.logPassedStepWithScreenshot(base64);
            ExtentTestReporter.logPassStep("Test Case Passed");
        } catch (Exception e) {

        }
    }

    public void onTestFailure(ITestResult result) {
        try {
            String base64 = basePage.captureScreenshotAsBase64();
            ExtentTestReporter.logFailedStepWithScreenshot(base64);
            ExtentTestReporter.logFailStep("Test Case Failed");

        } catch (Exception e) {
            ExtentTestReporter.logFailStep(e.getMessage());
        }

    }

    public void onTestSkipped(ITestResult result) {
        ExtentTestReporter.logSkipStep("Test Skipped Due to unknown reasons");
    }

    public void onFinish(ITestContext context) {
        ExtentReporter.mainReport.flush();
    }

    public void onStart(ITestContext context) {

    }


}
