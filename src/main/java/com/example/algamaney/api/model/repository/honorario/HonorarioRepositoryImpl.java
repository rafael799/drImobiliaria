package com.example.algamaney.api.model.repository.honorario;

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

import com.example.algamaney.api.model.Honorario;
import com.example.algamaney.api.model.repository.filter.HonorarioFilter;

public class HonorarioRepositoryImpl  implements HonorarioRepositoryQuery {
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Honorario> filtrar(HonorarioFilter honorarioFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Honorario> criteria = builder.createQuery(Honorario.class);
		Root<Honorario> root = criteria.from(Honorario.class);
		
		Predicate[] predicates = criarRestricoes(honorarioFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Honorario> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(honorarioFilter));
	}
	
	private Predicate[] criarRestricoes(HonorarioFilter honorarioFilter, CriteriaBuilder builder,
			Root<Honorario> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(honorarioFilter.getTipoTransacao() != null) {
			predicates.add(builder.equal(root.get("tipoTransacao"), honorarioFilter.getTipoTransacao()));
		}
		
		if(honorarioFilter.getMes() != null) {
			predicates.add(builder.equal(root.get("mes"), honorarioFilter.getMes()));
		}
		
		if(honorarioFilter.getAno() != null) {
			predicates.add(builder.equal(root.get("ano"), honorarioFilter.getAno()));
		}
		
		if (honorarioFilter.getDataLancamento() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get("dataLancamento"), honorarioFilter.getDataLancamento()));
		}
		
		if(honorarioFilter.getValor() != null) {
			predicates.add(builder.equal(root.get("ano"), honorarioFilter.getAno()));
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
	
	private Long total(HonorarioFilter honorarioFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Honorario> root = criteria.from(Honorario.class);
		
		Predicate[] predicates = criarRestricoes(honorarioFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
}
