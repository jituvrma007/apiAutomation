package com.assignment.tests.helpers.bookingController;

import java.util.LinkedHashMap;

import com.assignment.tests.models.bookingController.BookingRequestModel;
import com.assignment.testware.base.ApiBaseSetup;

import io.restassured.response.Response;

public class BookingControllerTestHelper extends ApiBaseSetup {
	
	public Response createBookingWithValidData(BookingRequestModel bookingModel) {
		requestExecutor.testSteps = super.testSteps;
		String apiEndpoint = envReader.returnCompleteUri("bookingService", "createBooking");
		return requestExecutor.executePostRequest(apiEndpoint, bookingModel, new LinkedHashMap<String, String>());
	}

	public Response getBooking(String[] params) {
		String apiEndpoint = envReader.returnCompleteUri("bookingService", "getBooking");
		return requestExecutor.executeGetRequest(apiEndpoint, params);
	}

	public Response getBookings(String[] params) {
		String apiEndpoint = envReader.returnCompleteUri("bookingService", "getBookings");
		return requestExecutor.executeGetRequest(apiEndpoint, params);
	}

}
