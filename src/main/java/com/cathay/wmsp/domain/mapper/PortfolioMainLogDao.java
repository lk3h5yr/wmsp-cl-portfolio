package com.cathay.wmsp.domain.mapper;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cathay.wmsp.domain.projecttions.PortfolioMainLogPo;

@Repository
public interface PortfolioMainLogDao extends CrudRepository<PortfolioMainLogPo, String>, JpaRepository<PortfolioMainLogPo, String> {

	PortfolioMainLogPo findByPortfolioId(String portfolioId);
	
	PortfolioMainLogPo findByPortfolioIdAndStatusCode(String portfolioId, String statusCode);
	
	List<PortfolioMainLogPo> findByOrderByPortfolioIdAsc();
}
