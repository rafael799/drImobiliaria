package com.example.algamaney.api.model.repository.contratoLocacao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.algamaney.api.model.ContratoLocacao;
import com.example.algamaney.api.model.repository.filter.ContratoLocacaoFilter;

public interface ContratoLocacaoRepositoryQuery {
	public Page<ContratoLocacao> filtrar(ContratoLocacaoFilter contratoLocacaoFilter, Pageable pageable);
}
