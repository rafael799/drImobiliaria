package com.example.algamaney.api.model.repository.imovel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.algamaney.api.model.Imovel;
import com.example.algamaney.api.model.repository.filter.ImovelFilter;

public interface ImovelRepositoryQuery {
	public Page<Imovel> filtrar(ImovelFilter imovelFilter, Pageable pageable);
}
