package com.donus.challenge.api.account.management.service;

import java.math.BigDecimal;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.donus.challenge.api.account.management.exception.AccountNotFoundException;
import com.donus.challenge.api.account.management.exception.InvalidDataException;
import com.donus.challenge.api.account.management.model.dto.ContaDTO;
import com.donus.challenge.api.account.management.model.entity.Conta;
import com.donus.challenge.api.account.management.repository.ContaRepository;
import com.donus.challenge.api.account.management.util.DataValidator;
import com.donus.challenge.api.account.management.util.NumberGeberatorUtil;

@Service
public class ContaService {

	private ContaRepository contaRepository;

	@Autowired
	public ContaService(ContaRepository contaRepository) {
		this.contaRepository = contaRepository;
	}

	public Conta saveConta(Conta conta) {
		return contaRepository.save(conta);
	}

	/**
	 * @param contaDTO
	 * @return
	 */
	public ContaDTO saveAccount(ContaDTO contaDTO) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		Date now = new Date();
		contaDTO.setDate(now);
		contaDTO.setNumero(NumberGeberatorUtil.accountNumberGenerator());
		Conta contaEntity = modelMapper.map(contaDTO, Conta.class);

		contaRepository.save(contaEntity);

		ContaDTO returnValue = modelMapper.map(contaEntity, ContaDTO.class);

		return returnValue;

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
