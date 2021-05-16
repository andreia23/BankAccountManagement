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

import com.donus.challenge.api.account.management.model.dto.ClienteDTO;
import com.donus.challenge.api.account.management.model.dto.ContaDTO;
import com.donus.challenge.api.account.management.model.entity.Cliente;
import com.donus.challenge.api.account.management.service.ClienteService;
import com.donus.challenge.api.account.management.service.ContaService;

//@Api(value="API REST Produtos")
@RestController
@RequestMapping("/conta")
public class ContaController {

	private ContaService contaService;
	private ClienteService clienteService;

	@Autowired
	public ContaController(ContaService contaService, ClienteService clienteService) {
		this.contaService = contaService;
		this.clienteService = clienteService;
	}

//	@ApiOperation(value = "Salva uma conta")
//	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
//	public ResponseEntity<Conta> createProduto(@RequestBody Conta conta) {
//		return ResponseEntity.ok().body(this.contaService.saveConta(conta));
//
//	}
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ContaDTO> openAccount(@RequestBody ContaDTO conta) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		ClienteDTO clienteDTO = modelMapper.map(conta.getCliente(), ClienteDTO.class);
		ContaDTO contaDTO = modelMapper.map(conta, ContaDTO.class);
		clienteDTO = clienteService.saveClient(clienteDTO);
		Cliente clienteEntity = modelMapper.map(clienteDTO, Cliente.class);
		contaDTO.setCliente(clienteEntity);
		contaDTO = contaService.saveAccount(contaDTO);

		return ResponseEntity.status(HttpStatus.CREATED).body(contaDTO);
	}
}
