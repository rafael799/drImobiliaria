package com.example.algamaney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.algamaney.api.model.Locador;
import com.example.algamaney.api.model.repository.LocadorRepository;

@Service
public class LocadorService {

	@Autowired
	private LocadorRepository locadorRepository;
	
	public Locador atualizar(Long codigo, Locador locador) {
		Locador locadorSalvo = buscarILocadorExistente(codigo);
		BeanUtils.copyProperties(locador, locadorSalvo, "codigo");
		return locadorRepository.save(locadorSalvo);
	}
	
	private Locador buscarILocadorExistente(Long codigo) {
		Locador locadorsalvo = locadorRepository.findOne(codigo);
		if (locadorsalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return locadorsalvo;
	}
}
