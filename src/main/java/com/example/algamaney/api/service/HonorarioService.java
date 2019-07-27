package com.example.algamaney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.algamaney.api.model.Honorario;
import com.example.algamaney.api.model.repository.HonorarioRepository;

@Service
public class HonorarioService {
	
	@Autowired
	private HonorarioRepository HonorarioRepository;
	
	@Autowired
	private ContratoLocacaoService contratoLocacaoService;
	
	@Autowired
	private TipoHonorarioService tipoHonorarioService;
	
	public Honorario atualizar(Long codigo, Honorario honorario) {
		Honorario honorarioSalva = buscarHonorarioExistente(codigo);
		validarVinculo(honorario);
		BeanUtils.copyProperties(honorario, honorarioSalva, "codigo");
		return HonorarioRepository.save(honorarioSalva);
	}
	
	private Honorario buscarHonorarioExistente(Long codigo) {
		Honorario honorarioSalvo = HonorarioRepository.findOne(codigo);
		if (honorarioSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return honorarioSalvo;
	}
	
	public void validarVinculo(Honorario honorario) {
		contratoLocacaoService.validarContratoLocacao(honorario.getContratoLocacao());
		tipoHonorarioService.validarTipoHonorario(honorario.getTipoHonorario());
	}


}
