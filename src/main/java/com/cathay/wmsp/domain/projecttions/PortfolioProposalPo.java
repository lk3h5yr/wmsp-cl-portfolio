package com.cathay.wmsp.domain.projecttions;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 投資組合建議書: PORTFOLIO_PROPOSAL
 */
@Entity
@Data
@Table(name = "portfolio_proposal")
public class PortfolioProposalPo implements Serializable {
	
	@Id
	@Column(name = "PORTFOLIO_ID")
	private String portfolioId;

	@Column(name = "PROPOSAL")
	private String proposal;

	@Column(name = "LOG_ID")
	private String logId;
}
