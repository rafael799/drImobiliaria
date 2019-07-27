package com.example.algamaney.api.model.repository.contratoLocacao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.example.algamaney.api.model.ContratoLocacao;
import com.example.algamaney.api.model.repository.filter.ContratoLocacaoFilter;

public class ContratoLocacaoRepositoryImpl implements ContratoLocacaoRepositoryQuery  {
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<ContratoLocacao> filtrar(ContratoLocacaoFilter contratoLocacaoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ContratoLocacao> criteria = builder.createQuery(ContratoLocacao.class);
		Root<ContratoLocacao> root = criteria.from(ContratoLocacao.class);
		
		Predicate[] predicates = criarRestricoes(contratoLocacaoFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<ContratoLocacao> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(contratoLocacaoFilter));
	}
	
	private Predicate[] criarRestricoes(ContratoLocacaoFilter contratoLocacaoFilter, CriteriaBuilder builder,
			Root<ContratoLocacao> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (contratoLocacaoFilter.getDataVencimento() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get("dataVencimento"), contratoLocacaoFilter.getDataVencimento()));
		}
		
		if (contratoLocacaoFilter.getValorAluguel() != null) {
			predicates.add(
					builder.equal(root.get("valorAluguel"), contratoLocacaoFilter.getValorAluguel()));
		}
		
		if (contratoLocacaoFilter.getDuracaoMeses() != null) {
			predicates.add(
					builder.equal(root.get("duracaoMeses"), contratoLocacaoFilter.getDuracaoMeses()));
		}
		
		if (contratoLocacaoFilter.getConsiderarPagamentoHonorario() != null) {
			predicates.add(
					builder.equal(root.get("considerarPagamentoHonorario"), contratoLocacaoFilter.getConsiderarPagamentoHonorario()));
		}
		
		if(contratoLocacaoFilter.getSituacao() != null) {
			predicates.add(builder.equal(root.get("situacao"), contratoLocacaoFilter.getSituacao()));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}
	
	private Long total(ContratoLocacaoFilter contratoLocacaoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<ContratoLocacao> root = criteria.from(ContratoLocacao.class);
		
		Predicate[] predicates = criarRestricoes(contratoLocacaoFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
}
