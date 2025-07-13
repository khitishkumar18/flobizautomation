package com.automationframework.listeners;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.automationframework.base.BaseClass;
import com.automationframework.utilities.ReadConfig;
import com.automationframework.utilities.RetryAnalyzer;
import org.openqa.selenium.WebDriver;
import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static com.automationframework.base.BaseClass.logger;

public class ExtentListenerClass extends BaseClass implements ITestListener, IAnnotationTransformer {

    ExtentSparkReporter htmlReporter;
    ExtentReports reports;
    ExtentTest test;

    public ExtentListenerClass() throws IOException {
        // Default constructor
    }

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }

    public void configureReport() throws IOException {
        ReadConfig readConfig = new ReadConfig();
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String reportName = "AutomationReport_Billing_" + timestamp + ".html";

        htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/src/test/resources/Reports/" + reportName);
        reports = new ExtentReports();
        reports.attachReporter(htmlReporter);

        // Add system/environment info
        reports.setSystemInfo("Machine:", "Khitish PC");
        reports.setSystemInfo("OS", "Windows 11");
        reports.setSystemInfo("Browser:", readConfig.getKey("browser"));
        reports.setSystemInfo("User Name:", "Khitish Kumar Nayak");

        // Configure report look and feel
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("This is automation report for myBillBook");
        htmlReporter.config().setTheme(Theme.DARK);
    }

    @Override
    public void onStart(ITestContext context) {
        try {
            configureReport();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("On Start method invoked....");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("On Finished method invoked....");
        reports.flush(); // mandatory to ensure info is written to report
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = getTestName(result);
        logger.info("********** [TEST CASE STARTED] " + testName + " **********");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = getTestName(result);
        logger.info("********** [TEST CASE PASSED] " + testName + " **********");

        test = reports.createTest(result.getName());
        test.log(Status.PASS, MarkupHelper.createLabel(testName + " testcase is passed", ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = getTestName(result);
        logger.info("********** [TEST CASE FAILED] " + testName + " **********");

        test = reports.createTest(result.getName());
        test.log(Status.FAIL, MarkupHelper.createLabel(testName + " testcase is failed", ExtentColor.RED));

        WebDriver driver = getDriver();
        if (driver != null) {
            try {
                // Capture screenshot using your ScreenshotUtil (optional)
                su.captureScreenShot(driver, result.getName() + "_failed");
            } catch (IOException e) {
                logger.error("Exception while taking screenshot: " + e.getMessage());
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = getTestName(result);
        logger.info("********** [TEST CASE SKIPPED] " + testName + " **********");

        test = reports.createTest(result.getName());
        test.log(Status.SKIP, MarkupHelper.createLabel(testName + " testcase is skipped", ExtentColor.YELLOW));
        System.out.println("Name of test method skipped: " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Not implemented
    }

    // Utility method to get test name or method name
    private String getTestName(ITestResult result) {
        String testName = result.getMethod().getDescription();
        if (testName == null || testName.isEmpty()) {
            testName = result.getMethod().getMethodName();
        }
        return testName;
    }
}
