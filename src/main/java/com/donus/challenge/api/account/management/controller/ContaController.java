package com.donus.challenge.api.account.management.controller;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
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
import com.donus.challenge.api.account.management.model.dto.TransacaoDTO;
import com.donus.challenge.api.account.management.model.entity.Cliente;
import com.donus.challenge.api.account.management.model.entity.Transacao;
import com.donus.challenge.api.account.management.model.request.TransacaoRequest;
import com.donus.challenge.api.account.management.service.ClienteService;
import com.donus.challenge.api.account.management.service.ContaService;
import com.donus.challenge.api.account.management.service.TransacaoService;

//@Api(value="API REST Produtos")
@RestController
public class ContaController {

	private ContaService contaService;

	private ClienteService clienteService;

	private TransacaoService transacaoService;

	@Autowired
	public ContaController(ContaService contaService, ClienteService clienteService,
			TransacaoService transacaoService) {
		this.contaService = contaService;
		this.clienteService = clienteService;
		this.transacaoService = transacaoService;
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
	 * @param clienteDTO
	 * @param contaDTO
	 * @return
	 */
	@RequestMapping(value = "/v1/open-account-user", method = RequestMethod.POST)
	public ResponseEntity<ContaDTO> openAccountUser(@Valid @RequestBody ClienteDTO clienteDTO, ContaDTO contaDTO) {

		Cliente cliente = clienteService.saveClient(clienteDTO);
		contaService.saveAccountUser(cliente, contaDTO);

		return ResponseEntity.status(HttpStatus.CREATED).body(contaDTO);
	}

	/**
	 * @param number
	 * @param transacaoRequest
	 * @return
	 */
	@RequestMapping(path = "/v1/deposit", method = RequestMethod.PUT)
	public ResponseEntity<?> deposit(@Valid @RequestParam String number,
			@RequestBody TransacaoRequest transacaoRequest) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		TransacaoDTO transacaoDTO = modelMapper.map(transacaoRequest, TransacaoDTO.class);

		contaService.deposit(number, transacaoDTO);

		return new ResponseEntity<>("Deposito realizado com sucesso", HttpStatus.OK);
	}

	/**
	 * @param sourceNumber
	 * @param destinationNumber
	 * @param transacaoRequest
	 * @return
	 */
	@RequestMapping(value = "/v1/transfer", method = RequestMethod.POST)
	public ResponseEntity<TransacaoDTO> transfer(@Valid @RequestParam String sourceNumber,
			@RequestParam String destinationNumber, @RequestBody TransacaoRequest transacaoRequest) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		TransacaoDTO transacaoDTO = modelMapper.map(transacaoRequest, TransacaoDTO.class);
		contaService.transfer(sourceNumber, destinationNumber, transacaoDTO);

		return ResponseEntity.status(HttpStatus.CREATED).body(transacaoDTO);

	}

	/**
	 * @param number
	 * @return
	 */
	@RequestMapping(value = "/v1/get-account", method = RequestMethod.GET)
	public ResponseEntity<ContaDTO> accountNumber(@Valid @RequestParam String number) {

		ContaDTO contaDTO = contaService.getAccount(number);

		return ResponseEntity.status(HttpStatus.CREATED).body(contaDTO);

	}

	/**
	 * @param number
	 * @return
	 */
	@RequestMapping(value = "/v1/get-balance", method = RequestMethod.GET)
	public ResponseEntity<?> getAccountBalance(@Valid @RequestParam String number) {

		BigDecimal saldo = contaService.getBalance(number);
		return new ResponseEntity<>("Saldo: " + saldo, HttpStatus.OK);

	}

	@RequestMapping(value = "/v1/deactivate-account", method = RequestMethod.GET)
	public ResponseEntity<?> deactivateAccount(@Valid @RequestParam String number) {

		contaService.deactivate(number);
		return new ResponseEntity<>("Conta desativada com sucesso", HttpStatus.OK);

	}

	@RequestMapping(value = "/v1/delete-account", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAccount(@Valid @RequestParam String number) {

		contaService.delete(number);
		return new ResponseEntity<>("Conta deletada com sucesso", HttpStatus.OK);

	}

}
