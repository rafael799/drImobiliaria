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
import com.example.algamaney.api.model.Honorario;
import com.example.algamaney.api.model.repository.HonorarioRepository;
import com.example.algamaney.api.model.repository.filter.HonorarioFilter;
import com.example.algamaney.api.service.HonorarioService;

@RestController
@RequestMapping("/honorarios")
public class HonorarioResource {
	

	@Autowired
	private HonorarioRepository honorarioRepository;
	
	@Autowired
	private HonorarioService honorarioService;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_HONORARIO') and #oauth2.hasScope('read')")
	public ResponseEntity<Honorario> buscarPeloCodigo(@PathVariable Long codigo) {
		Honorario honorario = honorarioRepository.findOne(codigo);
		return honorario != null ? ResponseEntity.ok(honorario) : ResponseEntity.notFound().build();
	}
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_HONORARIO') and #oauth2.hasScope('read')")
	public Page<Honorario> pesquisar(HonorarioFilter locadorFilter, Pageable pageable) {
		return honorarioRepository.filtrar(locadorFilter, pageable);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_HONORARIO') and #oauth2.hasScope('read')")
	public ResponseEntity<Honorario>criar(@Valid @RequestBody Honorario honorario, HttpServletResponse response){
		honorarioService.validarVinculo(honorario);
		Honorario honorarioSalvo = honorarioRepository.save(honorario);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, honorarioSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(honorario);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_HONORARIO') and #oauth2.hasScope('read')")
	public void remover(@PathVariable Long codigo) {
		honorarioRepository.delete(codigo);
	}
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_EDITAR_HONORARIO') and #oauth2.hasScope('read')")
	public ResponseEntity<Honorario> atualizar(@PathVariable Long codigo, @Valid @RequestBody Honorario honorario) {
		Honorario honorarioAtualizar = honorarioService.atualizar(codigo, honorario);
		return ResponseEntity.ok(honorarioAtualizar);
	
	}

}
