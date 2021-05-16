package com.donus.challenge.api.account.management.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.donus.challenge.api.account.management.model.entity.Cliente;
import com.donus.challenge.api.account.management.model.entity.Conta;
import com.donus.challenge.api.account.management.model.entity.Transacao;

/**
 * @author andreia
 *
 */
public class ContaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer idConta;
	private Cliente cliente;
	private Date date;
	private BigDecimal saldo;
	private Set<Transacao> transacoes = new HashSet<Transacao>();
	private boolean ativa;

	public ContaDTO() {

	}

	public ContaDTO(Conta conta) {
		idConta = conta.getIdConta();
		cliente = conta.getCliente();
		date = conta.getDate();
		saldo = conta.getSaldo();
		transacoes = conta.getTransacoes();

	}

	/**
	 * @return the idConta
	 */
	public Integer getIdConta() {
		return idConta;
	}

	/**
	 * @param idConta the idConta to set
	 */
	public void setIdConta(Integer idConta) {
		this.idConta = idConta;
	}

	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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

	/**
	 * @return the saldo
	 */
	public BigDecimal getSaldo() {
		return saldo;
	}

	/**
	 * @param saldo the saldo to set
	 */
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	/**
	 * @return the transacoes
	 */
	public Set<Transacao> getTransacoes() {
		return transacoes;
	}

	/**
	 * @param transacoes the transacoes to set
	 */
	public void setTransacoes(Set<Transacao> transacoes) {
		this.transacoes = transacoes;
	}

	/**
	 * @return the ativa
	 */
	public boolean isAtiva() {
		return ativa;
	}

	/**
	 * @param ativa the ativa to set
	 */
	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}

}
