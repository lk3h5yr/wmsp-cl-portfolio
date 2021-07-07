package com.cathay.wmsp.domain.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cathay.wmsp.domain.facade.PortfolioQueryInterface;
import com.cathay.wmsp.domain.projecttions.PortfolioMainLogPo;
import com.cathay.wmsp.infrastructure.repositories.PortfolioMainLogRepository;

/**
 * 模型詳細邏輯
 */
@Repository
public class PortfolioQueryRepositoryImpI implements PortfolioQueryInterface {

	@Autowired
	PortfolioMainLogRepository portfolioMainLogRepository;

	/**
	 * 查詢: 指定投資組合基本資料LOG
	 */
	public PortfolioMainLogPo findByPortfolioId(String portfolioId) {
		return portfolioMainLogRepository.findByPortfolioId(portfolioId);
	}
	
	/**
	 * 查詢: 指定投資組合基本資料LOG
	 */
	public PortfolioMainLogPo findByPortfolioIdAndStatusCode(String portfolioId, String statusCode) {
		return portfolioMainLogRepository.findByPortfolioIdAndStatusCode(portfolioId, statusCode);
	}
	
	/**
	 * 查詢: 全部投資組合基本資料LOG
	 */
	public List<PortfolioMainLogPo> findByPortfolioIdLike(String portfolioIdDate){
		return portfolioMainLogRepository.findByOrderByPortfolioIdAsc();
	}
}
