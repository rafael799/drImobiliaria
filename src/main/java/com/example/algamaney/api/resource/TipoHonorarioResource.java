package com.example.algamaney.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamaney.api.model.TipoHonorario;
import com.example.algamaney.api.model.repository.TipoHonorarioRepository;

@RestController
@RequestMapping("/tipoHonorarios")
public class TipoHonorarioResource {

	@Autowired
	private TipoHonorarioRepository tipoHonorarioRepository;
	
	@GetMapping
	public List<TipoHonorario> pesquisar() {
		return tipoHonorarioRepository.findAll();
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<TipoHonorario> buscarPeloCodigo(@PathVariable Long codigo) {
		TipoHonorario tipoHonorario = tipoHonorarioRepository.findOne(codigo);
		System.out.println("Tlinerzinho coisa linda");
		return tipoHonorario != null ? ResponseEntity.ok(tipoHonorario) : ResponseEntity.notFound().build();
	}
	
	
	
}
