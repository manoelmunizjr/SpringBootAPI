package com.mmz.ecommerce.api.service;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.mmz.ecommerce.api.model.Pessoa;
import com.mmz.ecommerce.api.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	public Pessoa atualizar(Long codigo, Pessoa pessoa) {
		Pessoa pessoaSalva = buscarPessoaPeloCodigo(codigo);
		BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
		return pessoaRepository.save(pessoaSalva);
	}

	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		System.out.println(" =====>atualizarPropriedadeAtivo.  chegou na parada. Codigo "+codigo);
		Pessoa pessoaSalva = buscarPessoaPeloCodigo(codigo);
		pessoaSalva.setAtivo(ativo);
		pessoaRepository.save(pessoaSalva);
	}
	
	private Pessoa buscarPessoaPeloCodigo(Long codigo) {
		System.out.println(" =====> 2 buscarPessoaPeloCodigo "+codigo);
		Pessoa pessoaSalva = pessoaRepository.findOne(codigo);
		System.out.println(" =====> 3 buscarPessoaPeloCodigo buscou Lebrao");
		if (pessoaSalva == null) {
			throw new EmptyResultDataAccessException(1); 
		}
		return pessoaSalva;
	}
}
