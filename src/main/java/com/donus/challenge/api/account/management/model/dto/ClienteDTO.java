package com.donus.challenge.api.account.management.model.dto;

import java.io.Serializable;

import com.donus.challenge.api.account.management.model.entity.Cliente;

/**
 * @author andreia
 *
 */
public class ClienteDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer idCliente;
	private String nomeCompleto;
	private String cpf;

	public ClienteDTO() {

	}

	public ClienteDTO(Cliente cliente) {
		idCliente = cliente.getIdCliente();
		nomeCompleto = cliente.getNomeCompleto();
		cpf = cliente.getCpf();
	}

	/**
	 * @return the idCliente
	 */
	public Integer getIdCliente() {
		return idCliente;
	}

	/**
	 * @param idCliente the idCliente to set
	 */
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	/**
	 * @return the nomeCompleto
	 */
	public String getNomeCompleto() {
		return nomeCompleto;
	}

	/**
	 * @param nomeCompleto the nomeCompleto to set
	 */
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	/**
	 * @return the cpf
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * @param cpf the cpf to set
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
