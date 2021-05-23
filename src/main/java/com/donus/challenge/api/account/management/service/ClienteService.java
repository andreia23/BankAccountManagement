package com.donus.challenge.api.account.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.donus.challenge.api.account.management.exception.CustomerNotFoundException;
import com.donus.challenge.api.account.management.model.entity.Cliente;
import com.donus.challenge.api.account.management.repository.ClienteRepository;

/**
 * @author andreia
 *
 */
@Service
public class ClienteService {

	private ClienteRepository clienteRepository;

	@Autowired
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	/**
	 * @return
	 */
	@Transactional
	public List<Cliente> findALL() {
		return clienteRepository.findAll();
	}

	/**
	 * @param cpf
	 * @return
	 */
	@Transactional
	public Cliente findByCPF(String cpf) {
		Cliente cliente = clienteRepository.findByCPFCustomer(cpf);
		if (cliente == null) {
			throw new CustomerNotFoundException("Cliente inexistente");
		}
		return cliente;
	}

}
