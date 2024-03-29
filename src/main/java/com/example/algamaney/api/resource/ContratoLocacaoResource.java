package com.example.algamaney.api.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamaney.api.event.RecursoCriadoEvent;
import com.example.algamaney.api.model.ContratoLocacao;
import com.example.algamaney.api.model.repository.ContratoLocacaoRepository;
import com.example.algamaney.api.model.repository.filter.ContratoLocacaoFilter;
import com.example.algamaney.api.service.ContratoLocacaoService;

@RestController
@RequestMapping("/contratosLocacao")
public class ContratoLocacaoResource {
	
	@Autowired
	private ContratoLocacaoRepository contratoLocacaoRepository;
	
	@Autowired
	private ContratoLocacaoService contratoLocacaoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CONTRATO_LOCACAO') and #oauth2.hasScope('read')")
	public Page<ContratoLocacao> pesquisar(ContratoLocacaoFilter imovelFilter, Pageable pageable) {
		return contratoLocacaoRepository.filtrar(imovelFilter, pageable);
	}
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CONTRATO_LOCACAO') and #oauth2.hasScope('read')")
	public ResponseEntity<ContratoLocacao> buscarPeloCodigo(@PathVariable Long codigo) {
		ContratoLocacao contratoLocacao = contratoLocacaoRepository.findOne(codigo);
		return contratoLocacao != null ? ResponseEntity.ok(contratoLocacao) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CONTRATO_LOCACAO') and #oauth2.hasScope('write')")
	public ResponseEntity<ContratoLocacao>criar(@Valid @RequestBody ContratoLocacao contratoLocacao, HttpServletResponse response){
		
		contratoLocacaoService.validarVinculo(contratoLocacao);
		contratoLocacaoService.buscarImovelExistenteEmContrato(contratoLocacao.getImovel().getCodigo());
		
		ContratoLocacao contratoLocacaoSalvo = contratoLocacaoRepository.save(contratoLocacao);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, contratoLocacaoSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(contratoLocacaoSalvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_CONTRATO_LOCACAO') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		contratoLocacaoRepository.delete(codigo);
	}
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_EDITAR_CONTRATO_LOCACAO') and #oauth2.hasScope('write')")
	public ResponseEntity<ContratoLocacao> atualizar(@PathVariable Long codigo, @Valid @RequestBody ContratoLocacao contratoLocacao) {
		ContratoLocacao contratoLocacaoSalvo = contratoLocacaoService.atualizar(codigo, contratoLocacao);
		return ResponseEntity.ok(contratoLocacaoSalvo);
	
	}

}
