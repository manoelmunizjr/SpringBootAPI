package com.mmz.ecommerce.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.mmz.ecommerce.api.model.Produto;
import com.mmz.ecommerce.api.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;
	
	public Produto atualizar(Long codigo, Produto produto) {
		Produto produtoSalvo = buscarProdutoPeloCodigo(codigo);
		BeanUtils.copyProperties(produto, produtoSalvo, "codigo");
		return produtoRepository.save(produtoSalvo);
	}

	private Produto buscarProdutoPeloCodigo(Long codigo) {
		// TODO Auto-generated method stub
		Produto produtoSalvo = produtoRepository.findOne(codigo);
		if (produtoSalvo == null) {
			throw new EmptyResultDataAccessException(1); 
		}
		return produtoSalvo;
	}
	
}
