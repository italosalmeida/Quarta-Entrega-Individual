package com.appviagem.appviagem.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.appviagem.appviagem.models.Cidade;
import com.appviagem.appviagem.models.Pais;
import com.appviagem.appviagem.repository.CidadeRepository;
import com.appviagem.appviagem.repository.PaisRepository;

@Controller
public class PaisController {
	@Autowired
	private PaisRepository pr;
	@Autowired
	private CidadeRepository cr;
	
	@RequestMapping(value="/listar-paises", method=RequestMethod.GET)
	public ModelAndView list(RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("pais/lista");
		Iterable<Pais> paises = pr.findAll();
		mv.addObject("paises", paises);
		return mv;
	}
	
	@RequestMapping(value="/cadastrar-pais", method=RequestMethod.GET)
	public String form() {
		return "pais/form";
	}
	
	@RequestMapping(value="/cadastrar-pais", method=RequestMethod.POST)
	public String form(@Valid Pais pais, RedirectAttributes attributes) {
		if (pais.getNome().equals("")) {
			attributes.addFlashAttribute("mensagem", "Nome não pode ser vazio!");
			return "redirect:/cadastrar-pais";
		}
		pr.save(pais);
		return "redirect:/listar-paises";
	}
	
	@RequestMapping(value="/atualiza-pais/{id}", method=RequestMethod.POST)
	public String form(@PathVariable("id") long id, @RequestParam("nome") String nome, Pais pais) {
		pais.setNome(nome);
		pr.save(pais);
		return "redirect:/listar-paises";
	}
	
	@RequestMapping(value="/detalhes-pais/{id}", method=RequestMethod.GET)
	public ModelAndView detalhes(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("pais/detalhes");
		Pais pais = pr.findById(id);
		List<Cidade> cidades = cr.getByPais(pais);
		pais.setCidades(cidades);
		mv.addObject("pais", pais);
		return mv;
	}
		
	@RequestMapping("/deletar-pais/{id}")
	public String deletar(@PathVariable("id") long id, RedirectAttributes attributes) {
		Pais pais = pr.findById(id);
		
		if (cr.getByPais(pais).size() > 0) {
			attributes.addFlashAttribute("mensagem", pais.getNome()+" não pode ser deletado, porque tem cidades relacionadas!");
			return "redirect:/listar-paises";
		}
		
		pr.delete(pais);
		return "redirect:/listar-paises";
	}
}
