package com.donus.challenge.api.account.management.service;

import java.math.BigDecimal;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.donus.challenge.api.account.management.exception.AccountNotFoundException;
import com.donus.challenge.api.account.management.exception.NotEnoughMoneyException;
import com.donus.challenge.api.account.management.model.dto.TransacaoDTO;
import com.donus.challenge.api.account.management.model.entity.Conta;
import com.donus.challenge.api.account.management.model.entity.Transacao;
import com.donus.challenge.api.account.management.repository.ContaRepository;
import com.donus.challenge.api.account.management.repository.TransacaoRepository;

/**
 * @author andreia
 *
 */
@Service
public class TransacaoService {

	private TransacaoRepository transacaoRepository;

	private ContaRepository contaRepository;

	@Autowired
	public TransacaoService(TransacaoRepository transacaoRepository, ContaRepository contaRepository) {
		this.transacaoRepository = transacaoRepository;
		this.contaRepository = contaRepository;
	}

	@Transactional
	public void createTransaction(TransacaoDTO transacaoDTO) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		Date now = new Date();
		transacaoDTO.setDate(now);

		Transacao transacao = modelMapper.map(transacaoDTO, Transacao.class);

		if (!contaRepository.isAccountExists(transacao.getSourceNumber())) {
			throw new AccountNotFoundException("Conta de origem não encontrada");
		}
		
		if (!contaRepository.isAccountExists(transacao.getDestinationNumber())) {
			throw new AccountNotFoundException("Conta de destino não encontrada");
		}
		
		Conta sourceAccount = contaRepository.findByNumberAccount(transacao.getSourceNumber());
		
		if (sourceAccount.getSaldo().compareTo(transacao.getValor()) < 0) {
			throw new NotEnoughMoneyException("Saldo insuficiente para transação");
		}
		
		BigDecimal sourceAccountSum = sourceAccount.getSaldo().subtract(transacao.getValor());
		sourceAccount.setSaldo(sourceAccountSum);
//		this.addTransacao(sourceAccount, transacao);
//		transacao.setConta(sourceAccount);
		contaRepository.save(sourceAccount);
		
		Conta destinationAccount = contaRepository.findByNumberAccount(transacao.getDestinationNumber());
		BigDecimal destinationAccountSum = destinationAccount.getSaldo().add(transacao.getValor());
		destinationAccount.setSaldo(destinationAccountSum);
		contaRepository.save(destinationAccount);
		
		transacaoRepository.save(transacao);
		
	}
	
	@Transactional
	public void addTransacao(Conta conta, Transacao transacao) {
		conta.adicionar(transacao);
		
	}

}
