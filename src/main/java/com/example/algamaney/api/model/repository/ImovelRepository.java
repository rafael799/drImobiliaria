package com.example.algamaney.api.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.algamaney.api.model.Imovel;
import com.example.algamaney.api.model.repository.imovel.ImovelRepositoryQuery;

public interface ImovelRepository extends JpaRepository<Imovel, Long>, ImovelRepositoryQuery {
	
}
