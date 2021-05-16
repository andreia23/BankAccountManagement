package com.donus.challenge.api.account.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.donus.challenge.api.account.management.model.entity.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {

}
