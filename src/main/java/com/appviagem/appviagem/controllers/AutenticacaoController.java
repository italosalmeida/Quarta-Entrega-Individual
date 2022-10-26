package com.appviagem.appviagem.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.appviagem.appviagem.models.Role;
import com.appviagem.appviagem.models.Usuario;
import com.appviagem.appviagem.repository.RoleRepository;
import com.appviagem.appviagem.repository.UsuarioRepository;

@Controller
public class AutenticacaoController {

	@Autowired
	UsuarioRepository ur;
	
	@Autowired
	RoleRepository rr;
	
    @RequestMapping("/login")
    public String formLogin() {
        return "autenticacao/login";
    }

    @RequestMapping(value="/logar", method=RequestMethod.POST)
    public String logar() {
        return "autenticacao/login";
    }
    
    @RequestMapping(value="/cadastrar", method=RequestMethod.GET)
    public String cadastrar() {
    	return "autenticacao/cadastrar"; 
    }
    
    @RequestMapping(value="/cadastrar", method=RequestMethod.POST)
    public String cadastrar(Usuario usuario, RedirectAttributes attributes) {
    	List<Role> roles = new ArrayList<>();
    	roles.add(rr.findByRole("USER"));
    	usuario.setRoles(roles);
    	ur.save(usuario);
    	
    	attributes.addFlashAttribute("mensagem", "Cadastro realizado");
    	return "autenticacao/login";
    }
}
