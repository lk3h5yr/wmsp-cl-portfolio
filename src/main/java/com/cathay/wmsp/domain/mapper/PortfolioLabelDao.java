package com.cathay.wmsp.domain.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cathay.wmsp.domain.projecttions.PortfolioLabelPo;

@Repository
public interface PortfolioLabelDao extends CrudRepository<PortfolioLabelPo, String>, JpaRepository<PortfolioLabelPo, String> {

	PortfolioLabelPo findByPortfolioId(String portfolioId);
}
