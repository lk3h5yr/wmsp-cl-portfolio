package com.cathay.wmsp.application.internal.commandgateways;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.cathay.wmsp.domain.facade.PortfolioCommandInterface;
import com.cathay.wmsp.domain.facade.PortfolioQueryInterface;
import com.cathay.wmsp.domain.model.valueobjects.PortfolioConst;
import com.cathay.wmsp.domain.model.valueobjects.PortfolioIdPrefix;
import com.cathay.wmsp.domain.projecttions.PortfolioMainLogPo;
import com.cathay.wmsp.infrastructure.util.DateUtil;
import com.cathay.wmsp.interfaces.exceptions.ErrorStatus;
import com.cathay.wmsp.interfaces.exceptions.PortfolioException;
import com.cathay.wmsp.interfaces.rest.dto.AddPortfolioDraftReqDto;
import com.cathay.wmsp.interfaces.rest.dto.ModifyPortfolioDraftReqDto;
import com.cathay.wmsp.interfaces.rest.dto.ReviewPortfolioDraftReqDto;

import lombok.extern.slf4j.Slf4j;

/**
 * 應用層: 定義要完成的任務, 調用模型層(DOMAIN)
 */
@RestController
@Slf4j
@Service
public class PortfolioService {
	
	@Autowired
	PortfolioCommandInterface portfolioCommandInterface;
	
	@Autowired
	PortfolioQueryInterface portfolioQueryInterface;

	/**
	 * 新增投資組合及商品標的
	 * @param addPortfolioDraft
	 */
	public void addPortfolioDraft(AddPortfolioDraftReqDto addPortfolioDraft) {
		String portfolioId = this.getPortfolioId();
		log.debug("addPortfolioDraft.findByPortfolioId({}).", portfolioId);
		// 查詢portfolio_main_log
		PortfolioMainLogPo  portfolioMainLogPO = portfolioQueryInterface.findByPortfolioId(portfolioId);
		// 如果有資料, 返回錯誤訊息:該筆資料已存在，無法新增
		if (portfolioMainLogPO != null) {
			log.error("addPortfolioDraft portfolioMainLogPO is exsit. portfolioId: {}.", portfolioId);
			throw new PortfolioException(ErrorStatus.ERROR_1001.msg(), ErrorStatus.ERROR_1001.code(), HttpStatus.BAD_REQUEST);
		}
		log.debug("portfolioCommandInterface.addPortfolioDraft({}, {}).", addPortfolioDraft, portfolioId);
		portfolioCommandInterface.addPortfolioDraft(addPortfolioDraft, portfolioId);
	}
	
	/**
	 * 修改投資組合基本資料及投資標的
	 * @param modifyPortfolioDraft
	 * @param portfolioId
	 */
	public void modifyPortfolioDraft(ModifyPortfolioDraftReqDto modifyPortfolioDraft, String portfolioId) {
		log.debug("modifyPortfolioDraft.findByPortfolioIdAndStatusCode({}, {}).", portfolioId, PortfolioConst.STATUS_CODE_DEFAULT);
		// 查詢portfolio_main_log, StatusCode: 0 的資料
		PortfolioMainLogPo  portfolioMainLogPO = portfolioQueryInterface.findByPortfolioIdAndStatusCode(portfolioId, PortfolioConst.STATUS_CODE_DEFAULT);
		// 如果沒有資料, 返回錯誤訊息:沒有該筆資料，無法修改
		if (portfolioMainLogPO == null) {
			log.error("modifyPortfolioDraft portfolioMainLogPO is not exsit. portfolioId: {}.", portfolioId);
			throw new PortfolioException(ErrorStatus.ERROR_1001_1.msg(), ErrorStatus.ERROR_1001_1.code(), HttpStatus.BAD_REQUEST);
		}
		log.debug("portfolioCommandInterface.modifyPortfolioDraft({}, {}, {}).", modifyPortfolioDraft, portfolioId, portfolioMainLogPO);
		portfolioCommandInterface.modifyPortfolioDraft(modifyPortfolioDraft, portfolioId, portfolioMainLogPO);
	}
	
	/**
	 * 刪除投資組合
	 * @param portfolioId
	 * @param memberId
	 */
	public void deletePortfolioDraft(String portfolioId, String memberId) {
		log.debug("deletePortfolioDraft.findByPortfolioIdAndStatusCode({}, {}).", portfolioId, PortfolioConst.STATUS_CODE_DEFAULT);
		
		// 查詢portfolio_main_log, StatusCode: 0 的資料
		PortfolioMainLogPo  portfolioMainLogPO = portfolioQueryInterface.findByPortfolioIdAndStatusCode(portfolioId, PortfolioConst.STATUS_CODE_DEFAULT);
		if (portfolioMainLogPO == null) {
			log.error("deletePortfolioDraft portfolioMainLogPO is not exsit. portfolioId: {}.", portfolioId);
			throw new PortfolioException(ErrorStatus.ERROR_1001_2.msg(), ErrorStatus.ERROR_1001_2.code(), HttpStatus.BAD_REQUEST);
		}
		log.debug("portfolioCommandInterface.deletePortfolioDraft({}, {}, {}).", portfolioMainLogPO, portfolioId, memberId);
		portfolioCommandInterface.deletePortfolioDraft(portfolioMainLogPO, portfolioId, memberId);
	}
	
	/**
	 * 送審投資組合
	 * @param sendReviewPortfolioDraft
	 * @param portfolioId
	 * @param memberId
	 */
	public void sendReviewPortfolioDraft(String portfolioId, String memberId) {
		log.debug("sendReviewPortfolioDraft.findByPortfolioIdAndStatusCode({}, {}).", portfolioId, PortfolioConst.STATUS_CODE_DEFAULT);
		
		// 查詢portfolio_main_log, StatusCode: 0 的資料
		PortfolioMainLogPo  portfolioMainLogPO = portfolioQueryInterface.findByPortfolioIdAndStatusCode(portfolioId, PortfolioConst.STATUS_CODE_DEFAULT);
		if (portfolioMainLogPO == null) {
			log.error("sendReviewPortfolioDraft portfolioMainLogPO is not exsit. portfolioId: {}.", portfolioId);
			throw new PortfolioException(ErrorStatus.ERROR_1001_3.msg(), ErrorStatus.ERROR_1001_3.code(), HttpStatus.BAD_REQUEST);
		}
		log.debug("portfolioCommandInterface.sendReviewPortfolioDraft({}, {}, {}).", portfolioMainLogPO, portfolioId, memberId);
		portfolioCommandInterface.sendReviewPortfolioDraft(portfolioMainLogPO, portfolioId, memberId);
	}
	
	/**
	 * 審核投資組合
	 * @param reviewPortfolioDraft
	 * @param portfolioId
	 * @param memberId
	 */
	public void reviewPortfolioDraft(ReviewPortfolioDraftReqDto reviewPortfolioDraft, String portfolioId, String memberId, String reviewType) {
		log.debug("reviewPortfolioDraft.findByPortfolioIdAndStatusCode({}, {}).", portfolioId, PortfolioConst.STATUS_CODE_SEND_REVIEW);
		
		// 查詢portfolio_main_log, StatusCode: 1 的資料
		PortfolioMainLogPo  portfolioMainLogPO = portfolioQueryInterface.findByPortfolioIdAndStatusCode(portfolioId, PortfolioConst.STATUS_CODE_SEND_REVIEW);
		if (portfolioMainLogPO == null) {
			log.error("reviewPortfolioDraft portfolioMainLogPO is not exsit. portfolioId: {}.", portfolioId);
			throw new PortfolioException(ErrorStatus.ERROR_1001_4.msg(), ErrorStatus.ERROR_1001_4.code(), HttpStatus.BAD_REQUEST);
		}
		log.debug("portfolioCommandInterface.reviewPortfolioDraft({}, {}, {}, {}, {}, {}).", portfolioMainLogPO, portfolioId, memberId, reviewType
				, reviewPortfolioDraft.getStopDate(), reviewPortfolioDraft.getStopDesc());
		portfolioCommandInterface.reviewPortfolioDraft(portfolioMainLogPO, portfolioId, memberId, reviewType, reviewPortfolioDraft.getStopDate(), reviewPortfolioDraft.getStopDesc());
	}
	
	/**
	 * 取投資組合ID
	 * @return 14碼("PI"+系統日期之西元年月日+4碼流水號）
	 */
	private String getPortfolioId() {
		String portfolioIdStr = PortfolioIdPrefix.CUSTOM.getPreFix() + DateUtil.getFormatLocalDateTime();
		log.debug("getPortfolioId.findByPortfolioIdLike({}).", portfolioIdStr);
		List<PortfolioMainLogPo> portfolioMainPOList = portfolioQueryInterface.findByPortfolioIdLike(portfolioIdStr);
		// 取得投資組合目前筆數
		String newSequence = String.format("%04d", portfolioMainPOList.size() + PortfolioConst.LIST_SIZE_PLUS);
		return PortfolioIdPrefix.CUSTOM.getPreFix() + DateUtil.getFormatLocalDateTime() + newSequence;
	}
}
