package com.cathay.wmsp.domain.facade;

import com.cathay.wmsp.domain.projecttions.PortfolioMainLogPo;
import com.cathay.wmsp.interfaces.rest.dto.AddPortfolioDraftReqDto;
import com.cathay.wmsp.interfaces.rest.dto.ModifyPortfolioDraftReqDto;

public interface PortfolioCommandInterface {

	/**
	 * 新增投資組合及商品池標的
	 * @param addPortfolioDraft
	 * @param portfolioId
	 */
	void addPortfolioDraft(AddPortfolioDraftReqDto addPortfolioDraft, String portfolioId);
	
	/**
	 * 修改投資組合基本資料及投資標的
	 * @param modifyPortfolioDraft
	 * @param portfolioId
	 */
	void modifyPortfolioDraft(ModifyPortfolioDraftReqDto modifyPortfolioDraft, String portfolioId, PortfolioMainLogPo  portfolioMainLogPO);
	
	/**
	 * 刪除投資組合
	 * @param portfolioMainLogPO
	 * @param portfolioId
	 * @param member
	 */
	void deletePortfolioDraft(PortfolioMainLogPo  portfolioMainLogPO, String portfolioId, String member);
	
	/**
	 * 送審投資組合
	 * @param portfolioMainLogPO
	 * @param portfolioId
	 * @param memberId
	 */
	void sendReviewPortfolioDraft(PortfolioMainLogPo portfolioMainLogPO, String portfolioId, String memberId);
	
	/**
	 * 審核投資組合
	 * @param portfolioMainLogPO
	 * @param portfolioId
	 * @param memberId
	 * @param reviewType
	 */
	void reviewPortfolioDraft(PortfolioMainLogPo portfolioMainLogPO, String portfolioId, String memberId, String reviewType, String stopDate, String stopDesc);
}
