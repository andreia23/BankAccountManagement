package com.donus.challenge.api.account.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.donus.challenge.api.account.management.model.entity.Transacao;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {

}
