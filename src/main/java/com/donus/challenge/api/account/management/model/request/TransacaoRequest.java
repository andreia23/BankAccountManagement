package com.donus.challenge.api.account.management.model.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

/**
 * @author andreia
 *
 */
public class TransacaoRequest {

	private String descricao;

	@NotNull(message = "Valor não pode ser nulo")
	private BigDecimal valor;

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the valor
	 */
	public BigDecimal getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
