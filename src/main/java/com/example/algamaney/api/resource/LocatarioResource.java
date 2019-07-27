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
import com.example.algamaney.api.model.Locatario;
import com.example.algamaney.api.model.repository.LocatarioRepository;
import com.example.algamaney.api.model.repository.filter.LocatarioFilter;
import com.example.algamaney.api.service.LocatarioService;

@RestController
@RequestMapping("/locatarios")
public class LocatarioResource {
	
	@Autowired
	private LocatarioRepository locatarioRepository;
	
	@Autowired
	private LocatarioService locatarioService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LOCATARIO') and #oauth2.hasScope('read')")
	public Page<Locatario> pesquisar(LocatarioFilter locatarioFilter, Pageable pageable) {
		return locatarioRepository.filtrar(locatarioFilter, pageable);
	}
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LOCATARIO') and #oauth2.hasScope('read')")
	public ResponseEntity<Locatario> buscarPeloCodigo(@PathVariable Long codigo) {
		Locatario locatario = locatarioRepository.findOne(codigo);
		return locatario != null ? ResponseEntity.ok(locatario) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_LOCATARIO') and #oauth2.hasScope('read')")
	public ResponseEntity<Locatario>criar(@Valid @RequestBody Locatario locatario, HttpServletResponse response){
		Locatario locatarioSalvo = locatarioRepository.save(locatario);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, locatarioSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(locatarioSalvo);
	}
	
	@DeleteMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_REMOVER_LOCATARIO') and #oauth2.hasScope('read')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		locatarioRepository.delete(codigo);
	}
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_EDITAR_LOCATARIO') and #oauth2.hasScope('read')")
	public ResponseEntity<Locatario> atualizar(@PathVariable Long codigo, @Valid @RequestBody Locatario locatario) {
		Locatario locatariolSalvo = locatarioService.atualizar(codigo, locatario);
		return ResponseEntity.ok(locatariolSalvo);
	
	}


}
