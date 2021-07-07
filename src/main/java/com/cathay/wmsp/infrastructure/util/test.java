package com.cathay.wmsp.infrastructure.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class test {
	private static final String COMMODITY_POOL_TYPE_DATE_FORMAT = "yyyyMMdd";
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat(COMMODITY_POOL_TYPE_DATE_FORMAT);
		System.out.println(sdf.parse("2021-7-7"));
	}

}
