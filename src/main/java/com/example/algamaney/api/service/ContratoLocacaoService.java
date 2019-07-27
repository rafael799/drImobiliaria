package com.example.algamaney.api.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.algamaney.api.exceptionHandler.ContratoJaPossuiEsteImovel;
import com.example.algamaney.api.exceptionHandler.ContratoLocacaoInexistente;
import com.example.algamaney.api.model.ContratoLocacao;
import com.example.algamaney.api.model.Imovel;
import com.example.algamaney.api.model.repository.ContratoLocacaoRepository;
import com.example.algamaney.api.model.repository.ImovelRepository;

@Service
public class ContratoLocacaoService {

	@Autowired
	private ContratoLocacaoRepository contratoLocacaoRepository;
	
	@Autowired
	private ImovelService imovelService;
	
	@Autowired
	private LocatarioService locatarioService;
	
	@Autowired
	private LocadorService locadorService;
	
	@Autowired
	private ImovelRepository imovelRepository;
	
	
	public ContratoLocacao atualizar(Long codigo, ContratoLocacao contratoLocacao) {
		ContratoLocacao contratoLocacaoSalvo = buscarContratoExistente(codigo);
		validarVinculo(contratoLocacao);
		
		if(contratoLocacaoSalvo.getImovel().getCodigo() != contratoLocacao.getImovel().getCodigo()) {
			buscarImovelExistenteEmContrato(contratoLocacao.getImovel().getCodigo());
		}
		
		BeanUtils.copyProperties(contratoLocacao, contratoLocacaoSalvo, "codigo");
		return contratoLocacaoRepository.save(contratoLocacaoSalvo);
	}
	
	private ContratoLocacao buscarContratoExistente(Long codigo) {
		ContratoLocacao contratoLocacaoSalvo = contratoLocacaoRepository.findOne(codigo);
		if (contratoLocacaoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return contratoLocacaoSalvo;
	}
	
	public void validarVinculo(ContratoLocacao contratoLocacao) {
		imovelService.validarImovel(contratoLocacao.getImovel());
		locatarioService.validarLocatario(contratoLocacao.getLocatario());
		locadorService.validarLocador(contratoLocacao.getLocador());
	}
	
	public void buscarImovelExistenteEmContrato(Long codigoImovel) {
		Imovel imovel = imovelRepository.findOne(codigoImovel);
		imovelService.validarImovel(imovel);
		
		ContratoLocacao contratoLocacao = new ContratoLocacao();
		contratoLocacao.setImovel(imovel);
		contratoLocacao.setSituacao(ContratoLocacao.ATIVO);
		List<ContratoLocacao> listaContratoLocacao = contratoLocacaoRepository.findByImovelAndSituacao(imovel, Imovel.ATIVO);
		
		if (!listaContratoLocacao.isEmpty()) {
			throw new ContratoJaPossuiEsteImovel();
		}
	}
	
	public void validarContratoLocacao(ContratoLocacao contratoLocacao) {
		
		if (contratoLocacao != null && contratoLocacao.getCodigo() != null) {
			contratoLocacao = contratoLocacaoRepository.findOne(contratoLocacao.getCodigo());
		}

		if (contratoLocacao == null) {
			throw new ContratoLocacaoInexistente();
		}
	}

	
}
