package com.cathay.wmsp.interfaces.rest.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "刪除投資組合")
@Data
public class ReviewPortfolioDraftReqDto {

	@ApiModelProperty("維護人員ID")
	@NotBlank
	@JsonProperty(value = "MEMBER_ID")
	String memberId;

	@ApiModelProperty("審核種類")
	@NotBlank
	@JsonProperty(value = "REVIEW_TYPE")
	String reviewType;
	
	@ApiModelProperty("下架日期")
	@NotBlank
	@JsonProperty(value = "STOP_DATE")
	String stopDate;
	
	@ApiModelProperty("下架說明")
	@NotBlank
	@JsonProperty(value = "STOP_DESC")
	String stopDesc;

	@Override
	public String toString() {
		return "ReviewPortfolioDraftReqDto [memberId=" + memberId + ", reviewType=" + reviewType + ", stopDate="
				+ stopDate + ", stopDesc=" + stopDesc + "]";
	}

}
