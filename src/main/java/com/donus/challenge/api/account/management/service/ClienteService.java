package com.donus.challenge.api.account.management.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donus.challenge.api.account.management.model.dto.ClienteDTO;
import com.donus.challenge.api.account.management.model.entity.Cliente;
import com.donus.challenge.api.account.management.repository.ClienteRepository;

@Service
public class ClienteService {

	private ClienteRepository clienteRepository;

	@Autowired
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	public ClienteDTO saveClient(ClienteDTO clienteDTO) {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		Cliente clienteEntity = modelMapper.map(clienteDTO, Cliente.class);

		clienteRepository.save(clienteEntity);

		ClienteDTO returnValue = modelMapper.map(clienteEntity, ClienteDTO.class);

		return returnValue;

	}
}
