package com.framework.core;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    public static int RetryLimit = 3;
    public static int retryCount = 0;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {
            if (retryCount < RetryLimit) {
                iTestResult.setStatus(ITestResult.FAILURE);
                retryCount++;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
