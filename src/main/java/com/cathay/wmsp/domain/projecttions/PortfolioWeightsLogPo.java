package com.cathay.wmsp.domain.projecttions;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;

/**
 * 投資組合商品池及標的LOG: PORTFOLIO_WEIGHTS_LOG
 */
@IdClass(PortfolioWeightsLogPo.class)
@Entity
@Data
@Table(name = "portfolio_weights_log")
public class PortfolioWeightsLogPo implements Serializable {
	
	@Id
	@Column(name = "LOG_ID")
	private String logId;
	
	@Column(name = "LOG_DATETIME")
	private LocalDateTime logDateTime;
	
	@Id
	@Column(name = "PORTFOLIO_ID")
	private String portfolioId;

	@Id
	@Column(name = "COMMODITY_POOL_ID")
	private String commodityPoolId;

	@Id
	@Column(name = "COMMODITY_ID")
	private String commodityId;

	@Column(name = "COMMODITY_WEIGHT")
	private BigDecimal commodityWeight;
	
	@Column(name = "CHANGE_ID")
	private String changeId;
	
	@Column(name = "CHANGE_DATETIME")
	private LocalDateTime changeDateTime;

}
