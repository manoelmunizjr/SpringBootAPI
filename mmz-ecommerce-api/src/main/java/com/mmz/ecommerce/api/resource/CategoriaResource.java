package com.mmz.ecommerce.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mmz.ecommerce.api.event.RecursoCriadoEvent;
import com.mmz.ecommerce.api.model.Categoria;
import com.mmz.ecommerce.api.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	// @CrossOrigin(maxAge = 10, origins = {"http://localhost:8000"} )
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and #oauth2.hasScope('read')")
	public List<Categoria> listar(){
		return categoriaRepository.findAll();
	}
	
	//@GetMapping
	//public ResponseEntity<?> listar(){
	//	List<Categoria> categorias = categoriaRepository.findAll();
	//	return !categorias.isEmpty()? ResponseEntity.ok(categorias) : ResponseEntity.noContent().build();
	//}
	
	
	//@ResponseStatus(org.springframework.http.HttpStatus.CREATED)
	//public void criar(@RequestBody Categoria categoria , HttpServletResponse response) {
	@PostMapping
	public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria , HttpServletResponse response) {
		Categoria categoriaSalva = categoriaRepository.save(categoria);
	
		publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getCodigo()));
		return ResponseEntity.status(org.springframework.http.HttpStatus.CREATED).body(categoriaSalva);
	}
	
	@GetMapping("/{codigo}")
	//public Categoria buscarPeloCodigo(@PathVariable Long codigo) {
	public ResponseEntity<?> buscarPeloCodigo(@PathVariable Long codigo) {
		
		Categoria categoria = categoriaRepository.findOne(codigo);
		return categoria != null ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
	  //return categoriaRepository.findOne(codigo);    
	}
}
