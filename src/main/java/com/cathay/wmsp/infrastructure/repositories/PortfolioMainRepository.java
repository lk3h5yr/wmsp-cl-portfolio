package com.cathay.wmsp.infrastructure.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cathay.wmsp.domain.mapper.PortfolioMainDao;
import com.cathay.wmsp.domain.model.valueobjects.PortfolioConst;
import com.cathay.wmsp.domain.projecttions.PortfolioMainPo;

@Repository
public class PortfolioMainRepository {

	@Autowired
	PortfolioMainDao portfolioMainDao;

	public void insertPortfolioMain(PortfolioMainPo portfolioMainPo, String stage) {
		stage = PortfolioConst.STAGE_INSERT;
		portfolioMainDao.save(portfolioMainPo);
		portfolioMainDao.flush();
	}
	
	public void deletePortfolioMain(PortfolioMainPo portfolioMainPo, String stage) {
		if (portfolioMainPo != null) {
			stage = PortfolioConst.STAGE_DELETE;
			portfolioMainDao.delete(portfolioMainPo);
			portfolioMainDao.flush();	
		}
	}

	public PortfolioMainPo findByPortfolioId(String portfolioId) {
		return portfolioMainDao.findByPortfolioId(portfolioId);
	}

}
