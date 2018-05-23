package com.api.util;

public class NumberUtils {

	public static Boolean isEmpty(Integer intNumber) {
		if (intNumber == null || intNumber == 0) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public static Boolean isEmpty(Long longNumber) {
		if (longNumber == null || longNumber == 0L) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

}
