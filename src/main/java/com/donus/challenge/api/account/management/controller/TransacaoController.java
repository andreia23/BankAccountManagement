package com.donus.challenge.api.account.management.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.donus.challenge.api.account.management.model.dto.TransacaoDTO;
import com.donus.challenge.api.account.management.model.request.TransacaoRequest;
import com.donus.challenge.api.account.management.service.TransacaoService;

/**
 * @author andreia
 *
 */
@RestController
public class TransacaoController {

	private TransacaoService transacaoService;

	@Autowired
	public TransacaoController(TransacaoService transacaoService) {
		this.transacaoService = transacaoService;
	}

	/**
	 * @param transacaoRequest
	 * @return
	 */
//	@RequestMapping(value = "/v1/transfer", method = RequestMethod.POST)
//	public ResponseEntity<TransacaoDTO> transfer(@Valid @RequestBody TransacaoRequest transacaoRequest) {
//
//		ModelMapper modelMapper = new ModelMapper();
//		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//
//		TransacaoDTO transacaoDTO = modelMapper.map(transacaoRequest, TransacaoDTO.class);
//
//		transacaoService.createTransaction(transacaoDTO);
//		return ResponseEntity.status(HttpStatus.CREATED).body(transacaoDTO);
//
//	}

}
