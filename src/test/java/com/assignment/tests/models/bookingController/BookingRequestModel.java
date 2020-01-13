package com.assignment.tests.models.bookingController;

import java.util.Random;

import com.assignment.testware.utility.CommonHelperMethods;

public class BookingRequestModel {
	
	private BookingDatesModel bookingdates;
	private long bookingid;
	private boolean depositpaid;
	private String email;
	private String firstname;
	private String lastname;
	private String phone;
	private long roomid;

	//==== Getter/Setter Below ====
	
	public BookingDatesModel getBookingdates() {
		return bookingdates;
	}

	public void setBookingdates(BookingDatesModel bookingdates) {
		this.bookingdates = bookingdates;
	}

	public long getBookingid() {
		return bookingid;
	}

	public void setBookingid(long bookingid) {
		this.bookingid = bookingid;
	}

	public boolean isDepositpaid() {
		return depositpaid;
	}

	public void setDepositpaid(boolean depositpaid) {
		this.depositpaid = depositpaid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public long getRoomid() {
		return roomid;
	}

	public void setRoomid(long roomid) {
		this.roomid = roomid;
	}

	//==== Helper Methods Below ====
	
	public BookingRequestModel withBookingModelDefaultData() {
		return this.withBookingDatesModel(new BookingDatesModel()
												.withBookingDatesModelDefaultData())
				.withDepositpaid(true)
				.withEmail(CommonHelperMethods.generateRandomEmailAddress())
				.withFirstname("TestFirstname")
				.withLastname("TestLastname")
				.withPhone(CommonHelperMethods.generateRandomMobileNumber())
				.withBookingid(new Random().nextInt(1000))
				.withRoomId(new Random().nextInt(1000));

	}

	public BookingRequestModel withBookingDatesModel(BookingDatesModel bookingdates) {
		this.bookingdates = bookingdates;
		return this;
	}

	public BookingRequestModel withBookingid(long bookingid) {
		this.bookingid = bookingid;
		return this;
	}

	public BookingRequestModel withDepositpaid(boolean depositpaid) {
		this.depositpaid = depositpaid;
		return this;
	}

	public BookingRequestModel withEmail(String email) {
		this.email = email;
		return this;
	}

	public BookingRequestModel withFirstname(String firstname) {
		this.firstname = firstname;
		return this;
	}

	public BookingRequestModel withLastname(String lastname) {
		this.lastname = lastname;
		return this;
	}

	public BookingRequestModel withPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public BookingRequestModel withRoomId(long roomid) {
		this.roomid = roomid;
		return this;
	}

	@Override
	public String toString() {
		return "BookingRequestModel [bookingdates=" + bookingdates + ", bookingid=" + bookingid + ", depositpaid="
				+ depositpaid + ", email=" + email + ", firstname=" + firstname + ", lastname=" + lastname + ", phone="
				+ phone + ", roomid=" + roomid + "]";
	}

	
	
	
	
}
