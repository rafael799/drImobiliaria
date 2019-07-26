package com.example.algamaney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.algamaney.api.exceptionHandler.ImovelInexistesteOuInativo;
import com.example.algamaney.api.model.Imovel;
import com.example.algamaney.api.model.repository.ImovelRepository;

@Service
public class ImovelService {
	
	@Autowired
	private ImovelRepository imovelRepository;
	
	public Imovel atualizar(Long codigo, Imovel imovel) {
		Imovel imovelSalvo = buscarImovelExistente(codigo);
		BeanUtils.copyProperties(imovel, imovelSalvo, "codigo");
		return imovelRepository.save(imovelSalvo);
	}
	
	private Imovel buscarImovelExistente(Long codigo) {
		Imovel imovelsalvo = imovelRepository.findOne(codigo);
		if (imovelsalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return imovelsalvo;
	}
	
	public void validarImovel(Imovel imovel) {
		
		if (imovel.getCodigo() != null) {
			imovel = imovelRepository.findOne(imovel.getCodigo());
		}

		if (imovel == null || imovel.getSituacao().equals(false)) {
			throw new ImovelInexistesteOuInativo();
		}
	}


}
