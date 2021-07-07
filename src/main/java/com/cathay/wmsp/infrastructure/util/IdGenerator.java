package com.cathay.wmsp.infrastructure.util;

import java.util.UUID;

public class IdGenerator {

	public static String nextId() {
		return splitString(UUID.randomUUID().toString());
	}

	private static String splitString(String uuid) {
		String arrUuid = uuid.replace("-", "");
		return arrUuid.substring(0, 10);
	}
}
