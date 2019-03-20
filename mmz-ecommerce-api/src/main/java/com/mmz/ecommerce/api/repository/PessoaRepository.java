package com.mmz.ecommerce.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mmz.ecommerce.api.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
