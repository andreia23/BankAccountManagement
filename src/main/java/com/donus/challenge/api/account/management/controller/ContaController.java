package com.donus.challenge.api.account.management.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.donus.challenge.api.account.management.model.dto.ClienteDTO;
import com.donus.challenge.api.account.management.model.dto.ContaDTO;
import com.donus.challenge.api.account.management.service.ContaService;

//@Api(value="API REST Produtos")
@RestController
public class ContaController {

	private ContaService contaService;

	@Autowired
	public ContaController(ContaService contaService) {
		this.contaService = contaService;
	}

//	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
//	public ResponseEntity<ContaDTO> openAccount(@RequestBody ContaDTO conta) {
//
//		ModelMapper modelMapper = new ModelMapper();
//		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//			
//		ClienteDTO clienteDTO = modelMapper.map(conta.getCliente(), ClienteDTO.class);
//		ContaDTO contaDTO = modelMapper.map(conta, ContaDTO.class); //ISSO N PRECISA
//		clienteDTO = clienteService.saveClient(clienteDTO);
//		Cliente clienteEntity = modelMapper.map(clienteDTO, Cliente.class);
//		contaDTO.setCliente(clienteEntity);
//		contaDTO = contaService.saveAccount(contaDTO);
//
//		return ResponseEntity.status(HttpStatus.CREATED).body(contaDTO);
//	}

	/**
	 * @param cliente
	 * @param contaDTO
	 * @return
	 */
	@RequestMapping(value = "/v1/open-account-User", method = RequestMethod.POST)
	public ResponseEntity<ContaDTO> openAccountUser(@RequestBody ClienteDTO cliente, ContaDTO contaDTO) {

		contaService.saveAccountUser(cliente, contaDTO);

		return ResponseEntity.status(HttpStatus.CREATED).body(contaDTO);
	}

	/**
	 * @param number
	 * @param value
	 * @return
	 */
	@RequestMapping(path = "/v1/deposit", method = RequestMethod.PUT)
	public ResponseEntity<?> deposit(@RequestParam String number, @RequestParam BigDecimal value) {
		contaService.deposit(number, value);
		return new ResponseEntity<>("Deposito realizado com sucesso", HttpStatus.OK);
	}

}
