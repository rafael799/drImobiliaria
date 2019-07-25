package com.example.algamaney.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.algamaney.api.model.Imovel;
import com.example.algamaney.api.model.repository.ImovelRepository;

@Service
public class ImovelService {
	
	@Autowired
	private ImovelRepository imovelRepository;
	
	public Imovel atualizar(Long codigo, Imovel imovel) {
		buscarImovelExistente(codigo);
		imovel.setCodigo(codigo);

		return imovelRepository.save(imovel);
	}
	
	private Imovel buscarImovelExistente(Long codigo) {
		Imovel imovelsalvo = imovelRepository.findOne(codigo);
		if (imovelsalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return imovelsalvo;
	}


}
