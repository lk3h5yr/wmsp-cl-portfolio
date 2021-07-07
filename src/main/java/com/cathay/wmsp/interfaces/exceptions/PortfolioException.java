package com.cathay.wmsp.interfaces.exceptions;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import lombok.Data;

/** 
 * CommodityPoolException
 */
@Data
public class PortfolioException extends RuntimeException implements Serializable {

	private String msg;
	private String code;
	private String rtnCode;
	private HttpStatus status;
	private String stage;

	public PortfolioException(String msg, String code, HttpStatus status) {
		super(msg);
		this.msg = msg;
		this.rtnCode = code;
		this.status = status;
	}

	public PortfolioException(String msg, String code, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.rtnCode = code;
	}
	
	public PortfolioException(String msg, String code, HttpStatus status, String stage) {
		super(msg);
		this.msg = msg;
		this.rtnCode = code;
		this.status = status;
		this.stage = stage;
	}
}
