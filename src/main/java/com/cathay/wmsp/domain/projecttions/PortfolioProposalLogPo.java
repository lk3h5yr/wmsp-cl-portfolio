package com.cathay.wmsp.domain.projecttions;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 投資組合建議書LOG: PORTFOLIO_PROPOSAL_LOG
 */
@Entity
@Data
@Table(name = "portfolio_proposal_log")
public class PortfolioProposalLogPo implements Serializable {
	
	@Id
	@Column(name = "LOG_ID")
	private String logId;
	
	@Column(name = "LOG_DATETIME")
	private LocalDateTime logDateTime;
	
	@Column(name = "PORTFOLIO_ID")
	private String portfolioId;

	@Column(name = "PROPOSAL")
	private String proposal;
	
	@Column(name = "CHANGE_ID")
	private String changeId;
	
	@Column(name = "CHANGE_DATETIME")
	private LocalDateTime changeDateTime;
}
