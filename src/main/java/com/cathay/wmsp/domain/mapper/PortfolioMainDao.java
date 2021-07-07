package com.cathay.wmsp.domain.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cathay.wmsp.domain.projecttions.PortfolioMainPo;

@Repository
public interface PortfolioMainDao extends CrudRepository<PortfolioMainPo, String>, JpaRepository<PortfolioMainPo, String> {

	PortfolioMainPo findByPortfolioId(String portfolioId);
}
