package com.cathay.wmsp.domain.projecttions;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 投資組合基本資料: PORTFOLIO_MAIN
 */
@Entity
@Data
@Table(name = "portfolio_main")
public class PortfolioMainPo implements Serializable {
	
	@Id
	@Column(name = "PORTFOLIO_ID")
	private String portfolioId;

	@Column(name = "PORTFOLIO_NAME")
	private String portfolioName;

	@Column(name = "PORTFOLIO_TEMPLATE_ID")
	private String portfolioTemplateId;

	@Column(name = "INVESTMENT_AMOUNT")
	private int investmentAmount;

	@Column(name = "RELEASE_DATE")
	private Date releaseDate;

	@Column(name = "COMMODITY_STOCK")
	private String commodityStock;

	@Column(name = "COMMODITY_ETF")
	private String commodityEtf;

	@Column(name = "COMMODITY_FUND")
	private String commodityFund;

	@Column(name = "RISK_LEVEL")
	private String riskLevel;

	@Column(name = "STOP_DATE")
	private Date stopDate;

	@Column(name = "STOP_CHECK")
	private String stopCheck;

	@Column(name = "STOP_DESC")
	private String stopDesc;

	@Column(name = "LOG_ID")
	private String logId;
}
