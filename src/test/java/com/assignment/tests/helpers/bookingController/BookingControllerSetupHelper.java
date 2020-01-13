package com.assignment.tests.helpers.bookingController;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.assignment.tests.models.bookingController.BookingDatesModel;
import com.assignment.tests.models.bookingController.BookingRequestModel;
import com.assignment.testware.utility.CommonHelperMethods;

public class BookingControllerSetupHelper extends BookingControllerTestHelper {

	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		log = LogManager.getLogger(this.getClass());
		testReport = extent.createTest(this.getClass().getSimpleName());
	}

	@BeforeMethod
	public void beforeMethod(Method method) {
		String testDescription = method.getAnnotation(Test.class).description();
		String methodName = method.getName();
		testReport.info(testDescription + "<br/> => " + methodName);
		testSteps = testReport.createNode(testDescription + " => " + methodName);
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		writeTestReportStatus(result);
	}

	@DataProvider(name = "createBookingSingleObject")
	public Object[][] dataProviderForCreateBookingSingleObject() {
		return new Object[][] {{ new BookingRequestModel().withBookingModelDefaultData() }};
	}

	@DataProvider(name = "createBookingMultiObject")
	public Object[][] dataProviderForCreateBookingMultisObject() {

		List<BookingRequestModel> listBookingRequestModel = new LinkedList<>();

		// adding one object with default values.
		listBookingRequestModel.add(new BookingRequestModel().withBookingModelDefaultData());

		// adding second object with same roomId and different CheckIn/CheckOut Date.
		listBookingRequestModel.add(new BookingRequestModel()
				.withBookingModelDefaultData()
				.withBookingDatesModel(new BookingDatesModel()
						.withCheckin(CommonHelperMethods.getAheadDate(10))
						.withCheckout(CommonHelperMethods.getAheadDate(12)))
				.withRoomId(listBookingRequestModel.get(0).getRoomid()));

		return new Object[][] {{ listBookingRequestModel }};
	}

	@DataProvider(name = "createBookingSingleObjectNegativeCase")
	public Object[][] createBookingSingleObjectNegativeCase() {

		// adding the object with different CheckOut date lesser than CheckIn date.
		BookingRequestModel bookingModel = new BookingRequestModel();
		bookingModel.withBookingModelDefaultData()
					.withBookingDatesModel(new BookingDatesModel()
							.withCheckin(CommonHelperMethods.getAheadDate(12))
							.withCheckout(CommonHelperMethods.getAheadDate(10)));
		return new Object[][] {{ bookingModel }};
	}

	@DataProvider(name = "createBookingMultiObjectNegativeCase")
	public Object[][] createBookingMultiObjectNegativeCase() {

		List<BookingRequestModel> listBookingRequestModel = new LinkedList<>();

		// adding one object with default values.
		listBookingRequestModel.add(new BookingRequestModel().withBookingModelDefaultData());

		// adding second object with same roomId and different CheckIn/CheckOut Date.
		listBookingRequestModel.add(new BookingRequestModel()
				.withBookingModelDefaultData()
				.withRoomId(listBookingRequestModel.get(0).getRoomid())
				.withBookingid(new Random().nextInt(1000)));

		return new Object[][] {{ listBookingRequestModel }};
	}
	
	@BeforeSuite
	public void beforeSuite() {
		log = LogManager.getLogger(this.getClass());
		log.info("===============================================================");
		log.info("======= Starting Automated API TestCase Execution =============");
		log.info("===============================================================");
	}
	
	@AfterSuite
	public void afterSuite() {
		log.info("===============================================================");
		log.info("======== Ending Automated API TestCase Execution ==============");
		log.info("===============================================================");
	}
}
