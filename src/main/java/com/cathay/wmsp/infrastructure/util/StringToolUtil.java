package com.cathay.wmsp.infrastructure.util;

public class StringToolUtil {
	
	public static String replaceNull(String str) {
		if (str == null) {
			return "";
		}
		return str;
	}
	
	public static String toUpperCase(String str) {
		if (str == null) {
			return "";
		}
		return str.toUpperCase();
	}
	
	public static String getLogIdNext(String logId) {
		int tempLogId = Integer.parseInt(logId);
		tempLogId = tempLogId + 1 ;
		return String.valueOf(tempLogId);
	}
}
