package com.example.algamaney.api.model.repository.locador;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.algamaney.api.model.Locador;
import com.example.algamaney.api.model.repository.filter.LocadorFilter;

public interface LocadorRepositoryQuery {
	public Page<Locador> filtrar(LocadorFilter locadorFilter, Pageable pageable);
}
