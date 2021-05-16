package com.donus.challenge.api.account.management.service;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donus.challenge.api.account.management.model.dto.ContaDTO;
import com.donus.challenge.api.account.management.model.entity.Conta;
import com.donus.challenge.api.account.management.repository.ContaRepository;

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

	public ContaDTO saveAccount(ContaDTO contaDTO) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		Date now = new Date();		
		contaDTO.setDate(now);
		Conta contaEntity = modelMapper.map(contaDTO, Conta.class);
		
		contaRepository.save(contaEntity);

		ContaDTO returnValue = modelMapper.map(contaEntity, ContaDTO.class);

		return returnValue;

	}

}
