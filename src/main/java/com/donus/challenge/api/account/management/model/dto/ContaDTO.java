package com.donus.challenge.api.account.management.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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

	private Cliente cliente;
	private String numero;
	private Date date;
	private BigDecimal saldo;
	private boolean ativa;

	public ContaDTO() {

	}

	public ContaDTO(Conta conta) {
		cliente = conta.getCliente();
		date = conta.getDate();
		saldo = conta.getSaldo();

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
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
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
