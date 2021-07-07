package com.cathay.wmsp.domain.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cathay.wmsp.domain.projecttions.PortfolioWeightsPo;

@Repository
public interface PortfolioWeightsDao extends CrudRepository<PortfolioWeightsPo, String>, JpaRepository<PortfolioWeightsPo, String> {

	PortfolioWeightsPo findByPortfolioId(String portfolioId);
}
