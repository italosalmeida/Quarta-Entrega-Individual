package com.appviagem.appviagem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.appviagem.appviagem.models.Cidade;
import com.appviagem.appviagem.models.Destino;

public interface DestinoRepository extends CrudRepository<Destino, String> {
	Destino findById(long id);
	List<Destino> findAllByDestino(Cidade cidade);
	List<Destino> findAllByPartida(Cidade cidade);
}
