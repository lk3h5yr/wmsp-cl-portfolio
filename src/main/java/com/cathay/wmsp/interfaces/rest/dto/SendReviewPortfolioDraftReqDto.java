package com.cathay.wmsp.interfaces.rest.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "刪除投資組合")
@Data
public class SendReviewPortfolioDraftReqDto {

	@ApiModelProperty("維護人員ID")
	@NotBlank
	@JsonProperty(value = "MEMBER_ID")
	String memberId;

	@Override
	public String toString() {
		return "sendReviewPortfolioDraftReqDto [memberId=" + memberId + "]";
	}
}
