package com.mmz.ecommerce.api.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mmz.ecommerce.api.event.RecursoCriadoEvent;
import com.mmz.ecommerce.api.model.Produto;
import com.mmz.ecommerce.api.repository.ProdutoRepository;
import com.mmz.ecommerce.api.repository.filter.ProdutoFilter;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {
  
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public Page<Produto> pesquisar(ProdutoFilter produtoFilter, Pageable pageable){
		return produtoRepository.filtrar(produtoFilter, pageable);
	}
		
	@GetMapping("/{codigo}")
	//public Categoria buscarPeloCodigo(@PathVariable Long codigo) {
	public ResponseEntity<Produto> buscarPeloCodigo(@PathVariable Long codigo) {		
		Produto produto = produtoRepository.findOne(codigo);
		return produto != null ? ResponseEntity.ok(produto) : ResponseEntity.notFound().build();	  
	}
	
	@PostMapping
	public ResponseEntity<Produto> criar(@Valid @RequestBody Produto produto , HttpServletResponse response) {
		Produto produtoSalvo = produtoRepository.save(produto);	
		publisher.publishEvent(new RecursoCriadoEvent(this, response, produtoSalvo.getCodigo()));
		return ResponseEntity.status(org.springframework.http.HttpStatus.CREATED).body(produtoSalvo);
	}
	
}
