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
 * 投資組合基本資料LOG: PORTFOLIO_MAIN_LOG
 */
@Entity
@Data
@Table(name = "portfolio_main_log")
public class PortfolioMainLogPo implements Serializable {
	
	@Id
	@Column(name = "LOG_ID")
	private String logId;

	@Column(name = "LOG_DATETIME")
	private LocalDateTime logDateTime;

	@Column(name = "PORTFOLIO_ID")
	private String portfolioId;
	
	@Column(name = "PORTFOLIO_NAME")
	private String portfolioName;
	
	@Column(name = "PORTFOLIO_TEMPLATE_ID")
	private String portfolioTemplateId;
	
	@Column(name = "RELEASE_DATE")
	private Date releaseDate;
	
	@Column(name = "INVESTMENT_AMOUNT")
	private int investmentAmount;
	
	@Column(name = "COMMODITY_STOCK")
	private String commodityStock;
	
	@Column(name = "COMMODITY_ETF")
	private String commodityEtf;

	@Column(name = "COMMODITY_FUND")
	private String commodityFund;
	
	@Column(name = "RISK_LEVEL")
	private String riskLevel;
	
	@Column(name = "STOP_DATE")
	private LocalDateTime stopDate;
	
	@Column(name = "STOP_CHECK")
	private String stopCheck;
	
	@Column(name = "STOP_DESC")
	private String stopDesc;
	
	@Column(name = "STATUS_CODE")
	private String statusCode;

	@Column(name = "CHANGE_ID")
	private String changeId;
	
	@Column(name = "CHANGE_DATETIME")
	private LocalDateTime changeDateTime;

	@Column(name = "SEND_REVIEW_ID")
	private String sendReviewId;
	
	@Column(name = "SEND_REVIEW_DATETIME")
	private LocalDateTime sendReviewDateTime;
	
	@Column(name = "REVIEW_ID")
	private String reviewId;
	
	@Column(name = "REVIEW_DATETIME")
	private LocalDateTime reviewDateTime;
	
	@Column(name = "DELETE_ID")
	private String deleteId;
	
	@Column(name = "DELETE_DATETIME")
	private LocalDateTime deleteDateTime;
	
	@Column(name = "IS_EDIT")
	private String isEdit;
	
	@Column(name = "END_DATE")
	private LocalDateTime endDate;

	@Override
	public String toString() {
		return "PortfolioMainLogPO [logId=" + logId + ", logDateTime=" + logDateTime + ", portfolioId=" + portfolioId
				+ ", portfolioName=" + portfolioName + ", portfolioTemplateId=" + portfolioTemplateId + ", releaseDate="
				+ releaseDate + ", commodityStock=" + commodityStock + ", commodityEtf=" + commodityEtf
				+ ", commodityFund=" + commodityFund + ", riskLevel=" + riskLevel + ", stopDate=" + stopDate
				+ ", stopCheck=" + stopCheck + ", stopDesc=" + stopDesc + ", statusCode=" + statusCode + ", changeId="
				+ changeId + ", changeDateTime=" + changeDateTime + ", sendReviewId=" + sendReviewId
				+ ", sendReviewDateTime=" + sendReviewDateTime + ", reviewId=" + reviewId + ", reviewDateTime="
				+ reviewDateTime + ", deleteId=" + deleteId + ", deleteDateTime=" + deleteDateTime + ", isEdit="
				+ isEdit + ", endDate=" + endDate + "]";
	}
}
