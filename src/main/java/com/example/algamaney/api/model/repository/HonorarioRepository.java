package com.example.algamaney.api.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.algamaney.api.model.Honorario;
import com.example.algamaney.api.model.repository.honorario.HonorarioRepositoryQuery;

public interface HonorarioRepository extends JpaRepository<Honorario, Long>, HonorarioRepositoryQuery {

}
