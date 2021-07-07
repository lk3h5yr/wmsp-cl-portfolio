package com.cathay.wmsp.interfaces.exceptions;

public enum ErrorStatus {
	ERROR_0001("0001", "輸入欄位缺漏或格式有誤"), 
	ERROR_0002("0002", "INSERT ERROR"),
	ERROR_0003("0003", "UPDATE ERROR"),
	ERROR_0004("0004", "DELETE ERROR"),
	ERROR_0005("0005", "查無資料"),
	ERROR_1001("1001", "該筆資料已存在，無法新增"), 
	ERROR_1001_1("1001", "沒有該筆資料，無法修改"), 
	ERROR_1001_2("1001", "沒有該筆資料，無法刪除"), 
	ERROR_1001_3("1001", "無法送出審核"), 
	ERROR_1001_4("1001", "該筆資料無法審核"), 
	ERROR_1002("1002", "該筆資料已送出審核，無法修改"),
	ERROR_1002_1("1002", "該筆資無法停用"), 
	ERROR_9999("9999", "其他未定義錯誤，請聯絡API負責人員");

	private final String code;
	private final String msg;

	ErrorStatus(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String code() {
		return this.code;
	}

	public String msg() {
		return this.msg;
	}

	public static ErrorStatus resolve(String code) {
		for (ErrorStatus status : values()) {
			if (status.code.equals(code)) {
				return status;
			}
		}
		return null;
	}
}
