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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamaney.api.event.RecursoCriadoEvent;
import com.example.algamaney.api.model.TipoHonorario;
import com.example.algamaney.api.model.repository.TipoHonorarioRepository;
import com.example.algamaney.api.service.TipoHonorarioService;

@RestController
@RequestMapping("/tipoHonorarios")
public class TipoHonorarioResource {

	@Autowired
	private TipoHonorarioRepository tipoHonorarioRepository;
	
	@Autowired
	private TipoHonorarioService tipoHonorarioService;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_TIPO_HONORARIO') and #oauth2.hasScope('read')")
	public ResponseEntity<TipoHonorario> buscarPeloCodigo(@PathVariable Long codigo) {
		TipoHonorario tipoHonorario = tipoHonorarioRepository.findOne(codigo);
		return tipoHonorario != null ? ResponseEntity.ok(tipoHonorario) : ResponseEntity.notFound().build();
	}
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_TIPO_HONORARIO') and #oauth2.hasScope('read')")
	public Page<TipoHonorario> pesquisar(@RequestParam(required = false, defaultValue = "%") String descricao, Pageable pageable) {
		return tipoHonorarioRepository.findByDescricaoContaining(descricao, pageable);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_TIPO_HONORARIO') and #oauth2.hasScope('read')")
	public ResponseEntity<TipoHonorario>criar(@Valid @RequestBody TipoHonorario tipoHonorario, HttpServletResponse response){
		TipoHonorario tipoHonorarioSalvo = tipoHonorarioRepository.save(tipoHonorario);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, tipoHonorarioSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(tipoHonorario);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_TIPO_HONORARIO') and #oauth2.hasScope('read')")
	public void remover(@PathVariable Long codigo) {
		tipoHonorarioRepository.delete(codigo);
	}
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_EDITAR_TIPO_HONORARIO') and #oauth2.hasScope('read')")
	public ResponseEntity<TipoHonorario> atualizar(@PathVariable Long codigo, @Valid @RequestBody TipoHonorario tipoHonorario) {
		TipoHonorario tipoHonorarioAtualizar = tipoHonorarioService.atualizar(codigo, tipoHonorario);
		return ResponseEntity.ok(tipoHonorarioAtualizar);
	
	}
	
}
