package com.donus.challenge.api.account.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.donus.challenge.api.account.management.model.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Cliente c WHERE c.cpf = :cpf")
	boolean isCPFExists(@Param("cpf") String cpf);

	@Query("select c from Cliente c where c.cpf = ?1")
	Cliente findByCPFCustomer(String cpf);

}
