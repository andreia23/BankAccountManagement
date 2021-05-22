package com.donus.challenge.api.account.management.service;

import java.math.BigDecimal;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.donus.challenge.api.account.management.exception.AccountNotFoundException;
import com.donus.challenge.api.account.management.exception.InvalidDataException;
import com.donus.challenge.api.account.management.exception.NotEnoughMoneyException;
import com.donus.challenge.api.account.management.model.dto.ClienteDTO;
import com.donus.challenge.api.account.management.model.dto.ContaDTO;
import com.donus.challenge.api.account.management.model.dto.TransacaoDTO;
import com.donus.challenge.api.account.management.model.entity.Cliente;
import com.donus.challenge.api.account.management.model.entity.Conta;
import com.donus.challenge.api.account.management.model.entity.Transacao;
import com.donus.challenge.api.account.management.repository.ClienteRepository;
import com.donus.challenge.api.account.management.repository.ContaRepository;
import com.donus.challenge.api.account.management.repository.TransacaoRepository;
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

	private TransacaoRepository transacaoRepository;

	@Autowired
	public ContaService(ContaRepository contaRepository, ClienteRepository clienteRepository, TransacaoRepository transacaoRepository ) {
		this.contaRepository = contaRepository;
		this.clienteRepository = clienteRepository;
		this.transacaoRepository = transacaoRepository;
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
	@Transactional
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

	/**
	 * @param transacaoDTO
	 */
	public Transacao transfer(String sourceNumber, String destinationNumber, TransacaoDTO transacaoDTO) {

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
//		sourceAccount.adicionar(transacao);
		
		transacao.setConta(sourceAccount);
//		contaRepository.save(sourceAccount);
		
		addTransacao(sourceAccount, transacao);
		
		Conta destinationAccount = contaRepository.findByNumberAccount(destinationNumber);
		BigDecimal destinationAccountSum = destinationAccount.getSaldo().add(transacao.getValor());
		destinationAccount.setSaldo(destinationAccountSum);
//		contaRepository.save(destinationAccount);
		addTransacao(destinationAccount, transacao);


//		transacaoRepository.save(transacao);

		return transacao;

	}

	/**
	 * @param conta
	 * @param transacao
	 */
	@Transactional
	public void addTransacao(Conta conta, Transacao transacao) {
		conta.adicionar(transacao);
		contaRepository.save(conta);

	}

}
