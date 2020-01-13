package com.assignment.tests.bookingController;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.Test;

import com.assignment.tests.helpers.bookingController.BookingControllerSetupHelper;
import com.assignment.tests.models.bookingController.BookingRequestModel;
import com.assignment.testware.utility.Constants;
import com.aventstack.extentreports.Status;

public class BookingController_PostiveCases extends BookingControllerSetupHelper {

	@Test(dataProvider = "createBookingSingleObject", description = "Test that bookings can be created.")
	public void PostAPI_createBooking_booking_canBeCreated(BookingRequestModel bookingModel){
		
		// Preconditions -> Data is already set in model input from dataProvider.
		
		// Test -> Hit POST Api createBooking and fetch the response.
		testSteps.log(Status.INFO, "Going to hit API 'createBooking'");
		log.info("Going to hit API 'createBooking'");
		response = createBookingWithValidData(bookingModel);
		testSteps.log(Status.PASS, "Response Code: " + response.getStatusCode() + "\n Actual Response Body :" + response.asString());
		log.info("Response Code:" + response.getStatusCode() + "\n Actual Response Body :" + response.asString());		 
		
		// Assert -> validate the the response data from createBooking Api
		assertEquals(response.getStatusCode(), httpCode.CREATED, Constants.INCORRECT_RESPONSE);
		assertEquals(responseValidator.jsonStringValue(response, "$.booking.bookingdates.checkin"), bookingModel.getBookingdates().getCheckin());
		assertEquals(responseValidator.jsonStringValue(response, "$.booking.bookingdates.checkout"), bookingModel.getBookingdates().getCheckout());
		assertEquals(responseValidator.jsonLongValue(response, "$.booking.roomid"), bookingModel.getRoomid());
		testSteps.log(Status.PASS, "API response verified successfully !");
		log.info("API response verified successfully !");
		
	}
	
	@Test(dataProvider = "createBookingSingleObject", description = "Test that the data returned for an existing booking matches.")
	public void GetAPI_getBooking_existingBooking_matches(BookingRequestModel bookingModel){
		
		// Preconditions -> Create a test booking using POST Api.
		testSteps.log(Status.INFO, "Going to hit API 'createBooking'");
		log.info("Going to hit createBooking 'createBooking'");
		response = createBookingWithValidData(bookingModel);
		testSteps.log(Status.PASS, "Response Code:" + response.getStatusCode() + "\nActual Response Body :" + response.asString());
		log.info("Response Code:" + response.getStatusCode() + "\nActual Response Body :" + response.asString());	
		String bookingId = responseValidator.jsonStringValue(response, "$.bookingid");
		
		// Test -> Hit GET getBooking Api and fetch the response
		testSteps.log(Status.INFO, "Going to hit API 'getBooking' with bookingId : " +bookingId);
		log.info("Going to hit API 'getBooking' with bookingId : " +bookingId);
		response = getBooking(new String[] {bookingId});
		testSteps.log(Status.PASS, "Response Code:" + response.getStatusCode() + "\nActual Response Body :" + response.asString());
		log.info("Response Code:" + response.getStatusCode() + "\n Actual Response Body :" + response.asString());
		
		// Assert -> validate the the response data from getBooking api
		assertEquals(response.getStatusCode(), httpCode.SUCCESS, Constants.INCORRECT_RESPONSE);
		assertEquals(responseValidator.jsonStringValue(response, "$.bookingid"), bookingId);
		assertEquals(responseValidator.jsonLongValue(response, "$.roomid"), bookingModel.getRoomid());
		assertEquals(responseValidator.jsonStringValue(response, "$.bookingdates.checkin"), bookingModel.getBookingdates().getCheckin());
		assertEquals(responseValidator.jsonStringValue(response, "$.bookingdates.checkout"), bookingModel.getBookingdates().getCheckout());
		testSteps.log(Status.PASS, "API response verified successfully, so this positive test is verified successfully ! !");
		log.info("API response verified successfully, so this positive test is verified successfully ! !");
		
	}
	
	@Test(dataProvider = "createBookingMultiObject", description = "Testing at least 2 existing bookings are returned in the response.")
	public void GetAPI_getBookings_twoBookingsReturned(List<BookingRequestModel> bookingModel){
		
		// Preconditions -> Create two test bookings using POST Api on same roomId.
		testSteps.log(Status.INFO, "Going to hit API 'createBooking' for first booking");
		log.info("Going to hit createBooking 'createBooking' for first booking");
		response = createBookingWithValidData(bookingModel.get(0));
		testSteps.log(Status.PASS, "Response Code:" + response.getStatusCode() + "\nActual Response Body :" + response.asString());
		log.info("Response Code:" + response.getStatusCode() + "\n Actual Response Body :" + response.asString());
		String firstBookingId = responseValidator.jsonStringValue(response, "$.bookingid");
		
		testSteps.log(Status.INFO, "Going to hit API 'createBooking' for second booking");
		log.info("Going to hit createBooking 'createBooking' for second booking");
		response = createBookingWithValidData(bookingModel.get(1));
		testSteps.log(Status.PASS, "Response Code:" + response.getStatusCode() + "\nActual Response Body :" + response.asString());
		log.info("Response Code:" + response.getStatusCode() + "\n Actual Response Body :" + response.asString());
		String secondBookingId = responseValidator.jsonStringValue(response, "$.bookingid");
		String roomId = String.valueOf(bookingModel.get(0).getRoomid());
		
		
		// Test -> Hit GET getBookings API and fetch the response
		testSteps.log(Status.INFO, "Going to hit API 'getBookings' with roomId : " +roomId);
		log.info("Going to hit API 'getBookings' with roomId : " +roomId);
		response = getBookings(new String[] {roomId});
		testSteps.log(Status.PASS, "Response Code:" + response.getStatusCode() + "\nActual Response Body :" + response.asString());
		log.info("Response Code:" + response.getStatusCode() + "\n Actual Response Body :" + response.asString());
	
		
		// Assert -> validate the the response data from get booking
		assertEquals(response.getStatusCode(), httpCode.SUCCESS, Constants.INCORRECT_RESPONSE);

		/*verifying the first booking data*/
		assertEquals(responseValidator.jsonStringValue(response, "$.bookings[0].bookingid"),firstBookingId);
		assertEquals(responseValidator.jsonStringValue(response, "$.bookings[0].roomid"),roomId);
		assertTrue(responseValidator.jsonListOfStringValue(response, "$.bookings[0]..checkin").contains(bookingModel.get(0).getBookingdates().getCheckin()));
		assertTrue(responseValidator.jsonStringValue(response, "$.bookings[0]..checkout").contains(bookingModel.get(0).getBookingdates().getCheckout()));
		
		/*verifying the second booking data*/
		assertEquals(responseValidator.jsonStringValue(response, "$.bookings[1].bookingid"),secondBookingId);
		assertEquals(responseValidator.jsonStringValue(response, "$.bookings[1].roomid"),roomId);
		assertTrue(responseValidator.jsonListOfStringValue(response, "$.bookings[1]..checkin").contains(bookingModel.get(1).getBookingdates().getCheckin()));
		assertTrue(responseValidator.jsonStringValue(response, "$.bookings[1]..checkout").contains(bookingModel.get(1).getBookingdates().getCheckout()));

		testSteps.log(Status.PASS, "API response verified successfully, so this positive test is verified successfully ! !");
		log.info("API response verified successfully, so this positive test is verified successfully ! !");
		
	}

}
