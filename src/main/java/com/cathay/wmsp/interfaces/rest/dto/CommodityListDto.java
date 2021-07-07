package com.cathay.wmsp.interfaces.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CommodityListDto {

	@ApiModelProperty("商品池代碼")
	@JsonProperty("COMMODITY_POOL_ID")
	private String commodityPoolId;

	@ApiModelProperty("商品代碼")
	@JsonProperty("COMMODITY_ID")
	private String commodityId;

	@ApiModelProperty("比重")
	@JsonProperty("COMMODITY_WEIGHTS")
	private String commodityWeights;
}
