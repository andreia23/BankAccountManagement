package com.donus.challenge.api.account.management.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.donus.challenge.api.account.management.exception.DuplicateDataException;
import com.donus.challenge.api.account.management.exception.InvalidDataException;
import com.donus.challenge.api.account.management.model.dto.ClienteDTO;
import com.donus.challenge.api.account.management.model.entity.Cliente;
import com.donus.challenge.api.account.management.repository.ClienteRepository;
import com.donus.challenge.api.account.management.util.DataValidator;

@Service
public class ClienteService {

	private ClienteRepository clienteRepository;

	@Autowired
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	@Transactional
	public Cliente saveClient(ClienteDTO clienteDTO) {

		if (!DataValidator.isCPF(clienteDTO.getCpf()))
			throw new InvalidDataException("CPF inválido");
		
		try {

			ModelMapper modelMapper = new ModelMapper();
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

			Cliente clienteEntity = modelMapper.map(clienteDTO, Cliente.class);

			clienteRepository.save(clienteEntity);

			return clienteEntity;

		} catch (DataIntegrityViolationException e) {
			throw new DuplicateDataException("CPF já utilizado");
		}

	}
}
