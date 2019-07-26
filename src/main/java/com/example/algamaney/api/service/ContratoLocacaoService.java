package com.example.algamaney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.algamaney.api.model.ContratoLocacao;
import com.example.algamaney.api.model.repository.ContratoLocacaoRepository;

@Service
public class ContratoLocacaoService {

	@Autowired
	private ContratoLocacaoRepository contratoLocacaoRepository;
	
	@Autowired
	private ImovelService imovelService;
	
	
	public ContratoLocacao atualizar(Long codigo, ContratoLocacao contratoLocacao) {
		ContratoLocacao contratoLocacaoSalvo = buscarImovelExistente(codigo);
		imovelService.validarImovel(contratoLocacao.getImovel());
		BeanUtils.copyProperties(contratoLocacao, contratoLocacaoSalvo, "codigo");
		return contratoLocacaoRepository.save(contratoLocacaoSalvo);
	}
	
	private ContratoLocacao buscarImovelExistente(Long codigo) {
		ContratoLocacao contratoLocacaoSalvo = contratoLocacaoRepository.findOne(codigo);
		if (contratoLocacaoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return contratoLocacaoSalvo;
	}
	
}
