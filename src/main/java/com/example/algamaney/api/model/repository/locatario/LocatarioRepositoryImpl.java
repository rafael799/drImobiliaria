package com.example.algamaney.api.model.repository.locatario;

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

import com.example.algamaney.api.model.Locatario;
import com.example.algamaney.api.model.repository.filter.LocatarioFilter;

public class LocatarioRepositoryImpl implements LocatarioRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Locatario> filtrar(LocatarioFilter locatarioFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Locatario> criteria = builder.createQuery(Locatario.class);
		Root<Locatario> root = criteria.from(Locatario.class);
		
		Predicate[] predicates = criarRestricoes(locatarioFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Locatario> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(locatarioFilter));
	}
	
	private Predicate[] criarRestricoes(LocatarioFilter locatarioFilter, CriteriaBuilder builder,
			Root<Locatario> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(locatarioFilter.getNome())) {
			predicates.add(builder.like(
					builder.lower(root.get("nome")), "%" + locatarioFilter.getNome().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(locatarioFilter.getCpf())) {
			predicates.add(builder.equal(root.get("cpf"), locatarioFilter.getCpf()));
		}
		
		if (!StringUtils.isEmpty(locatarioFilter.getRg())) {
			predicates.add(builder.equal(root.get("rg"), locatarioFilter.getRg()));
		}
		
		if (!StringUtils.isEmpty(locatarioFilter.getEmail())) {
			predicates.add(builder.like(
					builder.lower(root.get("email")), "%" + locatarioFilter.getEmail().toLowerCase() + "%"));
		}
		
		if(locatarioFilter.getSituacao() != null) {
			predicates.add(builder.equal(root.get("situacao"), locatarioFilter.getSituacao()));
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
	
	private Long total(LocatarioFilter locatarioFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Locatario> root = criteria.from(Locatario.class);
		
		Predicate[] predicates = criarRestricoes(locatarioFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
	
}
