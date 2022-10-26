package com.appviagem.appviagem.repository;

import org.springframework.data.repository.CrudRepository;

import com.appviagem.appviagem.models.Role;

public interface RoleRepository extends CrudRepository<Role, String>{
	Role findByRole(String role);
}
