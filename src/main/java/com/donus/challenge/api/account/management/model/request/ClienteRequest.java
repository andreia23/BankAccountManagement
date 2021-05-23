package com.donus.challenge.api.account.management.model.request;

import javax.validation.constraints.NotNull;

/**
 * @author andreia
 *
 */
public class ClienteRequest {

	@NotNull(message = "Nome não pode ser nulo")
	private String nomeCompleto;

	@NotNull(message = "CPF não pode ser nulo")
	private String cpf;

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
