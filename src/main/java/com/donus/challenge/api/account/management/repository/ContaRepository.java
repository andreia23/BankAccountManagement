package com.donus.challenge.api.account.management.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.donus.challenge.api.account.management.model.entity.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {
	
	@Transactional
	@Modifying
	@Query("update Conta c set c.saldo = c.saldo + ?2 where c.numero = ?1")
	void updateBalance(String number, BigDecimal value);

	@Query("select c from Conta c where c.numero = ?1")
	Conta findByNumberAccount(String number);
	

}
