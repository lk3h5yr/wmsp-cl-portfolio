package com.cathay.wmsp.interfaces.exceptions.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ErrorDto {

	@JsonProperty("RETURNCODE")
	private String returnCode;

	@JsonProperty("RETURNDESC")
	private String returnDesc;

	@JsonIgnore
	public String getRETURNCODE() {
		return returnCode;
	}

	public void setRETURNCODE(String returnCode) {
		this.returnCode = returnCode;
	}

	@JsonIgnore
	public String getRETURNDESC() {
		return returnDesc;
	}

	public void setRETURNDESC(String returnDesc) {
		this.returnDesc= returnDesc;
	}
}
