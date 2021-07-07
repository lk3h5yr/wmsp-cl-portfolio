package com.cathay.wmsp.domain.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cathay.wmsp.domain.projecttions.PortfolioProposalPo;

@Repository
public interface PortfolioProposalDao extends CrudRepository<PortfolioProposalPo, String>,JpaRepository<PortfolioProposalPo, String> {

	PortfolioProposalPo findByPortfolioId(String portfolioId);
}
