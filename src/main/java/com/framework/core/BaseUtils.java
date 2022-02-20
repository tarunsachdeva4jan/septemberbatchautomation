package com.framework.core;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class BaseUtils {
    public static ThreadLocal<WebDriver> baseDriver = new ThreadLocal<>();
    PropertiesLoader ORProps;
    String ORFilePath;


//    public BaseUtils() throws IOException {
//        ORProps = new PropertiesLoader();
//        ORFilePath = System.getProperty("user.dir") + "//src//main//resources//OR.properties";
//        ORProps.loadAllProperties(ORFilePath);
//    }

    public void launchBrowser(String browserType) {
        if (browserType.equalsIgnoreCase("Chrome")) {
            ChromeOptions options = new ChromeOptions();
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("download.default_directory", System.getProperty("user.dir") + "//browserDownloads//");
            prefs.put("excludeSwitches", Arrays.asList("disable-popup-blocking"));
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            prefs.put("useAutomationExtension", false);
            options.setExperimentalOption("prefs", prefs);
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\binaries\\chromedriver.exe");
            //baseDriver = new ChromeDriver(options);
            baseDriver.set(new ChromeDriver(options));
        } else if (browserType.equalsIgnoreCase("IE")) {
            InternetExplorerOptions ieOptions = new InternetExplorerOptions();
            ieOptions.destructivelyEnsureCleanSession();
            ieOptions.requireWindowFocus();
            ieOptions.ignoreZoomSettings();
            ieOptions.introduceFlakinessByIgnoringSecurityDomains();
            System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\binaries\\IEDriverServer.exe");
            // baseDriver = new InternetExplorerDriver(ieOptions);
            baseDriver.set(new InternetExplorerDriver(ieOptions));
        } else if (browserType.equalsIgnoreCase("Edge")) {
            System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\binaries\\msedgedriver.exe");
            //  baseDriver = new EdgeDriver();
            baseDriver.set(new EdgeDriver());
        }
        baseDriver.get().manage().window().maximize();//maximize window
        baseDriver.get().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS); //wait for page to load
        baseDriver.get().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void closeAllBrowser() {
        baseDriver.get().quit();
    }

    public void closeBrowser() {
        baseDriver.get().close();
    }

    public void refreshBrowser() {
        baseDriver.get().navigate().refresh();
    }

    public void launchUrl(String url) {
        baseDriver.get().navigate().to(url);
    }

    public void sleepFor(int seconds) throws InterruptedException {
        Thread.sleep(seconds * 1000);
    }

    public void clickOn(String elementName) {
        ElementFinder obj = new ElementFinder(baseDriver.get());
        String value = ORProps.getProperty(elementName);
        String splittedValues[] = value.split("-");
        String by = splittedValues[0];
        String byValue = splittedValues[1];
        WebElement element = obj.findElementBy(by, byValue);
        element.click();
    }

    public void typeInto(String elementName, String testValue) {
        ElementFinder obj = new ElementFinder(baseDriver.get());
        String value = ORProps.getProperty(elementName);
        String splittedValues[] = value.split("-");
        String by = splittedValues[0];
        String byValue = splittedValues[1];
        WebElement element = obj.findElementBy(by, byValue);
        element.sendKeys(testValue);
    }

    public WebElement getElementFromWebPage(String elementName) {
        ElementFinder obj = new ElementFinder(baseDriver.get());
        String value = ORProps.getProperty(elementName);
        String splittedValues[] = value.split("-");
        String by = splittedValues[0];
        String byValue = splittedValues[1];
        WebElement element = obj.findElementBy(by, byValue);

        return element;
    }

    public void switchToSecondWindow() {
        Set<String> windows = baseDriver.get().getWindowHandles();
        List<String> windowsList = new ArrayList<String>(windows);
        baseDriver.get().switchTo().window(windowsList.get(1)); //actual switching happens with this command.
    }

    public void switchToOriginalWindow() {
        Set<String> windows = baseDriver.get().getWindowHandles();
        List<String> windowsList = new ArrayList<String>(windows);
        baseDriver.get().switchTo().window(windowsList.get(0)); //actual switching happens with this command.
    }

    public void switchToLastWindow() {
        Set<String> windows = baseDriver.get().getWindowHandles();
        List<String> windowsList = new ArrayList<String>(windows);
        baseDriver.get().switchTo().window(windowsList.get(windowsList.size() - 1)); //actual switching happens with this command.

    }

    public void switchToSecondLastWindow() {
        Set<String> windows = baseDriver.get().getWindowHandles();
        List<String> windowsList = new ArrayList<String>(windows);
        baseDriver.get().switchTo().window(windowsList.get(windowsList.size() - 2)); //actual switching happens with this command.
    }


    public void switchToFrame(WebElement element) {
        baseDriver.get().switchTo().frame(element);
    }

    public void switchBacktoMainFrame() {
        baseDriver.get().switchTo().defaultContent();
    }

    public void switchToJavascriptAlertAndAccept() {
        baseDriver.get().switchTo().alert().accept();
    }

    public void switchToJavascriptAlertAndDismiss() {
        baseDriver.get().switchTo().alert().dismiss();
    }

    public void captureScreenshot(String TestName) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot) baseDriver.get();
        File file = screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(
                file, new File(
                        System.getProperty("user.dir") +
                                "src//test//resources//screenshots//" + TestName
                                + new SimpleDateFormat("dd.MM.yyyy.hh.mm.ss").format(new Date()) +
                                ".png"));
    }

    public String captureScreenshotAsBase64() throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot) baseDriver.get();
        String screenshotString = screenshot.getScreenshotAs(OutputType.BASE64);
        System.out.println(screenshotString);
        ThreadLocal<String> str = new ThreadLocal<>();
        str.set(screenshotString);
        return str.get();
    }

}
