package com.donus.challenge.api.account.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.donus.challenge.api.account.management.model.entity.Transacao;
import com.donus.challenge.api.account.management.repository.TransacaoRepository;

/**
 * @author andreia
 *
 */
@Service
public class TransacaoService {

	private TransacaoRepository transacaoRepository;

	@Autowired
	public TransacaoService(TransacaoRepository transacaoRepository) {
		this.transacaoRepository = transacaoRepository;
	}

	@Transactional
	public List<Transacao> findALL() {
		return transacaoRepository.findAll();
	}

}
