package com.donus.challenge.api.account.management.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donus.challenge.api.account.management.model.dto.ContaDTO;
import com.donus.challenge.api.account.management.model.entity.Conta;
import com.donus.challenge.api.account.management.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	private ContaRepository contaRepository;

	public Conta saveConta(Conta conta) {
		return contaRepository.save(conta);
	}
	
	public ContaDTO insert(ContaDTO user) {
		
//		user.setId(UUID.randomUUID().toString());
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		Conta userEntity = modelMapper.map(user, Conta.class);
		
		contaRepository.save(userEntity);
		
		ContaDTO returnValue = modelMapper.map(userEntity, ContaDTO.class);
		
		return returnValue;
		
	}

}
