package com.example.algamaney.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.algamaney.api.model.TipoHonorario;
import com.example.algamaney.api.model.repository.TipoHonorarioRepository;

@Service
public class TipoHonorarioService {
	
	@Autowired
	private TipoHonorarioRepository tipoHonorarioRepository;
	
	public TipoHonorario atualizar(Long codigo, TipoHonorario tipoHonorario) {
		buscarTipoHonorarioExistente(codigo);
		tipoHonorario.setCodigo(codigo);

		return tipoHonorarioRepository.save(tipoHonorario);
	}
	
	private TipoHonorario buscarTipoHonorarioExistente(Long codigo) {
		TipoHonorario tipoHonorarioSalvo = tipoHonorarioRepository.findOne(codigo);
		if (tipoHonorarioSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return tipoHonorarioSalvo;
	}


}
