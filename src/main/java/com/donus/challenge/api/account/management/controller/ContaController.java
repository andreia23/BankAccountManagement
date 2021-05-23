package com.donus.challenge.api.account.management.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
import com.donus.challenge.api.account.management.model.entity.Conta;
import com.donus.challenge.api.account.management.model.request.ClienteRequest;
import com.donus.challenge.api.account.management.model.request.TransacaoRequest;
import com.donus.challenge.api.account.management.service.ContaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author andreia
 *
 */
@Api(value = "API REST Contas")
@RestController
public class ContaController {

	private ContaService contaService;

	@Autowired
	public ContaController(ContaService contaService) {
		this.contaService = contaService;

	}

	/**
	 * @param clienteDTO
	 * @param contaDTO
	 * @return
	 */
	@ApiOperation(value = "Abre uma nova conta")
	@RequestMapping(value = "/v1/open-account-user", method = RequestMethod.POST)
	public ResponseEntity<ContaDTO> openAccountUser(@Valid @RequestBody ClienteRequest clienteRequest,
			ContaDTO contaDTO) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		ClienteDTO clienteDTO = modelMapper.map(clienteRequest, ClienteDTO.class);
		contaService.saveAccountUser(clienteDTO, contaDTO);

		return ResponseEntity.status(HttpStatus.CREATED).body(contaDTO);
	}

	/**
	 * @param number
	 * @param transacaoRequest
	 * @return
	 */
	@ApiOperation(value = "Realiza depósito")
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
	@ApiOperation(value = "Faz transferências")
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
	@ApiOperation(value = "Obtém uma conta pelo o número")
	@RequestMapping(value = "/v1/get-account", method = RequestMethod.GET)
	public ResponseEntity<ContaDTO> accountNumber(@Valid @RequestParam String number) {

		ContaDTO contaDTO = contaService.getAccount(number);

		return ResponseEntity.status(HttpStatus.CREATED).body(contaDTO);

	}

	/**
	 * @param number
	 * @return
	 */
	@ApiOperation(value = "Obtém o saldo da conta")
	@RequestMapping(value = "/v1/get-balance", method = RequestMethod.GET)
	public ResponseEntity<?> getAccountBalance(@Valid @RequestParam String number) {

		BigDecimal saldo = contaService.getBalance(number);
		return new ResponseEntity<>("Saldo: " + saldo, HttpStatus.OK);

	}

	/**
	 * @param number
	 * @return
	 */
	@ApiOperation(value = "Desativa a conta")
	@RequestMapping(value = "/v1/deactivate-account", method = RequestMethod.GET)
	public ResponseEntity<?> deactivateAccount(@Valid @RequestParam String number) {

		contaService.deactivate(number);
		return new ResponseEntity<>("Conta desativada com sucesso", HttpStatus.OK);

	}

	/**
	 * @param number
	 * @return
	 */
	@ApiOperation(value = "Ativa a conta")
	@RequestMapping(value = "/v1/activate-account", method = RequestMethod.GET)
	public ResponseEntity<?> activateAccount(@Valid @RequestParam String number) {

		contaService.activate(number);
		return new ResponseEntity<>("Conta ativada com sucesso", HttpStatus.OK);

	}

	/**
	 * @param number
	 * @return
	 */
	@ApiOperation(value = "Apaga a conta e o cliente")
	@RequestMapping(value = "/v1/delete-account", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAccount(@Valid @RequestParam String number) {

		contaService.delete(number);
		return new ResponseEntity<>("Conta deletada com sucesso", HttpStatus.OK);

	}

	/**
	 * @return
	 */
	@ApiOperation(value = "Obtém todas as contas")
	@RequestMapping(value = "/v1/all-accounts", method = RequestMethod.GET)
	public ResponseEntity<List<ContaDTO>> findALL() {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		List<ContaDTO> listDto = new ArrayList<ContaDTO>();
		List<Conta> list = contaService.findALL();
		for (Conta conta : list) {

			ContaDTO contaDTO = modelMapper.map(conta, ContaDTO.class);
			listDto.add(contaDTO);

		}

		return ResponseEntity.ok().body(listDto);

	}

}
