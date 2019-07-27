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
import com.example.algamaney.api.model.Locador;
import com.example.algamaney.api.model.repository.LocadorRepository;
import com.example.algamaney.api.model.repository.filter.LocadorFilter;
import com.example.algamaney.api.service.LocadorService;

@RestController
@RequestMapping("/locadores")
public class LocadorResource {
	
	@Autowired
	private LocadorRepository locadorRepository;
	
	@Autowired
	private LocadorService locadorService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LOCADOR') and #oauth2.hasScope('read')")
	public Page<Locador> pesquisar(LocadorFilter locadorFilter, Pageable pageable) {
		return locadorRepository.filtrar(locadorFilter, pageable);
	}
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LOCADOR') and #oauth2.hasScope('read')")
	public ResponseEntity<Locador> buscarPeloCodigo(@PathVariable Long codigo) {
		Locador locador = locadorRepository.findOne(codigo);
		return locador != null ? ResponseEntity.ok(locador) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_LOCADOR') and #oauth2.hasScope('read')")
	public ResponseEntity<Locador>criar(@Valid @RequestBody Locador locador, HttpServletResponse response){
		Locador locadorSalvo = locadorRepository.save(locador);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, locadorSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(locadorSalvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_LOCADOR') and #oauth2.hasScope('read')")
	public void remover(@PathVariable Long codigo) {
		locadorRepository.delete(codigo);
	}
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_EDITAR_LOCADOR') and #oauth2.hasScope('read')")
	public ResponseEntity<Locador> atualizar(@PathVariable Long codigo, @Valid @RequestBody Locador locador) {
		Locador locadorlSalvo = locadorService.atualizar(codigo, locador);
		return ResponseEntity.ok(locadorlSalvo);
	
	}

}
