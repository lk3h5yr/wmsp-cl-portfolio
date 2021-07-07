package com.cathay.wmsp.domain.model.valueobjects;


import lombok.Getter;

public enum PortfolioIdPrefix {
  CUSTOM("PI");

  @Getter 
  private String preFix;

  private PortfolioIdPrefix(String preFix) {
    this.preFix = preFix;
  }
}
