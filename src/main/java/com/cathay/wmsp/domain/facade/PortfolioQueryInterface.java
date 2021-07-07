package com.cathay.wmsp.domain.facade;

import java.util.List;

import com.cathay.wmsp.domain.projecttions.PortfolioMainLogPo;

public interface PortfolioQueryInterface {
	
	/**
	 * 查詢: 投資組合基本資料LOG
	 * @param portfolioId
	 * @return
	 */
	PortfolioMainLogPo findByPortfolioId(String portfolioId);
	
	/**
	 * 查詢: 投資組合基本資料LOG
	 * @param portfolioId
	 * @param statusCode
	 * @return
	 */
	PortfolioMainLogPo findByPortfolioIdAndStatusCode(String portfolioId, String statusCode);
	
	/**
	 * 查詢: 投資組合基本資料LOG
	 * @return
	 */
	List<PortfolioMainLogPo> findByPortfolioIdLike(String portfolioIdDate);
}
