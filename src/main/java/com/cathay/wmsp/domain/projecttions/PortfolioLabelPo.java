package com.cathay.wmsp.domain.projecttions;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 投資組合貼標: PORTFOLIO_LABEL
 */
@Entity
@Data
@Table(name = "portfolio_label")
public class PortfolioLabelPo implements Serializable {
	
	@Id
	@Column(name = "PORTFOLIO_ID")
	private String portfolioId;

	@Column(name = "LABEL_ID")
	private String labelId;

	@Column(name = "LABEL_SWITCH")
	private String labelSwitch;

	@Column(name = "LOG_ID")
	private LocalDateTime logId;
}
