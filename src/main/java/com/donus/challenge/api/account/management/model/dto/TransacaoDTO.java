package com.donus.challenge.api.account.management.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.donus.challenge.api.account.management.model.entity.Transacao;

/**
 * @author andreia
 *
 */

public class TransacaoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	private String sourceNumber;
//
//	private String destinationNumber;

	private String descricao;

	private BigDecimal valor;

	private Date date;

//	public TransacaoDTO(Transacao transacao) {
//		sourceNumber = transacao.getSourceNumber();
//		destinationNumber = transacao.getDestinationNumber();
//		descricao = transacao.getDescricao();
//		valor = transacao.getValor();
//		date = transacao.getDate();
//	}



//	/**
//	 * @return the sourceNumber
//	 */
//	public String getSourceNumber() {
//		return sourceNumber;
//	}
//
//	/**
//	 * @param sourceNumber the sourceNumber to set
//	 */
//	public void setSourceNumber(String sourceNumber) {
//		this.sourceNumber = sourceNumber;
//	}
//
//	/**
//	 * @return the destinationNumber
//	 */
//	public String getDestinationNumber() {
//		return destinationNumber;
//	}
//
//	/**
//	 * @param destinationNumber the destinationNumber to set
//	 */
//	public void setDestinationNumber(String destinationNumber) {
//		this.destinationNumber = destinationNumber;
//	}

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

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

}
