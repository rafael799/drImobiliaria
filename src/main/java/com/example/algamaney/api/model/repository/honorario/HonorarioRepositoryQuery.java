package com.example.algamaney.api.model.repository.honorario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.algamaney.api.model.Honorario;
import com.example.algamaney.api.model.repository.filter.HonorarioFilter;

public interface HonorarioRepositoryQuery {
	public Page<Honorario> filtrar(HonorarioFilter honorarioFilter, Pageable pageable);
}
