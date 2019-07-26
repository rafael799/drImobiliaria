package com.example.algamaney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.algamaney.api.model.Locatario;
import com.example.algamaney.api.model.repository.LocatarioRepository;

@Service
public class LocatarioService {
	@Autowired
	private LocatarioRepository locatarioRepository;
	
	public Locatario atualizar(Long codigo, Locatario locatario) {
		Locatario locatarioSalvo = buscarILocatarioExistente(codigo);
		BeanUtils.copyProperties(locatario, locatarioSalvo, "codigo");
		return locatarioRepository.save(locatarioSalvo);
	}
	
	private Locatario buscarILocatarioExistente(Long codigo) {
		Locatario locatariosalvo = locatarioRepository.findOne(codigo);
		if (locatariosalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return locatariosalvo;
	}
}
