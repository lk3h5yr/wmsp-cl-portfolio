package com.cathay.wmsp.infrastructure.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

	private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	private static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd hh:mm:ss";
	private static final String COMMODITY_POOL_TYPE_DATE_FORMAT = "yyyyMMdd";

	public static String getFormatLocalDateTime() {
		LocalDateTime localDateTime = LocalDateTime.ofInstant( new Date().toInstant(), ZoneId.systemDefault());
		DateTimeFormatter format = DateTimeFormatter.ofPattern(COMMODITY_POOL_TYPE_DATE_FORMAT);
		return localDateTime.format(format);
	}
	
	public static LocalDateTime getLocalDateTime() {
		return LocalDateTime.now();
	}
	
	public static LocalDateTime getLocalDateTimeMinusDays(int day) {
		return LocalDateTime.now().minusDays(day);
	}
	
	public static Date parseDate(String dateTimeStr) {
		try {
			if (dateTimeStr == null) {
				return null;
			}
			SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
			return sdf.parse(dateTimeStr);
		} catch (Exception e) {
			return null;
		}
	}

	//	public static Date parseDate(String dateStr) throws ParseException {
//		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
//		return sdf.parse(dateStr);
//	}

//
//	public static String formatDate(LocalDateTime date) {
//		if (date == null) {
//			return "";
//		}
//		return date.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT));
//	}
//
//	public static String formatDateTime(LocalDateTime date) {
//		if (date == null) {
//			return "";
//		}
//		return date.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT));
//	}
//
//
//	

}
