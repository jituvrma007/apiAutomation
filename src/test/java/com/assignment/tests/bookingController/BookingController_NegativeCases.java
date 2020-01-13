package com.assignment.tests.bookingController;


import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.annotations.Test;

import com.assignment.tests.helpers.bookingController.BookingControllerSetupHelper;
import com.assignment.tests.models.bookingController.BookingRequestModel;
import com.assignment.testware.utility.Constants;
import com.aventstack.extentreports.Status;

public class BookingController_NegativeCases extends BookingControllerSetupHelper {

	@Test(dataProvider = "createBookingMultiObjectNegativeCase", description = "A room cannot be booked more than once for a given date.")
	public void PostAPI_createBooking_moreThenOnceInDay(List<BookingRequestModel> bookingModel){
		
		// Preconditions -> Data is already set in model input from DataProvider.
		String roomId = String.valueOf(bookingModel.get(0).getRoomid());
		String checkInDate = String.valueOf(bookingModel.get(0).getBookingdates().getCheckin());
		String checkOutDate = String.valueOf(bookingModel.get(0).getBookingdates().getCheckout());
		
		// Test -> Hit POST Api createBooking to create booking first time. 
		testSteps.log(Status.INFO, "Doing 'createBooking' first time with roomId : "+roomId+" checkInDate : "+checkInDate+" & checkOutDate : "+checkOutDate);
		log.info("Doing 'createBooking' first time with roomId : "+roomId+" checkInDate : "+checkInDate+" & checkOutDate : "+checkOutDate);
		response = createBookingWithValidData(bookingModel.get(0));
		testSteps.log(Status.PASS, "Response Code:" + response.getStatusCode() + "\nActual Response Body :" + response.asString());
		log.info("Response Code:" + response.getStatusCode() + "\nActual Response Body :" + response.asString());	
		
		testSteps.log(Status.INFO, "Doing 'createBooking' second time with roomId : "+roomId+" checkInDate : "+checkInDate+"& checkOutDate : "+checkOutDate);
		log.info("Doing 'createBooking' second time with roomId : "+roomId+" checkInDate : "+checkInDate+"& checkOutDate : "+checkOutDate);
		response = createBookingWithValidData(bookingModel.get(1));
		testSteps.log(Status.PASS, "Response Code: " + response.getStatusCode());
		log.info("Response Code: " + response.getStatusCode());	
		
		// Assert -> validate the the response data from createBooking Api
		assertEquals(response.getStatusCode(), httpCode.ERROR, Constants.INCORRECT_RESPONSE);
		
		testSteps.log(Status.PASS, "Error Response received,so this negative test is verified successfully !");
		log.info("Error Response received,so this negative test is verified successfully !");
		
	}
	
	@Test(dataProvider = "createBookingSingleObjectNegativeCase", description = "The check-out date must be greater than the check-in date.")
	public void PostAPI_createBooking_checkOutDate_greaterThen_checkInDate(BookingRequestModel bookingModel){
		// Preconditions -> Data is already set in model input from DataProvider.
		String checkInDate = String.valueOf(bookingModel.getBookingdates().getCheckin());
		String checkOutDate = String.valueOf(bookingModel.getBookingdates().getCheckout());
		
		// Test -> Hit POST Api createBooking to create booking with invalid data. 
		testSteps.log(Status.INFO, "Doing 'createBooking' with checkInDate : "+checkInDate+" & checkOutDate : "+checkOutDate);
		log.info("Doing 'createBooking' with checkInDate : "+checkInDate+" & checkOutDate : "+checkOutDate);
		response = createBookingWithValidData(bookingModel);
		testSteps.log(Status.PASS, "Response Code: " + response.getStatusCode());
		log.info("Response Code: " + response.getStatusCode());
		
		// Assert -> validate the the response data from createBooking Api
		assertEquals(response.getStatusCode(), httpCode.ERROR, Constants.INCORRECT_RESPONSE);
		
		testSteps.log(Status.PASS, "Error Response received,so this negative test is verified successfully !");
		log.info("Error Response received,so this negative test is verified successfully !");
		
	}

}
