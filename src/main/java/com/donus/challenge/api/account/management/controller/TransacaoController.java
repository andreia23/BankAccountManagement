package com.donus.challenge.api.account.management.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.donus.challenge.api.account.management.model.dto.TransacaoDTO;
import com.donus.challenge.api.account.management.model.entity.Transacao;
import com.donus.challenge.api.account.management.service.TransacaoService;

import io.swagger.annotations.ApiOperation;

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
	 * @return
	 */
	@ApiOperation(value = "Obtém todas as transações")
	@RequestMapping(value = "/v1/all-transactions", method = RequestMethod.GET)
	public ResponseEntity<List<TransacaoDTO>> findALL() {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		List<TransacaoDTO> listDto = new ArrayList<TransacaoDTO>();
		List<Transacao> list = transacaoService.findALL();
		for (Transacao transacao : list) {

			TransacaoDTO transacaoDTO = modelMapper.map(transacao, TransacaoDTO.class);
			listDto.add(transacaoDTO);

		}
		return ResponseEntity.ok().body(listDto);

	}
}
