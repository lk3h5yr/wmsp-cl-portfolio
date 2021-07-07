package com.cathay.wmsp.infrastructure.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cathay.wmsp.domain.mapper.PortfolioLabelDao;
import com.cathay.wmsp.domain.model.valueobjects.PortfolioConst;
import com.cathay.wmsp.domain.projecttions.PortfolioLabelPo;

@Repository
public class PortfolioLabelRepository {

	@Autowired
	PortfolioLabelDao portfolioLabelDao;

	public void deletePortfolioLabel(PortfolioLabelPo portfolioLabelPo, String stage) {
		if (portfolioLabelPo != null) {
			stage = PortfolioConst.STAGE_DELETE;
			portfolioLabelDao.delete(portfolioLabelPo);
			portfolioLabelDao.flush();
		}
	}

	public PortfolioLabelPo findByPortfolioId(String portfolioId) {
		return portfolioLabelDao.findByPortfolioId(portfolioId);
	}
}
