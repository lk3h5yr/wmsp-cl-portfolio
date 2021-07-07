package com.cathay.wmsp.domain.persistence;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.cathay.wmsp.domain.facade.PortfolioCommandInterface;
import com.cathay.wmsp.domain.model.valueobjects.PortfolioConst;
import com.cathay.wmsp.domain.projecttions.PortfolioLabelPo;
import com.cathay.wmsp.domain.projecttions.PortfolioMainLogPo;
import com.cathay.wmsp.domain.projecttions.PortfolioMainPo;
import com.cathay.wmsp.domain.projecttions.PortfolioProposalPo;
import com.cathay.wmsp.domain.projecttions.PortfolioWeightsLogPo;
import com.cathay.wmsp.domain.projecttions.PortfolioWeightsPo;
import com.cathay.wmsp.infrastructure.repositories.PortfolioLabelLogRepository;
import com.cathay.wmsp.infrastructure.repositories.PortfolioLabelRepository;
import com.cathay.wmsp.infrastructure.repositories.PortfolioMainLogRepository;
import com.cathay.wmsp.infrastructure.repositories.PortfolioMainRepository;
import com.cathay.wmsp.infrastructure.repositories.PortfolioProposalLogRepository;
import com.cathay.wmsp.infrastructure.repositories.PortfolioProposalRepository;
import com.cathay.wmsp.infrastructure.repositories.PortfolioWeightsLogRepository;
import com.cathay.wmsp.infrastructure.repositories.PortfolioWeightsRepository;
import com.cathay.wmsp.infrastructure.util.DateUtil;
import com.cathay.wmsp.infrastructure.util.IdGenerator;
import com.cathay.wmsp.interfaces.exceptions.ErrorStatus;
import com.cathay.wmsp.interfaces.exceptions.PortfolioException;
import com.cathay.wmsp.interfaces.rest.dto.AddPortfolioDraftReqDto;
import com.cathay.wmsp.interfaces.rest.dto.CommodityListDto;
import com.cathay.wmsp.interfaces.rest.dto.ModifyPortfolioDraftReqDto;

import lombok.extern.slf4j.Slf4j;

/**
 * 模型詳細邏輯
 */
@Slf4j
@Repository
public class PortfolioCommandRepositoryImpI implements PortfolioCommandInterface {

	@Autowired
	PortfolioMainRepository portfolioMainRepository;
	
	@Autowired
	PortfolioMainLogRepository portfolioMainLogRepository;
	
	@Autowired
	PortfolioWeightsRepository portfolioWeightsRepository;
	
	@Autowired
	PortfolioWeightsLogRepository portfolioWeightsLogRepository;
	
	@Autowired
	PortfolioLabelRepository portfolioLabelRepository;
	
	@Autowired
	PortfolioLabelLogRepository portfolioLabelLogRepository;
	
	@Autowired
	PortfolioProposalRepository portfolioProposalRepository;
	
	@Autowired
	PortfolioProposalLogRepository portfolioProposalLogRepository;
	
	/**
	 * 新增投資組合及商品池標的
	 */
	@Transactional
	public void addPortfolioDraft(AddPortfolioDraftReqDto addPortfolioDraft, String portfolioId) {
		log.debug("PortfolioCommandRepositoryImpI addPortfolioDraft. addPortfolioDraft: {}, portfolioId: {}.", addPortfolioDraft, portfolioId);
		
		String stage = PortfolioConst.STAGE_DEFAULT;
		String logId = IdGenerator.nextId();
		LocalDateTime localDateTime = DateUtil.getLocalDateTime();
		// 異動人
		String memberId = addPortfolioDraft.getMemberId();
		// 商品池清單
		List<CommodityListDto> commodityListDtoList = addPortfolioDraft.getCommodityList();
		try {
			// 新增資料: portfolio_main_log
			log.debug("addPortfolioDraft.setPortfolioMainLog({}, {}, {}, {}, {}).", logId, portfolioId, localDateTime, memberId, addPortfolioDraft);
			this.setPortfolioMainLog(logId, portfolioId, localDateTime, memberId, addPortfolioDraft, stage);
			
			// 新增資料: portfolio_weights_log
			log.debug("addPortfolioDraft.setPortfolioWeightsLog({}, {}, {}, {}, {}).", logId, portfolioId, localDateTime, memberId, commodityListDtoList);
			this.setPortfolioWeightsLog(logId, portfolioId, localDateTime, memberId, commodityListDtoList, stage);
		} catch (Exception e) {
			log.error("addPortfolioDraft Error. ErrorMessage: {}", e);
			if (PortfolioConst.STAGE_INSERT.equals(stage)) {
				throw new PortfolioException(ErrorStatus.ERROR_0002.msg(), ErrorStatus.ERROR_0002.code(), HttpStatus.BAD_REQUEST);
			} else {
				throw e;
			}
		}
	}
	
	/**
	 * 修改投資組合基本資料及投資標的
	 */
	@Transactional
	public void modifyPortfolioDraft(ModifyPortfolioDraftReqDto modifyPortfolioDraft, String portfolioId, PortfolioMainLogPo  portfolioMainLogPo) {
		log.debug("PortfolioCommandRepositoryImpI modifyPortfolioDraft. modifyPortfolioDraft: {}, portfolioId: {}, portfolioMainLogPo: {}."
				, modifyPortfolioDraft, portfolioId, portfolioMainLogPo.toString());
		
		String stage = PortfolioConst.STAGE_DEFAULT;
		LocalDateTime localDateTime = DateUtil.getLocalDateTime();
		// 異動人
		String memberId = modifyPortfolioDraft.getMemberId();
		// 商品池清單
		List<CommodityListDto> commodityListDtoList = modifyPortfolioDraft.getCommodityList();
		// 更新資料: portfolio_main_log
		portfolioMainLogPo.setChangeId(memberId);
		portfolioMainLogPo.setChangeDateTime(localDateTime);
		portfolioMainLogPo.setLogDateTime(localDateTime);
		
		try {
			log.debug("modifyPortfolioDraft.insertPortfolioMainLog({}).", portfolioMainLogPo);
			portfolioMainLogRepository.updatePortfolioMainLog(portfolioMainLogPo, stage);
			
			List<PortfolioWeightsLogPo> portfolioWeightsLogPOList = portfolioWeightsLogRepository.findBylogIdAndPortfolioId(portfolioMainLogPo.getLogId(), portfolioId);
			if (!CollectionUtils.isEmpty(portfolioWeightsLogPOList)) {
				// 取其中一筆資料的logId
				String logId = portfolioWeightsLogPOList.get(0).getLogId();
				
				log.debug("modifyPortfolioDraft.deletePortfolioWeightsLog({}).", portfolioWeightsLogPOList);
				// 刪除資料: portfolio_weights_log
				portfolioWeightsLogRepository.deletePortfolioWeightsLog(portfolioWeightsLogPOList, stage);
				
				log.debug("modifyPortfolioDraft.setPortfolioWeightsLog({}, {}, {}, {}, {}).", logId, portfolioId, localDateTime, memberId, commodityListDtoList);
				// 新增資料: portfolio_weights_log
				this.setPortfolioWeightsLog(logId, portfolioId, localDateTime, memberId, commodityListDtoList, stage);
			}
		} catch (Exception e) {
			log.error("modifyPortfolioDraft Error. ErrorMessage: {}", e);
			if (PortfolioConst.STAGE_INSERT.equals(stage)) {
				throw new PortfolioException(ErrorStatus.ERROR_0002.msg(), ErrorStatus.ERROR_0002.code(), HttpStatus.BAD_REQUEST);
			} else if (PortfolioConst.STAGE_DELETE.equals(stage)) {
				throw new PortfolioException(ErrorStatus.ERROR_0004.msg(), ErrorStatus.ERROR_0004.code(), HttpStatus.BAD_REQUEST);
			} else {
				throw e;
			}
		}
	}
	
	/**
	 * 刪除投資組合
	 */
	@Transactional
	public void deletePortfolioDraft(PortfolioMainLogPo portfolioMainLogPo, String portfolioId, String memberId) {
		log.debug("PortfolioCommandRepositoryImpI deletePortfolioDraft. portfolioMainLogPo: {}, portfolioId: {}, memberId: {}."
				, portfolioMainLogPo, portfolioId, memberId);
		
		String stage = PortfolioConst.STAGE_DEFAULT;
		LocalDateTime localDateTime = DateUtil.getLocalDateTime();
		try {
			// 更新資料: portfolio_main_log
			portfolioMainLogPo.setStatusCode(PortfolioConst.STATUS_CODE_DELETE);
			portfolioMainLogPo.setLogDateTime(localDateTime);
			portfolioMainLogPo.setDeleteId(memberId);
			portfolioMainLogPo.setDeleteDateTime(localDateTime);
			log.debug("deletePortfolioDraft.insertPortfolioMainLog({}).", portfolioMainLogPo);
			portfolioMainLogRepository.updatePortfolioMainLog(portfolioMainLogPo, stage);
		} catch (Exception e) {
			log.error("deletePortfolioDraft Error. ErrorMessage: {}", e);
			if (PortfolioConst.STAGE_UPDATE.equals(stage)) {
				throw new PortfolioException(ErrorStatus.ERROR_0003.msg(), ErrorStatus.ERROR_0003.code(), HttpStatus.BAD_REQUEST);
			} else {
				throw e;
			}
		}
	}
	
	/**
	 * 送審投資組合
	 */
	@Transactional
	public void sendReviewPortfolioDraft(PortfolioMainLogPo portfolioMainLogPo, String portfolioId, String memberId){
		log.debug("PortfolioCommandRepositoryImpI sendReviewPortfolioDraft. portfolioMainLogPo: {}, portfolioId: {}, memberId: {}."
				, portfolioMainLogPo, portfolioId, memberId);
		
		String stage = PortfolioConst.STAGE_DEFAULT;
		LocalDateTime localDateTime = DateUtil.getLocalDateTime();
		try {
			// 更新資料: portfolio_main_log
			portfolioMainLogPo.setStatusCode(PortfolioConst.STATUS_CODE_SEND_REVIEW);
			portfolioMainLogPo.setLogDateTime(localDateTime);
			portfolioMainLogPo.setSendReviewId(memberId);
			portfolioMainLogPo.setSendReviewDateTime(localDateTime);
			log.debug("sendReviewPortfolioDraft.insertPortfolioMainLog({}).", portfolioMainLogPo);
			portfolioMainLogRepository.updatePortfolioMainLog(portfolioMainLogPo, stage);
		} catch (Exception e) {
			log.error("sendReviewPortfolioDraft Error. ErrorMessage: {}", e);
			if (PortfolioConst.STAGE_UPDATE.equals(stage)) {
				throw new PortfolioException(ErrorStatus.ERROR_0003.msg(), ErrorStatus.ERROR_0003.code(), HttpStatus.BAD_REQUEST);
			} else {
				throw e;
			}
		}
	}
	
	/**
	 * 審核投資組合
	 */
	@Transactional
	public void reviewPortfolioDraft(PortfolioMainLogPo portfolioMainLogPo, String portfolioId, String memberId, String reviewType, String stopDate, String stopDesc) {
		log.debug("PortfolioCommandRepositoryImpI reviewPortfolioDraft. portfolioMainLogPo: {}, portfolioId: {}, memberId: {}, reviewType: {}, stopDate: {}, stopDesc: {}."
				, portfolioMainLogPo, portfolioId, memberId, reviewType, stopDate, stopDesc);
		
		LocalDateTime localDateTime = DateUtil.getLocalDateTime();
		String stage = PortfolioConst.STAGE_DEFAULT;
		try {
			// 審核同意
			if ("Y".equals(reviewType)) {
				// 查詢portfolio_main_log, StatusCode: 9 的資料
				PortfolioMainLogPo  portfolioMainLogPoStatus9 = portfolioMainLogRepository.findByPortfolioIdAndStatusCode(portfolioId, PortfolioConst.STATUS_CODE_SEND_REVIEW);
				if (portfolioMainLogPoStatus9 != null) {
					portfolioMainLogPoStatus9.setStatusCode(PortfolioConst.STATUS_CODE_REVIEW_END);
					portfolioMainLogPoStatus9.setStopDate(localDateTime);
					//TODO: END_DESC = END_DESC  < 目前table裡面沒有這個欄位 
				}
				// 刪除正式檔PORTFOLIO_MAIN、PORTFOLIO_WEIGHTS、PORTFOLIO_LABEL、PORTFOLIO_PROPOSAL
				PortfolioMainPo portfolioMainPo = portfolioMainRepository.findByPortfolioId(portfolioId);
				portfolioMainRepository.deletePortfolioMain(portfolioMainPo, stage);
				PortfolioWeightsPo portfolioWeightsPo = portfolioWeightsRepository.findByPortfolioId(portfolioId);
				portfolioWeightsRepository.deletePortfolioWeights(portfolioWeightsPo, stage);
				PortfolioLabelPo portfolioLabelPo = portfolioLabelRepository.findByPortfolioId(portfolioId);
				portfolioLabelRepository.deletePortfolioLabel(portfolioLabelPo, stage);
				PortfolioProposalPo portfolioProposalPo = portfolioProposalRepository.findByPortfolioId(portfolioId);
				portfolioProposalRepository.deletePortfolioProposal(portfolioProposalPo, stage);
				// 新增資料: portfolio_main
				this.setPortfolioMain(portfolioMainLogPo, memberId, stopDate, stopDesc, localDateTime, stage);
			} 
			// 審核退回
			else if ("N".equals(reviewType)) {
				
			}
		} catch(Exception e) {
			log.error("reviewPortfolioDraft Error. ErrorMessage: {}", e);
			if (PortfolioConst.STAGE_INSERT.equals(stage)) {
				throw new PortfolioException(ErrorStatus.ERROR_0002.msg(), ErrorStatus.ERROR_0002.code(), HttpStatus.BAD_REQUEST);
			} else if (PortfolioConst.STAGE_UPDATE.equals(stage)) {
				throw new PortfolioException(ErrorStatus.ERROR_0003.msg(), ErrorStatus.ERROR_0003.code(), HttpStatus.BAD_REQUEST);
			} else if (PortfolioConst.STAGE_DELETE.equals(stage)) {
				throw new PortfolioException(ErrorStatus.ERROR_0004.msg(), ErrorStatus.ERROR_0004.code(), HttpStatus.BAD_REQUEST);
			} else {
				throw e;
			}
		}
	}
	
	/**
	 * 設定資料: PortfolioWeightsLog
	 */
	private void setPortfolioWeightsLog(String logId, String portfolioId, LocalDateTime localDateTime, String membber, List<CommodityListDto> commodityListDtoList, String stage) {
		log.debug("PortfolioCommandRepositoryImpI insertPortfolioWeightsLog. logId: {}, portfolioId: {}, localDateTime: {}, membber: {}, commodityListDtoList: {}."
				, logId, portfolioId, localDateTime, membber, commodityListDtoList);
		List<PortfolioWeightsLogPo> portfolioWeightsLogPOList = new ArrayList<>();
		commodityListDtoList.forEach( temp -> {
			PortfolioWeightsLogPo portfolioWeightsLogPO = new PortfolioWeightsLogPo();
			portfolioWeightsLogPO.setLogId(logId);
			portfolioWeightsLogPO.setLogDateTime(localDateTime);
			portfolioWeightsLogPO.setPortfolioId(portfolioId);
			portfolioWeightsLogPO.setCommodityId(temp.getCommodityId());
			portfolioWeightsLogPO.setCommodityPoolId(temp.getCommodityPoolId());
			portfolioWeightsLogPO.setCommodityWeight(new BigDecimal(temp.getCommodityWeights()));
			portfolioWeightsLogPO.setChangeId(membber);
			portfolioWeightsLogPO.setChangeDateTime(localDateTime);
			portfolioWeightsLogPOList.add(portfolioWeightsLogPO);
			log.debug("PortfolioWeightsLogPO: {}", portfolioWeightsLogPO);
		});
		portfolioWeightsLogRepository.insertPortfolioWeightsLog(portfolioWeightsLogPOList, stage);
	}
	
	/**
	 * 設定資料:PortfolioMainLog
	 */
	private void setPortfolioMainLog(String logId, String portfolioId, LocalDateTime chengeDateTime, String membber, AddPortfolioDraftReqDto addPortfolioDraft, String stage) {
		log.debug("PortfolioCommandRepositoryImpI insertportfolioMainLogPo. logId: {}, portfolioId: {}, chengeDateTime: {}, membber: {}, addPortfolioDraft: {}."
				, logId, portfolioId, chengeDateTime, membber, addPortfolioDraft.toString());
		PortfolioMainLogPo portfolioMainLogPo = new PortfolioMainLogPo();
		portfolioMainLogPo.setPortfolioId(portfolioId);
		portfolioMainLogPo.setLogId(logId);
		portfolioMainLogPo.setLogDateTime(chengeDateTime);
		portfolioMainLogPo.setStopCheck(PortfolioConst.STOP_CHECK_DEFAULT);
		portfolioMainLogPo.setStatusCode(PortfolioConst.STATUS_CODE_DEFAULT);
		portfolioMainLogPo.setChangeId(membber);
		portfolioMainLogPo.setChangeDateTime(chengeDateTime);
		portfolioMainLogPo.setInvestmentAmount(addPortfolioDraft.getInvestmentAmount());
		portfolioMainLogPo.setPortfolioName(addPortfolioDraft.getPortfolioName());
		portfolioMainLogPo.setPortfolioTemplateId(addPortfolioDraft.getPortfolioTemplateId());
		portfolioMainLogPo.setPortfolioName(addPortfolioDraft.getPortfolioName());
		portfolioMainLogPo.setCommodityStock(addPortfolioDraft.getCommodityStock());
		portfolioMainLogPo.setCommodityEtf(addPortfolioDraft.getCommodityEtf());
		portfolioMainLogPo.setCommodityFund(addPortfolioDraft.getCommodityFund());
		portfolioMainLogPo.setReleaseDate(new Date());

		log.debug("portfolioMainLogPo: {}", portfolioMainLogPo);
		portfolioMainLogRepository.insertPortfolioMainLog(portfolioMainLogPo, stage);
	}
	
	private void setPortfolioMain(PortfolioMainLogPo portfolioMainLogPo, String memberId, String stopDate, String stopDesc, LocalDateTime localDateTime, String stage) {
		PortfolioMainPo portfolioMainPo = new PortfolioMainPo();
		portfolioMainPo.setLogId(portfolioMainLogPo.getLogId());
		portfolioMainPo.setPortfolioId(portfolioMainLogPo.getPortfolioId());
		portfolioMainPo.setInvestmentAmount(portfolioMainLogPo.getInvestmentAmount());
		portfolioMainPo.setPortfolioName(portfolioMainLogPo.getPortfolioName());
		portfolioMainPo.setPortfolioTemplateId(portfolioMainLogPo.getPortfolioTemplateId());
		portfolioMainPo.setReleaseDate(portfolioMainLogPo.getReleaseDate());
		portfolioMainPo.setRiskLevel(portfolioMainLogPo.getRiskLevel());
		portfolioMainPo.setStopCheck(PortfolioConst.STOP_CHECK_DEFAULT);
		portfolioMainPo.setStopDesc(StringUtils.isEmpty(stopDesc) ? "" : stopDesc );
		portfolioMainPo.setStopDate(StringUtils.isEmpty(stopDesc) ? null : DateUtil.parseDate(stopDate));
		portfolioMainPo.setCommodityEtf(portfolioMainLogPo.getCommodityEtf());
		portfolioMainPo.setCommodityStock(portfolioMainLogPo.getCommodityStock());
		portfolioMainPo.setCommodityFund(portfolioMainLogPo.getCommodityFund());
		log.debug("portfolioMainPo: {}", portfolioMainPo);
		portfolioMainRepository.insertPortfolioMain(portfolioMainPo, stage);
	}

}
