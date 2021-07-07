package com.cathay.wmsp.domain.mapper;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cathay.wmsp.domain.projecttions.PortfolioWeightsLogPo;

@Repository
public interface PortfolioWeightsLogDao extends CrudRepository<PortfolioWeightsLogPo, String>, JpaRepository<PortfolioWeightsLogPo, String> {

	List<PortfolioWeightsLogPo> findBylogIdAndPortfolioId(String logId, String portfolioId);
}
