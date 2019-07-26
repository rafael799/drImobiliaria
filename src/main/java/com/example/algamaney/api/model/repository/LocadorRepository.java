package com.example.algamaney.api.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.algamaney.api.model.Locador;
import com.example.algamaney.api.model.repository.locador.LocadorRepositoryQuery;

public interface LocadorRepository extends JpaRepository<Locador, Long>, LocadorRepositoryQuery {

}
