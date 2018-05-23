package com.api.constant;

public class HttpConstant {

	private HttpConstant() {

	}

	public static final String CODE_BAD_REQUEST = "400";
	public static final String CODE_UNAUTHORIZED = "401";
	public static final String CODE_SUCCESS = "200";
	public static final String CODE_NOT_FOUND = "404";
	public static final String CODE_FORBIDDEN = "403";
	public static final String CODE_NOT_ACCEPTABLE = "409";
	public static final String CODE_SERVER_ERROR = "500";

	public static final String MESSAGE_BAD_REQUEST = "bad_request";
	public static final String MESSAGE_UNAUTHORIZED = "unauthorized";
	public static final String MESSAGE_SUCCESS = "success";
	public static final String MESSAGE_NOT_FOUND = "not found";
	public static final String MESSAGE_SERVER_ERROR = "server_error";
	public static final String MESSAGE_SERVER_GOT_ERROR = "Server got error. Please wait a moment and try it again later.";
	public static final String MESSAGE_NO_DATA_FOUND = "No data found.";
	public static final String MESSAGE_INVALID_INPUT_DATE = "End date should be greater than Start date.";
}
