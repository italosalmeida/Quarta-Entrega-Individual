package com.appviagem.appviagem.repository;

import org.springframework.data.repository.CrudRepository;

import com.appviagem.appviagem.models.Pais;

public interface PaisRepository extends CrudRepository<Pais, String> {
	Pais findById(long id);
}
