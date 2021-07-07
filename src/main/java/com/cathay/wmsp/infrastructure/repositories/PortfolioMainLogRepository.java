package com.cathay.wmsp.infrastructure.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cathay.wmsp.domain.mapper.PortfolioMainLogDao;
import com.cathay.wmsp.domain.model.valueobjects.PortfolioConst;
import com.cathay.wmsp.domain.projecttions.PortfolioMainLogPo;

@Repository
public class PortfolioMainLogRepository {

	@Autowired
	PortfolioMainLogDao portfolioMainLogDao;

	public void insertPortfolioMainLog(PortfolioMainLogPo portfolioMainLogPo, String stage) {
		stage = PortfolioConst.STAGE_INSERT;
		portfolioMainLogDao.save(portfolioMainLogPo);
		portfolioMainLogDao.flush();
	}

	public void updatePortfolioMainLog(PortfolioMainLogPo portfolioMainLogPo, String stage) {
		stage = PortfolioConst.STAGE_UPDATE;
		portfolioMainLogDao.save(portfolioMainLogPo);
		portfolioMainLogDao.flush();
	}

	public PortfolioMainLogPo findByPortfolioId(String portfolioId) {
		return portfolioMainLogDao.findByPortfolioId(portfolioId);
	}

	public PortfolioMainLogPo findByPortfolioIdAndStatusCode(String portfolioId, String statusCode) {
		return portfolioMainLogDao.findByPortfolioIdAndStatusCode(portfolioId, statusCode);
	}

	public List<PortfolioMainLogPo> findByOrderByPortfolioIdAsc() {
		return portfolioMainLogDao.findByOrderByPortfolioIdAsc();
	}
}
