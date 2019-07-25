package com.example.algamaney.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamaney.api.event.RecursoCriadoEvent;
import com.example.algamaney.api.model.TipoHonorario;
import com.example.algamaney.api.model.repository.TipoHonorarioRepository;

@RestController
@RequestMapping("/tipoHonorarios")
public class TipoHonorarioResource {

	@Autowired
	private TipoHonorarioRepository tipoHonorarioRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	
	@GetMapping
	public List<TipoHonorario> pesquisar() {
		return tipoHonorarioRepository.findAll();
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<TipoHonorario> buscarPeloCodigo(@PathVariable Long codigo) {
		TipoHonorario tipoHonorario = tipoHonorarioRepository.findOne(codigo);
		return tipoHonorario != null ? ResponseEntity.ok(tipoHonorario) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<TipoHonorario>criar(@Valid @RequestBody TipoHonorario tipoHonorario, HttpServletResponse response){
		TipoHonorario tipoHonorarioSalvo = tipoHonorarioRepository.save(tipoHonorario);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, tipoHonorarioSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(tipoHonorario);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		tipoHonorarioRepository.delete(codigo);
	}
	
	
	
}
