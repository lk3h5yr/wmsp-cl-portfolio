package com.cathay.wmsp.infrastructure.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cathay.wmsp.domain.mapper.PortfolioProposalDao;
import com.cathay.wmsp.domain.model.valueobjects.PortfolioConst;
import com.cathay.wmsp.domain.projecttions.PortfolioProposalPo;

@Repository
public class PortfolioProposalRepository {

	@Autowired
	PortfolioProposalDao portfolioProposalDao;

	public void deletePortfolioProposal(PortfolioProposalPo portfolioProposalPo, String stage) {
		if (portfolioProposalPo != null) {
			stage = PortfolioConst.STAGE_DELETE;
			portfolioProposalDao.delete(portfolioProposalPo);
			portfolioProposalDao.flush();
		}
	}

	public PortfolioProposalPo findByPortfolioId(String portfolioId) {
		return portfolioProposalDao.findByPortfolioId(portfolioId);
	}
}
