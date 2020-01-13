package com.assignment.testware.utility;

import java.io.File;
import java.util.Date;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	public static ExtentReports extent;

	public static ExtentReports getInstance() {

		File file = new File(Constants.REPORTS_PATH);
		if (!file.exists()) {
			file.mkdirs();
		}

		if (extent == null) {

			Date d = new Date();
			String fileName = d.toString().replace(":", "_").replace(" ", "_") + ".html";
			String reportPath = Constants.REPORTS_PATH + fileName;

			ExtentHtmlReporter report = new ExtentHtmlReporter(reportPath);
			report.setAnalysisStrategy(AnalysisStrategy.CLASS);

			report.config().setReportName(Constants.REPORTS_NAME);
			report.config().setChartVisibilityOnOpen(true);

			report.config().setTheme(Theme.STANDARD);
			report.config().setDocumentTitle(Constants.DOCUMENT_TITLE);
			report.config().setEncoding("utf-8");

			extent = new ExtentReports();
			extent.attachReporter(report);

		}
		return extent;
	}

}
