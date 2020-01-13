package com.assignment.testware.utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class CommonHelperMethods {

	public static String generateRandomEmailAddress() {
		String randomEmail = "testautomation" + Math.floor(Math.random() * 10000) + "@yopmail.com";
		return randomEmail;
	}

	public static String generateRandomMobileNumber() {
		Random r = new Random();
		int low = 7;
		int high = 9;

		int i1 = r.nextInt(high - low) + low;
		int i2 = r.nextInt(8);
		int i3 = r.nextInt(8);
		int i4 = r.nextInt(742); // returns random number between 0 and 742
		int i5 = r.nextInt(10000); // returns random number between 0 and 9999

		String phoneNumber = String.format("+91%d%d%d%03d%04d", i1, i2, i3, i4, i5);
		return phoneNumber;
	}

	public static String getAheadDate(int dayToAdd) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-DD"); // dd-MM-yyyy
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, dayToAdd);

		String newDate = format.format(calendar.getTime());
		return newDate;
	}
	
	public static String replaceParamInsideURL(String url, String[] params) {
		for (int i = 0; i < params.length; i++) {
			url = url.replace("${" + i + "}", params[i]);
		}
		return url;
	}
	
}
