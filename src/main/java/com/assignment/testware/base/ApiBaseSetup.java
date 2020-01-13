package com.assignment.testware.base;

import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

import com.assignment.testware.utility.CommonHelperMethods;
import com.assignment.testware.utility.EnvironmentXmlReader;
import com.assignment.testware.utility.ExtentManager;
import com.assignment.testware.utility.RequestExecutor;
import com.assignment.testware.utility.ResponseCodes;
import com.assignment.testware.utility.ResponseValidator;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.google.gson.Gson;

import io.restassured.response.Response;

public class ApiBaseSetup {

	public Logger log;
	public ExtentReports extent = ExtentManager.getInstance();
	public ExtentTest testReport;
	public ExtentTest testSteps;
	public Response response;

	public CommonHelperMethods common = new CommonHelperMethods();
	public EnvironmentXmlReader envReader = new EnvironmentXmlReader();
	public RequestExecutor requestExecutor = new RequestExecutor();
	public ResponseValidator responseValidator = new ResponseValidator();
	public ResponseCodes httpCode  = new ResponseCodes();
	public Gson gson = new Gson();
	
	public SoftAssert softAssert = new SoftAssert(); 
	

	protected void writeTestReportStatus(ITestResult result) {
		if (result.isSuccess()) {
			testSteps.log(Status.PASS, "Test Passed");
		} else if (result.getStatus() == ITestResult.FAILURE) {
			testSteps.log(Status.FAIL, "Test Failed");

		} else if (result.getStatus() == ITestResult.SKIP) {
			testSteps.log(Status.SKIP, "Test Skipped");
		}
		if (extent != null) {
			extent.flush();
		}
	}

}
