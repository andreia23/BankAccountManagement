package com.donus.challenge.api.account.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.donus.challenge.api.account.management.model.entity.Conta;

public interface ContaRepository extends JpaRepository<Conta, Integer> {

}
