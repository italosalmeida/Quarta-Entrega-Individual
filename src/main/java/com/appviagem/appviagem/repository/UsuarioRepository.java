package com.appviagem.appviagem.repository;

import org.springframework.data.repository.CrudRepository;

import com.appviagem.appviagem.models.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {
	Usuario findByUsername(String username);
}
