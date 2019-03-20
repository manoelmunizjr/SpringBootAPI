package com.mmz.ecommerce.api.repository.produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mmz.ecommerce.api.model.Produto;
import com.mmz.ecommerce.api.repository.filter.ProdutoFilter;

public interface ProdutoRepositoryQuery {

	public Page<Produto> filtrar(ProdutoFilter produtoFilter, Pageable pageable);
}
