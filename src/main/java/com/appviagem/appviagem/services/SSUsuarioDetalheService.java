package com.appviagem.appviagem.services;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.appviagem.appviagem.models.Role;
import com.appviagem.appviagem.models.Usuario;
import com.appviagem.appviagem.repository.UsuarioRepository;

@Service
@Transactional
public class SSUsuarioDetalheService implements UserDetailsService {

	private UsuarioRepository usuarioRepository;
	
	public SSUsuarioDetalheService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			Usuario usuario = this.usuarioRepository.findByUsername(username);
			
			if (usuario == null) {
				return null;
			}
			return new org.springframework.security.core.userdetails.User(usuario.getUsername(), usuario.getSenha(), getAuthorities(usuario));
		} catch (Exception e) {
			throw new UsernameNotFoundException("Usuario não encontrado!");
		}
	}
	
	private Set<GrantedAuthority> getAuthorities(Usuario usuario) {
		Set<GrantedAuthority> authorities = new HashSet<>();
		
		for (Role role: usuario.getRoles()) {
			GrantedAuthority ga = new SimpleGrantedAuthority(role.getRole());
			authorities.add(ga);
		}
		
		return authorities;
	}
	
}
