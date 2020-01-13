package com.assignment.tests.models.bookingController;

import com.assignment.testware.utility.CommonHelperMethods;

public class BookingDatesModel {

	private String checkin;
	private String checkout;

	//==== Getter/Setter Below ====
	
	public String getCheckin() {
		return checkin;
	}

	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}

	public String getCheckout() {
		return checkout;
	}

	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}

	//==== Helper Methods Below ====
	public BookingDatesModel withBookingDatesModelDefaultData() {
		return this
			.withCheckin(CommonHelperMethods.getAheadDate(5))
			.withCheckout(CommonHelperMethods.getAheadDate(8));
	}

	public BookingDatesModel withCheckout(String checkout) {
		this.checkout = checkout;
		return this;
	}

	public BookingDatesModel withCheckin(String checkin) {
		this.checkin = checkin;
		return this;
	}

	@Override
	public String toString() {
		return "BookingDatesModel [checkin=" + checkin + ", checkout=" + checkout + "]";
	}
	
	
	
	
	
	
}
