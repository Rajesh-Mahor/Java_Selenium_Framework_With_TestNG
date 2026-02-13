package com.orangehrm.TestCases;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
import com.orangehrm.Utilities.ReadConfig;

public class ListenerImplimentionClass implements ITestListener {

	ExtentSparkReporter htmlReport;
	ExtentReports report;
	ExtentTest test;

	public void ReportSetUp() {
		
		String fileName = "OrangeHrm_ExtentReport_" + 
		        LocalDateTime.now().format(
		        DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"))
		        + ".html";
		
		htmlReport = new ExtentSparkReporter(fileName);
		report = new ExtentReports();
		report.attachReporter(htmlReport);

		report.setSystemInfo("Operating Sysytem", "Windows 11");
		report.setSystemInfo("User", "Rajesh");
		report.setSystemInfo("Browser", new ReadConfig().getBrowser());
		report.setSystemInfo("Envirnoment", "UAT Testing");

		htmlReport.config().setTheme(Theme.DARK);
		htmlReport.config().setReportName("ExtendReport For OrangeHRM TestCases");
		htmlReport.config().setDocumentTitle("OrangeHRM Test Result");

	}

	@Override
	public void onStart(ITestContext context) {
		ReportSetUp();
		System.out.println("Test Suite Execution Started: " + context.getName());
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Test Suite Execution Finished: " + context.getName());
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {

		System.out.println("Test Started: " + result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Test Passed: " + result.getName());
		test = report.createTest(result.getName());
		test.log(Status.PASS,
				MarkupHelper.createLabel("Name Of The Pass TestCase is :" + result.getName(), ExtentColor.GREEN));
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Test Failed: " + result.getName());
		test = report.createTest(result.getName());
		test.log(Status.FAIL,
				MarkupHelper.createLabel("Name Of The Fail TestCase is :" + result.getName(), ExtentColor.RED));

		String sceenShotPath = System.getProperty("user.dir") + "\\ScreenShots\\" + result.getName() + ".png";
		File file = new File(sceenShotPath);
		if (file.exists()) {
			test.fail("Captured ScrrenShot Is Below :" + test.addScreenCaptureFromPath(sceenShotPath));
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Test Skipped: " + result.getName());
		test = report.createTest(result.getName());
		test.log(Status.SKIP,
				MarkupHelper.createLabel("Name Of The Skip TestCase is :" + result.getName(), ExtentColor.YELLOW));
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

}
