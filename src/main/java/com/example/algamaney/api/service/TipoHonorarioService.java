package com.example.algamaney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.algamaney.api.exceptionHandler.TipoHonorarioInexistente;
import com.example.algamaney.api.model.TipoHonorario;
import com.example.algamaney.api.model.repository.TipoHonorarioRepository;

@Service
public class TipoHonorarioService {
	
	@Autowired
	private TipoHonorarioRepository tipoHonorarioRepository;
	
	public TipoHonorario atualizar(Long codigo, TipoHonorario tipoHonorario) {
		TipoHonorario tipoHonorarioSalva = buscarTipoHonorarioExistente(codigo);
		BeanUtils.copyProperties(tipoHonorario, tipoHonorarioSalva, "codigo");
		return tipoHonorarioRepository.save(tipoHonorarioSalva);
	}
	
	private TipoHonorario buscarTipoHonorarioExistente(Long codigo) {
		TipoHonorario tipoHonorarioSalvo = tipoHonorarioRepository.findOne(codigo);
		if (tipoHonorarioSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return tipoHonorarioSalvo;
	}
	
	public void validarTipoHonorario(TipoHonorario tipoHonorario) {
		
		if (tipoHonorario != null && tipoHonorario.getCodigo() != null) {
			tipoHonorario = tipoHonorarioRepository.findOne(tipoHonorario.getCodigo());
		}

		if (tipoHonorario == null) {
			throw new TipoHonorarioInexistente();
		}
	}


}
