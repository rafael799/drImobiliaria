package com.example.algamaney.api.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.algamaney.api.event.RecursoCriadoEvent;
import com.example.algamaney.api.model.Imovel;
import com.example.algamaney.api.model.repository.ImovelRepository;
import com.example.algamaney.api.model.repository.filter.ImovelFilter;
import com.example.algamaney.api.service.ImovelService;

@RestController
@RequestMapping("/imoveis")
public class ImovelResource {

	@Autowired
	private ImovelRepository imovelRepository;
	
	@Autowired
	private ImovelService imovelService;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_IMOVEL') and #oauth2.hasScope('read')")
	public Page<Imovel> pesquisar(ImovelFilter imovelFilter, Pageable pageable) {
		return imovelRepository.filtrar(imovelFilter, pageable);
	}
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_IMOVEL') and #oauth2.hasScope('read')")
	public ResponseEntity<Imovel> buscarPeloCodigo(@PathVariable Long codigo) {
		Imovel imovel = imovelRepository.findOne(codigo);
		return imovel != null ? ResponseEntity.ok(imovel) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_IMOVEL') and #oauth2.hasScope('read')")
	public ResponseEntity<Imovel>criar(@Valid @RequestBody Imovel imovel, HttpServletResponse response){
		Imovel imovelSalvo = imovelRepository.save(imovel);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, imovelSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(imovelSalvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_IMOVEL') and #oauth2.hasScope('read')")
	public void remover(@PathVariable Long codigo) {
		imovelRepository.delete(codigo);
	}
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_EDITAR_IMOVEL') and #oauth2.hasScope('read')")
	public ResponseEntity<Imovel> atualizar(@PathVariable Long codigo, @Valid @RequestBody Imovel imovel) {
		Imovel imovelSalvo = imovelService.atualizar(codigo, imovel);
		return ResponseEntity.ok(imovelSalvo);
	
	}
	
}
