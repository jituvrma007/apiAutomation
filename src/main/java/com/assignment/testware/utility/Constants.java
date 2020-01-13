package com.assignment.testware.utility;

public class Constants {
	
	public static String ENV_XML_PATH = System.getProperty("user.dir")+ "/src/test/resources/com/assignment/environments/";
	public static String ENV_PROD = "prod"; 
	public static String ENV_HOST = System.getenv("ENV_HOST");
	
	/*Extent Report Properties*/
	public static String ALL_REPORTS_PATH = System.getProperty("user.dir")+ "/report/";
	public static String REPORTS_PATH = System.getProperty("user.dir")+ "/report/htmlReports/";
	public static String REPORTS_NAME = "Test Automation Report";
	public static String DOCUMENT_TITLE= "Test Automation Report";
	
	public static String INCORRECT_RESPONSE= "Service Response Code is not as expected";
	
}