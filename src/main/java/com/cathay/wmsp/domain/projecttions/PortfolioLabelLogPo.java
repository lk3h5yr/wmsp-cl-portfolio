package com.cathay.wmsp.domain.projecttions;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 投資組合貼標LOG: PORTFOLIO_LABEL_LOG
 */
@Entity
@Data
@Table(name = "portfolio_label_log")
public class PortfolioLabelLogPo implements Serializable {
	
	@Column(name = "LOG_ID")
	private LocalDateTime logId;

	@Column(name = "LOG_DATETIME")
	private LocalDateTime logDateTime;

	@Id
	@Column(name = "PORTFOLIO_ID")
	private String portfolioId;

	@Column(name = "LABEL_ID")
	private String labelId;

	@Column(name = "LABEL_SWITCH")
	private String labelSwitch;
	
	@Column(name = "CHANGE_ID")
	private String changeId;
	
	@Column(name = "CHANGE_DATETIME")
	private LocalDateTime changeDateTime;

}
