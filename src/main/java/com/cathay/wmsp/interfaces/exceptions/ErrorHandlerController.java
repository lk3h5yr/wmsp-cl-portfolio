package com.cathay.wmsp.interfaces.exceptions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cathay.wmsp.interfaces.exceptions.dto.ErrorDto;

import lombok.extern.slf4j.Slf4j;

/** 
 * 自訂回傳錯誤訊息
 */
@Slf4j
@ControllerAdvice
public class ErrorHandlerController {

	@ExceptionHandler(PortfolioException.class)
	public ResponseEntity<Object> handleDemoException(PortfolioException e) {
		log.error("Exception occurred, Error code : {}, Error message: {}.", e.getRtnCode(), e.getMessage());
		return returnErrorResponse(e.getRtnCode(), e.getMsg(), e.getStatus());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(HttpServletRequest request, HttpServletResponse response, Exception e) {
		log.error("Exception occurred, Error message: {}", e.getMessage());
		return returnErrorResponse(ErrorStatus.ERROR_9999.code(), ErrorStatus.ERROR_9999.msg(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler({ MethodArgumentNotValidException.class, ConstraintViolationException.class })
	public ResponseEntity<Object> handleMethodArgumentNotValidException() {
		return returnErrorResponse(ErrorStatus.ERROR_0001.code(), ErrorStatus.ERROR_0001.msg(), HttpStatus.BAD_REQUEST);
	}

	private ResponseEntity<Object> returnErrorResponse(String errorCode, String msg, HttpStatus httpStatus) {
		ErrorDto errorDTO = new ErrorDto();
		errorDTO.setRETURNCODE(errorCode);
		errorDTO.setRETURNDESC(msg);
		return ResponseEntity.status(httpStatus).body(errorDTO);
	}
}
