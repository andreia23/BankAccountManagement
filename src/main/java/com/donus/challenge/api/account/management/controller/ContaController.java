package com.donus.challenge.api.account.management.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donus.challenge.api.account.management.model.dto.ContaDTO;
import com.donus.challenge.api.account.management.service.ContaService;

//@Api(value="API REST Produtos")
@RestController
@RequestMapping("/produtos")
public class ContaController {

	@Autowired
	private ContaService contaService;

//	@ApiOperation(value = "Salva uma conta")
//	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
//	public ResponseEntity<Conta> createProduto(@RequestBody Conta conta) {
//		return ResponseEntity.ok().body(this.contaService.saveConta(conta));
//
//	}
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ContaDTO> insert( @RequestBody ContaDTO conta) {
				
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		ContaDTO userDTO = modelMapper.map(conta, ContaDTO.class);
		userDTO = contaService.insert(userDTO);

		return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
	}
}
