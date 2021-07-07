package com.cathay.wmsp.interfaces.rest.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "修改投資組合及投資標的")
@Data
public class ModifyPortfolioDraftReqDto {

	@ApiModelProperty("投資組合名稱")
	@NotBlank
	@JsonProperty(value = "PORTFOLIO_NAME")
	String portfolioName;

	@ApiModelProperty("模板ID")
	@NotBlank
	@JsonProperty(value = "PORTFOLIO_TEMPLATE_ID")
	String portfolioTemplateId;

	@ApiModelProperty("投資金額")
	@NotNull
	@JsonProperty(value = "INVESTMENT_AMOUNT")
	Integer investmentAmount;

	@ApiModelProperty("上架日期")
	@NotBlank
	@JsonProperty(value = "RELEASE_DATE")
	String releaseDate;

	@ApiModelProperty("下架日期")
	@NotBlank
	@JsonProperty(value = "STOP_DATE")
	String stopDate;

	@ApiModelProperty("股票 Y/N")
	@NotBlank
	@JsonProperty(value = "COMMODITY_STOCK")
	String commodityStock;

	@ApiModelProperty("ETF Y/N")
	@NotBlank
	@JsonProperty(value = "COMMODITY_ETF")
	String commodityEtf;

	@ApiModelProperty("基金 Y/N")
	@NotBlank
	@JsonProperty(value = "COMMODITY_FUND")
	String commodityFund;

	@ApiModelProperty("風險等級")
	@NotBlank
	@JsonProperty(value = "RISK_LEVEL")
	String riskLevel;

	@ApiModelProperty("商品池清單")
	@NotEmpty
	@JsonProperty(value = "COMMODITY_LIST")
	List<CommodityListDto> commodityList;

	@ApiModelProperty("建立者")
	@NotBlank
	@JsonProperty(value = "MEMBER_ID")
	String memberId;

	@Override
	public String toString() {
		return "ModifyPortfolioDraftReqDto [portfolioName=" + portfolioName + ", portfolioTemplateId="
				+ portfolioTemplateId + ", investmentAmount=" + investmentAmount + ", releaseDate=" + releaseDate
				+ ", stopDate=" + stopDate + ", commodityStock=" + commodityStock + ", commodityEtf=" + commodityEtf
				+ ", commodityFund=" + commodityFund + ", riskLevel=" + riskLevel + ", commodityList=" + commodityList
				+ ", memberId=" + memberId + "]";
	}
}
