package com.cathay.wmsp.infrastructure.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cathay.wmsp.domain.mapper.PortfolioProposalLogDao;

@Repository
public class PortfolioProposalLogRepository {

	@Autowired
	PortfolioProposalLogDao portfolioProposalLogDao;
}
