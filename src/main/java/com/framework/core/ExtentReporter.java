package com.framework.core;

import com.relevantcodes.extentreports.ExtentReports;

import java.io.File;

public class ExtentReporter {

    public static ExtentReports mainReport;

    public static  ExtentReports getReporter() {
        String path = System.getProperty("user.dir") + "//src//test//resources//reports";
        File reportDirectory = new File(path);
        if (!reportDirectory.exists()) {
            reportDirectory.mkdirs();
        }
        mainReport = new ExtentReports(path + "//AutomationReport.html");
        return mainReport;
    }
}


//1. Create a report File - Provide a location
//2 . Create the object of class for creating main frame page
//3 . Create the tests in the report.
//4. Start Logging the report data
//5. Take screenshots of the report