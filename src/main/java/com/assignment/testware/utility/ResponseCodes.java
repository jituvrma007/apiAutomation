package com.assignment.testware.utility;

import org.apache.http.HttpStatus;

public class ResponseCodes {

	public final int SUCCESS = HttpStatus.SC_OK;
	public final int UNAUTHORISED = HttpStatus.SC_FORBIDDEN;
	public final int CREATED = HttpStatus.SC_CREATED;
	public final int ERROR = HttpStatus.SC_CONFLICT;
}
