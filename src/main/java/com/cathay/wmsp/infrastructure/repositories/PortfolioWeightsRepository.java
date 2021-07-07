package com.cathay.wmsp.infrastructure.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cathay.wmsp.domain.mapper.PortfolioWeightsDao;
import com.cathay.wmsp.domain.model.valueobjects.PortfolioConst;
import com.cathay.wmsp.domain.projecttions.PortfolioWeightsPo;

@Repository
public class PortfolioWeightsRepository {

	@Autowired
	PortfolioWeightsDao portfolioWeightsDao;

	public void deletePortfolioWeights(PortfolioWeightsPo portfolioWeightsPo, String stage) {
		if (portfolioWeightsPo != null) {
			stage = PortfolioConst.STAGE_DELETE;
			portfolioWeightsDao.delete(portfolioWeightsPo);
			portfolioWeightsDao.flush();
		}
	}

	public PortfolioWeightsPo findByPortfolioId(String portfolioId) {
		return portfolioWeightsDao.findByPortfolioId(portfolioId);
	}
}
