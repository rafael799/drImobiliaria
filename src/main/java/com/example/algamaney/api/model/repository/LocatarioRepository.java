package com.example.algamaney.api.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.algamaney.api.model.Locatario;
import com.example.algamaney.api.model.repository.locatario.LocatarioRepositoryQuery;

public interface LocatarioRepository extends JpaRepository<Locatario, Long>, LocatarioRepositoryQuery {

}
