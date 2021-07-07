package com.cathay.wmsp.interfaces.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cathay.wmsp.application.internal.commandgateways.PortfolioService;
import com.cathay.wmsp.interfaces.rest.dto.AddPortfolioDraftReqDto;
import com.cathay.wmsp.interfaces.rest.dto.DeletePortfolioDraftReqDto;
import com.cathay.wmsp.interfaces.rest.dto.ModifyPortfolioDraftReqDto;
import com.cathay.wmsp.interfaces.rest.dto.ReviewPortfolioDraftReqDto;
import com.cathay.wmsp.interfaces.rest.dto.SendReviewPortfolioDraftReqDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RestController
@Slf4j
@Validated
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = { "投資組合服務介面API" })
public class PortfolioController {
	
	@Autowired
	PortfolioService portfolioService;

	/**
	 * 新增投資組合及商品標的
	 * @param AddPortfolioDraftReqDto
	 * @return
	 */
	@PostMapping("/portfolio-draft")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "新增成功") })
	public void addPortfolioDraft(@Valid @RequestBody AddPortfolioDraftReqDto addPortfolioDraft) {
		log.info("PortfolioCommandController addPortfolioDraft. URL: {}, AddPortfolioDraftReqDto: {}.", 
				"/portfolio-draft", addPortfolioDraft.toString());
		// 新增投資組合及商品池標的
		portfolioService.addPortfolioDraft(addPortfolioDraft);
	}
	
	/**
	 * 修改投資組合基本資料及投資標的
	 * @param ModifyPortfolioDraftReqDto
	 * @return
	 */
	@PutMapping("/portfolio-draft/{portfolio_id}/")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "修改成功") })
	public void modifyPortfolioDraft(
			@Parameter(description = "商品組合ID") @PathVariable(value = "portfolio_id") String portfolioId, 
			@Valid @RequestBody ModifyPortfolioDraftReqDto modifyPortfolioDraft) {
		log.info("PortfolioCommandController modifyPortfolioDraft. URL: {}, ModifyPortfolioDraftReqDto: {}."
				, "/portfolio-draft/{" + portfolioId + "}/", modifyPortfolioDraft.toString() );
		// 修改投資組合基本資料及投資標的
		portfolioService.modifyPortfolioDraft(modifyPortfolioDraft, portfolioId);
	}
	
	/**
	 * 刪除投資組合
	 * @param DeletePortfolioDraftReqDto
	 * @return
	 */
	@PostMapping("/portfolio-draft/{portfolio_id}/mark-delete/")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "刪除成功") })
	public void deletePortfolioDraft(
			@Parameter(description = "商品組合ID") @PathVariable(value = "portfolio_id") String portfolioId, 
			@Valid @RequestBody DeletePortfolioDraftReqDto deletePortfolioDraft) {
		log.info("PortfolioCommandController deletePortfolioDraft. URL: {}, DeletePortfolioDraftReqDto: {}."
				, "/portfolio-draft/{" + portfolioId + "}/mark-delete/", deletePortfolioDraft.toString() );
		// 刪除投資組合
		portfolioService.deletePortfolioDraft(portfolioId, deletePortfolioDraft.getMemberId());
	}
	
	/**
	 * 送審投資組合
	 * @param SendReviewPortfolioDraftReqDto
	 * @return
	 */
	@PutMapping("/portfolio-draft/{portfolio_id}/send-review/")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "送審成功") })
	public void sendReviewPortfolioDraft(
			@Parameter(description = "商品組合ID") @PathVariable(value = "portfolio_id") String portfolioId, 
			@Valid @RequestBody SendReviewPortfolioDraftReqDto sendReviewPortfolioDraft) {
		log.info("PortfolioCommandController sendReviewPortfolioDraft. URL: {}, SendReviewPortfolioDraftReqDto: {}."
				, "/portfolio-draft/{" + portfolioId + "}/send-review/", sendReviewPortfolioDraft.toString() );
		// 送審投資組合
		portfolioService.sendReviewPortfolioDraft(portfolioId, sendReviewPortfolioDraft.getMemberId());
	}
	
	/**
	 * 審核投資組合
	 * @param ReviewPortfolioDraftReqDto
	 * @return
	 */
	@PutMapping("/portfolio-draft/{portfolio_id}/review")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "審核成功") })
	public void reviewPortfolioDraft(
			@Parameter(description = "商品組合ID") @PathVariable(value = "portfolio_id") String portfolioId, 
			@Valid @RequestBody ReviewPortfolioDraftReqDto reviewPortfolioDraft) {
		log.info("PortfolioCommandController reviewPortfolioDraft. URL: {}, ReviewPortfolioDraftReqDto: {}."
				, "/portfolio-draft/{" + portfolioId + "}/review", reviewPortfolioDraft.toString() );
		// 審核投資組合
		portfolioService.reviewPortfolioDraft(reviewPortfolioDraft, portfolioId, reviewPortfolioDraft.getMemberId(), reviewPortfolioDraft.getReviewType());
	}
}
