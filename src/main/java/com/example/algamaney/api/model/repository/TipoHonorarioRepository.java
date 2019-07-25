package com.example.algamaney.api.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.algamaney.api.model.TipoHonorario;

public interface TipoHonorarioRepository extends JpaRepository<TipoHonorario, Long> {
	public Page<TipoHonorario> findByDescricaoContaining(String descricao, Pageable pageable);
}
