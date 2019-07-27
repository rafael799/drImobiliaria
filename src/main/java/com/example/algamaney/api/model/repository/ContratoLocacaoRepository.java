package com.example.algamaney.api.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.algamaney.api.model.ContratoLocacao;
import com.example.algamaney.api.model.Imovel;
import com.example.algamaney.api.model.repository.contratoLocacao.ContratoLocacaoRepositoryQuery;

public interface ContratoLocacaoRepository extends JpaRepository<ContratoLocacao, Long>, ContratoLocacaoRepositoryQuery {
	public List<ContratoLocacao> findByImovelAndSituacao(Imovel imovel,Boolean situacao);
}
