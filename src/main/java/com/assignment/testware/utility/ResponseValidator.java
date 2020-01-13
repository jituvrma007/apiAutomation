package com.assignment.testware.utility;

import java.util.List;

import com.jayway.jsonpath.JsonPath;

import io.restassured.response.Response;

public class ResponseValidator {
	
	public String jsonStringValue(Response response, String regPath) {
		return JsonPath.read(response.getBody().asString(),regPath).toString();	
	} 

	public List<String> jsonListOfStringValue(Response response, String regPath ) {
		return JsonPath.read(response.getBody().asString(),regPath);	
	}

	public long jsonLongValue(Response response, String regPath) {
		return 	Long.valueOf(JsonPath.read(response.getBody().asString(),regPath).toString());
	}
	
}
