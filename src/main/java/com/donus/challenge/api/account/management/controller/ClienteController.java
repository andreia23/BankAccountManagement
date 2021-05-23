package com.donus.challenge.api.account.management.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.donus.challenge.api.account.management.model.dto.ClienteDTO;
import com.donus.challenge.api.account.management.model.entity.Cliente;
import com.donus.challenge.api.account.management.service.ClienteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author andreia
 *
 */
@Api(value = "Clientes")
@RestController
public class ClienteController {

	private ClienteService clienteService;

	@Autowired
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	/**
	 * @return
	 */
	@ApiOperation(value = "Obtém todos os clientes")
	@RequestMapping(value = "/v1/all-customers", method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findALL() {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		List<ClienteDTO> listDto = new ArrayList<ClienteDTO>();

		List<Cliente> list = clienteService.findALL();
		for (Cliente cliente : list) {

			ClienteDTO clienteDTO = modelMapper.map(cliente, ClienteDTO.class);
			listDto.add(clienteDTO);

		}

		return ResponseEntity.ok().body(listDto);

	}

	/**
	 * @param cpf
	 * @return
	 */
	@ApiOperation(value = "Obtém um cliente por cpf")
	@RequestMapping(value = "/v1/customer-by-cpf", method = RequestMethod.GET)
	public ResponseEntity<ClienteDTO> findById(@Valid @RequestParam String cpf) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Cliente cliente = clienteService.findByCPF(cpf);

		ClienteDTO clienteDTO = modelMapper.map(cliente, ClienteDTO.class);

		return ResponseEntity.ok().body(clienteDTO);
	}

}
