package com.appviagem.appviagem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.appviagem.appviagem.models.Cidade;
import com.appviagem.appviagem.models.Pais;

public interface CidadeRepository extends CrudRepository<Cidade, String>{
	List<Cidade> getByPais(Pais pais);
	Cidade findById(long id);
}
