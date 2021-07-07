package com.cathay.wmsp.domain.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cathay.wmsp.domain.projecttions.PortfolioLabelLogPo;

@Repository
public interface PortfolioLabelLogDao extends CrudRepository<PortfolioLabelLogPo, String>, JpaRepository<PortfolioLabelLogPo, String> {

}
