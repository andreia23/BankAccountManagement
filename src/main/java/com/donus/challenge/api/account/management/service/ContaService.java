package com.donus.challenge.api.account.management.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.donus.challenge.api.account.management.exception.AccountNotFoundException;
import com.donus.challenge.api.account.management.exception.DuplicateDataException;
import com.donus.challenge.api.account.management.exception.InvalidDataException;
import com.donus.challenge.api.account.management.exception.NotEnoughMoneyException;
import com.donus.challenge.api.account.management.model.dto.ClienteDTO;
import com.donus.challenge.api.account.management.model.dto.ContaDTO;
import com.donus.challenge.api.account.management.model.dto.TransacaoDTO;
import com.donus.challenge.api.account.management.model.entity.Cliente;
import com.donus.challenge.api.account.management.model.entity.Conta;
import com.donus.challenge.api.account.management.model.entity.Transacao;
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

	@Autowired
	public ContaService(ContaRepository contaRepository) {
		this.contaRepository = contaRepository;
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
	 * @param cliente
	 * @param contaDTO
	 */
	@Transactional
	public void saveAccountUser(ClienteDTO clienteDTO, ContaDTO contaDTO) {

		if (!DataValidator.isCPF(clienteDTO.getCpf()))
			throw new InvalidDataException("CPF inválido. Exemplo de formato aceito: xxxxxxxxxxx");

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		try {

			Cliente clienteEntity = modelMapper.map(clienteDTO, Cliente.class);

			contaDTO.setCliente(clienteEntity);
			contaDTO.setAtiva(true);
			contaDTO.setSaldo(BigDecimal.ZERO);

			Date now = new Date();
			contaDTO.setDate(now);
			contaDTO.setNumero(NumberGeberatorUtil.accountNumberGenerator());
			Conta contaEntity = modelMapper.map(contaDTO, Conta.class);
			contaRepository.save(contaEntity);

		} catch (DataIntegrityViolationException e) {
			throw new DuplicateDataException("CPF já utilizado");
		}

	}

	/**
	 * @param number
	 * @param transacaoDTO
	 */
	@Transactional
	public void deposit(String number, TransacaoDTO transacaoDTO) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		Date now = new Date();
		transacaoDTO.setDate(now);

		Transacao transacao = modelMapper.map(transacaoDTO, Transacao.class);

		if (!contaRepository.isAccountExists(number)) {
			throw new AccountNotFoundException("Conta inexistente");
		}

		Conta conta = contaRepository.findByNumberAccount(number);

		if (!conta.isAtiva()) {
			throw new InvalidDataException("Conta desativada");
		}

		if (!DataValidator.validateValue(transacao.getValor())) {
			throw new InvalidDataException("Valor inválido. Valor limite: R$2.000");
		}

		BigDecimal destinationAccountSum = conta.getSaldo().add(transacao.getValor());
		conta.setSaldo(destinationAccountSum);

		addTransacao(conta, transacao);

	}

	/**
	 * @param transacaoDTO
	 */
	@Transactional
	public void transfer(String sourceNumber, String destinationNumber, TransacaoDTO transacaoDTO) {

		if (!DataValidator.validateValueNegative(transacaoDTO.getValor())) {
			throw new InvalidDataException("Valor inválido. Não aceitamos valores negativos.");
		}

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		Date now = new Date();
		transacaoDTO.setDate(now);

		Transacao transacao = modelMapper.map(transacaoDTO, Transacao.class);

		if (!contaRepository.isAccountExists(sourceNumber)) {
			throw new AccountNotFoundException("Conta de origem não encontrada");
		}

		if (!contaRepository.isAccountExists(destinationNumber)) {
			throw new AccountNotFoundException("Conta de destino não encontrada");
		}

		Conta sourceAccount = contaRepository.findByNumberAccount(sourceNumber);

		if (sourceAccount.getSaldo().compareTo(transacao.getValor()) < 0) {
			throw new NotEnoughMoneyException("Saldo insuficiente para transação");
		}

		BigDecimal sourceAccountSum = sourceAccount.getSaldo().subtract(transacao.getValor());
		sourceAccount.setSaldo(sourceAccountSum);

		transacao.setConta(sourceAccount);
		addTransacao(sourceAccount, transacao);

		Conta destinationAccount = contaRepository.findByNumberAccount(destinationNumber);
		BigDecimal destinationAccountSum = destinationAccount.getSaldo().add(transacao.getValor());
		destinationAccount.setSaldo(destinationAccountSum);

		contaRepository.save(destinationAccount);

	}

	/**
	 * @param conta
	 * @param transacao
	 */
	@Transactional
	public void addTransacao(Conta conta, Transacao transacao) {
		conta.addTransacao(transacao);
		contaRepository.save(conta);

	}

	/**
	 * @param number
	 * @return
	 */
	@Transactional
	public ContaDTO getAccount(String number) {

		if (!contaRepository.isAccountExists(number)) {
			throw new AccountNotFoundException("Conta inexistente");
		}

		Conta conta = contaRepository.findByNumberAccount(number);

		if (!conta.isAtiva()) {
			throw new InvalidDataException("Conta desativada");
		}

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		ContaDTO contaDTO = modelMapper.map(conta, ContaDTO.class);

		return contaDTO;
	}

	/**
	 * @param number
	 * @return
	 */
	@Transactional
	public BigDecimal getBalance(String number) {

		if (!contaRepository.isAccountExists(number)) {
			throw new AccountNotFoundException("Conta inexistente");
		}

		Conta conta = contaRepository.findByNumberAccount(number);

		if (!conta.isAtiva()) {
			throw new InvalidDataException("Conta desativada");
		}

		return conta.getSaldo();
	}

	/**
	 * @param number
	 */
	@Transactional
	public void deactivate(String number) {

		if (!contaRepository.isAccountExists(number)) {
			throw new AccountNotFoundException("Conta inexistente");
		}

		Conta conta = contaRepository.findByNumberAccount(number);

		if (!conta.isAtiva()) {
			throw new InvalidDataException("Conta já desativada");
		}

		conta.setAtiva(false);
		contaRepository.save(conta);

	}
	
	/**
	 * @param number
	 */
	@Transactional
	public void activate(String number) {

		if (!contaRepository.isAccountExists(number)) {
			throw new AccountNotFoundException("Conta inexistente");
		}

		Conta conta = contaRepository.findByNumberAccount(number);

		if (conta.isAtiva()) {
			throw new InvalidDataException("Conta já ativada");
		}

		conta.setAtiva(true);
		contaRepository.save(conta);

	}
	

	/**
	 * @param number
	 */
	@Transactional
	public void delete(String number) {

		if (!contaRepository.isAccountExists(number)) {
			throw new AccountNotFoundException("Conta inexistente");
		}

		Conta conta = contaRepository.findByNumberAccount(number);

		contaRepository.delete(conta);

	}
	
	/**
	 * @return
	 */
	@Transactional
	public List<Conta> findALL() {
		return contaRepository.findAll();
	}

}
