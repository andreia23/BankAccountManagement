package com.donus.challenge.api.account.management.model.request;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author andreia
 *
 */
public class TransacaoRequest {

	private String sourceNumber;

	private String destinationNumber;

	private String descricao;

	private BigDecimal valor;

	/**
	 * @return the sourceNumber
	 */
	public String getSourceNumber() {
		return sourceNumber;
	}

	/**
	 * @param sourceNumber the sourceNumber to set
	 */
	public void setSourceNumber(String sourceNumber) {
		this.sourceNumber = sourceNumber;
	}

	/**
	 * @return the destinationNumber
	 */
	public String getDestinationNumber() {
		return destinationNumber;
	}

	/**
	 * @param destinationNumber the destinationNumber to set
	 */
	public void setDestinationNumber(String destinationNumber) {
		this.destinationNumber = destinationNumber;
	}

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
