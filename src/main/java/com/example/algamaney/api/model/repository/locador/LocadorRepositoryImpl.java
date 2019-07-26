package com.example.algamaney.api.model.repository.locador;

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
import org.springframework.util.StringUtils;

import com.example.algamaney.api.model.Locador;
import com.example.algamaney.api.model.repository.filter.LocadorFilter;

public class LocadorRepositoryImpl implements LocadorRepositoryQuery {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Locador> filtrar(LocadorFilter locadorFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Locador> criteria = builder.createQuery(Locador.class);
		Root<Locador> root = criteria.from(Locador.class);
		
		Predicate[] predicates = criarRestricoes(locadorFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Locador> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(locadorFilter));
	}
	
	private Predicate[] criarRestricoes(LocadorFilter locadorFilter, CriteriaBuilder builder,
			Root<Locador> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(locadorFilter.getNome())) {
			predicates.add(builder.like(
					builder.lower(root.get("nome")), "%" + locadorFilter.getNome().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(locadorFilter.getCpf())) {
			predicates.add(builder.equal(root.get("cpf"), locadorFilter.getCpf()));
		}
		
		if (!StringUtils.isEmpty(locadorFilter.getRg())) {
			predicates.add(builder.equal(root.get("rg"), locadorFilter.getRg()));
		}
		
		if (!StringUtils.isEmpty(locadorFilter.getEmail())) {
			predicates.add(builder.like(
					builder.lower(root.get("email")), "%" + locadorFilter.getEmail().toLowerCase() + "%"));
		}
		
		if(locadorFilter.getSituacao() != null) {
			predicates.add(builder.equal(root.get("situacao"), locadorFilter.getSituacao()));
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
	
	private Long total(LocadorFilter locadorFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Locador> root = criteria.from(Locador.class);
		
		Predicate[] predicates = criarRestricoes(locadorFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}


}
