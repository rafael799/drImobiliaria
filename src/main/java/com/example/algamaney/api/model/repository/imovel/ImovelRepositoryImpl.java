package com.example.algamaney.api.model.repository.imovel;

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

import com.example.algamaney.api.model.Imovel;
import com.example.algamaney.api.model.repository.filter.ImovelFilter;

public class ImovelRepositoryImpl implements ImovelRepositoryQuery {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Imovel> filtrar(ImovelFilter imovelFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Imovel> criteria = builder.createQuery(Imovel.class);
		Root<Imovel> root = criteria.from(Imovel.class);
		
		Predicate[] predicates = criarRestricoes(imovelFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Imovel> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(imovelFilter));
	}
	
	private Predicate[] criarRestricoes(ImovelFilter imovelFilter, CriteriaBuilder builder,
			Root<Imovel> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(imovelFilter.getDescricao())) {
			predicates.add(builder.like(
					builder.lower(root.get("descricao")), "%" + imovelFilter.getDescricao().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(imovelFilter.getLogradouro())) {
			predicates.add(builder.like(
					builder.lower(root.get("logradouro")), "%" + imovelFilter.getLogradouro().toLowerCase() + "%"));
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
	
	private Long total(ImovelFilter imovelFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Imovel> root = criteria.from(Imovel.class);
		
		Predicate[] predicates = criarRestricoes(imovelFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
