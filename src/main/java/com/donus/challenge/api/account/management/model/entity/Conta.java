package com.donus.challenge.api.account.management.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author andreia
 *
 */
@Entity
public class Conta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idConta;
//	private Cliente cliente;
//	private Date date;
	private BigDecimal saldo;
//	private Set<Transacao> transacoes = new HashSet<Transacao>();
	private boolean ativa;

//	public BigDecimal getSaldo() {
//		BigDecimal total = BigDecimal.ZERO;
//		for (Transacao t : this.transacoes) {
//			total = total.add(t.getValor());
//		}
//		return total;
//	}

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
//	public Cliente getCliente() {
//		return cliente;
//	}
//
//	/**
//	 * @param cliente the cliente to set
//	 */
//	public void setCliente(Cliente cliente) {
//		this.cliente = cliente;
//	}

//	/**
//	 * @return the date
//	 */
//	public Date getDate() {
//		return date;
//	}
//
//	/**
//	 * @param date the date to set
//	 */
//	public void setDate(Date date) {
//		this.date = date;
//	}

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
//	public Set<Transacao> getTransacoes() {
//		return transacoes;
//	}
//
//	/**
//	 * @param transacoes the transacoes to set
//	 */
//	public void setTransacoes(Set<Transacao> transacoes) {
//		this.transacoes = transacoes;
//	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (ativa ? 1231 : 1237);
//		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
//		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((idConta == null) ? 0 : idConta.hashCode());
		result = prime * result + ((saldo == null) ? 0 : saldo.hashCode());
//		result = prime * result + ((transacoes == null) ? 0 : transacoes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		if (ativa != other.ativa)
			return false;
//		if (cliente == null) {
//			if (other.cliente != null)
//				return false;
//		} else if (!cliente.equals(other.cliente))
//			return false;
//		if (date == null) {
//			if (other.date != null)
//				return false;
//		} else if (!date.equals(other.date))
//			return false;
		if (idConta == null) {
			if (other.idConta != null)
				return false;
		} else if (!idConta.equals(other.idConta))
			return false;
		if (saldo == null) {
			if (other.saldo != null)
				return false;
		} else if (!saldo.equals(other.saldo))
			return false;
//		if (transacoes == null) {
//			if (other.transacoes != null)
//				return false;
//		} else if (!transacoes.equals(other.transacoes))
//			return false;
		return true;
	}
	 
	

}
