package com.cathay.wmsp.infrastructure.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.cathay.wmsp.domain.mapper.PortfolioWeightsLogDao;
import com.cathay.wmsp.domain.model.valueobjects.PortfolioConst;
import com.cathay.wmsp.domain.projecttions.PortfolioWeightsLogPo;

@Repository
public class PortfolioWeightsLogRepository {

	@Autowired
	PortfolioWeightsLogDao portfolioWeightsLogDao;

	/**
	 * 新增資料:PortfolioWeightsLog
	 * 
	 * @param portfolioWeightsLogPOList
	 */
	public void insertPortfolioWeightsLog(List<PortfolioWeightsLogPo> portfolioWeightsLogPOList, String stage) {
		stage = PortfolioConst.STAGE_INSERT;
		portfolioWeightsLogDao.saveAll(portfolioWeightsLogPOList);
		portfolioWeightsLogDao.flush();
	}

	/**
	 * 刪除資料:PortfolioWeightsLog
	 * 
	 * @param portfolioWeightsLog
	 * @param stage
	 */
	public void deletePortfolioWeightsLog(List<PortfolioWeightsLogPo> portfolioWeightsLogList, String stage) {
		if (!CollectionUtils.isEmpty(portfolioWeightsLogList)) {
			stage = PortfolioConst.STAGE_DELETE;
			portfolioWeightsLogDao.deleteAll(portfolioWeightsLogList);
			portfolioWeightsLogDao.flush();
		}
	}

	/**
	 * 取得資料:PortfolioWeightsLog
	 * 
	 * @param logId
	 * @param portfolioId
	 * @return
	 */
	public List<PortfolioWeightsLogPo> findBylogIdAndPortfolioId(String logId, String portfolioId) {
		return portfolioWeightsLogDao.findBylogIdAndPortfolioId(logId, portfolioId);
	}

}
