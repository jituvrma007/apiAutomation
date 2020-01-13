package com.assignment.testware.utility;

import static io.restassured.RestAssured.given;

import java.util.LinkedHashMap;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.google.gson.Gson;

import io.restassured.response.Response;

public class RequestExecutor {

	public Response response;
	public ExtentTest testSteps;
	public Gson gson = new Gson();

	public Response executePostRequest(String apiEndPoint, Object payload, LinkedHashMap<String, String> headers) {
		headers.put("Content-Type", "application/json");
		response = given().when().headers(headers).body(payload).log().all().post(apiEndPoint).then().extract()
				.response();

		testSteps.log(Status.INFO, "Request Headers ::: " + headers.toString());
		testSteps.log(Status.INFO, "Request ApiEndPoint ::: " + apiEndPoint);
		testSteps.log(Status.INFO, "Request Body ::: " + gson.toJson(payload));

		return response;
	}

	public Response executeGetRequest(String apiEndPoint, String[] params) {
		apiEndPoint = CommonHelperMethods.replaceParamInsideURL(apiEndPoint, params);
		response = given().when().log().all().get(apiEndPoint).then().extract().response();

		testSteps.log(Status.INFO, "Request ApiEndPoint ::: " + apiEndPoint);
		return response;
	}

}
