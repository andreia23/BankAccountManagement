package com.donus.challenge.api.account.management.service;

import java.math.BigDecimal;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.donus.challenge.api.account.management.exception.AccountNotFoundException;
import com.donus.challenge.api.account.management.exception.InvalidDataException;
import com.donus.challenge.api.account.management.model.dto.ClienteDTO;
import com.donus.challenge.api.account.management.model.dto.ContaDTO;
import com.donus.challenge.api.account.management.model.entity.Cliente;
import com.donus.challenge.api.account.management.model.entity.Conta;
import com.donus.challenge.api.account.management.repository.ClienteRepository;
import com.donus.challenge.api.account.management.repository.ContaRepository;
import com.donus.challenge.api.account.management.util.DataValidator;
import com.donus.challenge.api.account.management.util.NumberGeberatorUtil;

/**
 * @author andreia
 *
 */
@Service
public class ContaService {

	private ContaRepository contaRepository;

	private ClienteRepository clienteRepository;

	@Autowired
	public ContaService(ContaRepository contaRepository, ClienteRepository clienteRepository) {
		this.contaRepository = contaRepository;
		this.clienteRepository = clienteRepository;
	}

	/**
	 * @param conta
	 * @return
	 */
	@Transactional
	public Conta saveAccount(Conta conta) {
		return contaRepository.save(conta);
	}

	/**
	 * @param contaDTO
	 * @return
	 */
	@Transactional
	public void saveAccountUser(Cliente cliente, ContaDTO contaDTO) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		contaDTO.setCliente(cliente);
		contaDTO.setAtiva(true);
		contaDTO.setSaldo(BigDecimal.ZERO);

		Date now = new Date();
		contaDTO.setDate(now);
		contaDTO.setNumero(NumberGeberatorUtil.accountNumberGenerator());
		Conta contaEntity = modelMapper.map(contaDTO, Conta.class);

		contaRepository.save(contaEntity);

	}

	/**
	 * @param number
	 * @param value
	 */
	public void deposit(String number, BigDecimal value) {

		if (!contaRepository.isAccountExists(number)) {
			throw new AccountNotFoundException("Conta inexistente");
		}

		Conta conta = contaRepository.findByNumberAccount(number);

		if (!conta.isAtiva()) {
			throw new InvalidDataException("Conta desativada");
		}
		if (!DataValidator.validateValue(value)) {
			throw new InvalidDataException("Valor inválido");
		}

		contaRepository.updateBalance(number, value);

	}

}
