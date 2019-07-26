package com.example.algamaney.api.model.repository.locatario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.algamaney.api.model.Locatario;
import com.example.algamaney.api.model.repository.filter.LocatarioFilter;

public interface LocatarioRepositoryQuery {
	public Page<Locatario> filtrar(LocatarioFilter locatarioFilter, Pageable pageable);
}
