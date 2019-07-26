package com.example.algamaney.api.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.algamaney.api.model.ContratoLocacao;
import com.example.algamaney.api.model.repository.contratoLocacao.ContratoLocacaoRepositoryQuery;

public interface ContratoLocacaoRepository extends JpaRepository<ContratoLocacao, Long>, ContratoLocacaoRepositoryQuery {

}
