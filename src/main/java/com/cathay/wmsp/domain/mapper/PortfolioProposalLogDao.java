package com.cathay.wmsp.domain.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cathay.wmsp.domain.projecttions.PortfolioProposalLogPo;

@Repository
public interface PortfolioProposalLogDao extends CrudRepository<PortfolioProposalLogPo, String>,JpaRepository<PortfolioProposalLogPo, String> {

}
