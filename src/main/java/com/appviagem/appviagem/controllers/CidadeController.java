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
import com.appviagem.appviagem.repository.DestinoRepository;
import com.appviagem.appviagem.repository.PaisRepository;

@Controller
public class CidadeController {
	@Autowired
	private CidadeRepository cr;
	@Autowired
	private PaisRepository pr;
	@Autowired
	private DestinoRepository dr;
	
	@RequestMapping(value="/listar-cidades", method=RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("cidade/lista");
		Iterable<Cidade> cidades = cr.findAll();
		mv.addObject("cidades", cidades);
		return mv;
	}
	
	@RequestMapping(value="/cadastrar-cidade", method=RequestMethod.GET)
	public ModelAndView form() {
		ModelAndView mv = new ModelAndView("cidade/form");
		Iterable<Pais> paises = pr.findAll();
		mv.addObject("paises", paises);
		return mv;
	}
	
	@RequestMapping(value="/cadastrar-cidade", method=RequestMethod.POST)
	public String form(@RequestParam("pais_id") int pais_id, @Valid Cidade cidade, RedirectAttributes attributes) {
		if (cidade.getNome().equals("")) {
			attributes.addFlashAttribute("mensagem", "Nome não pode ser vazio!");
			return "redirect:/cadastrar-cidade";
		}
		
		cidade.setPais(pr.findById(pais_id));
		cr.save(cidade);		
		return "redirect:/listar-cidades";
	}
	
	@RequestMapping(value="/detalhes-cidade/{id}", method=RequestMethod.GET)
	public ModelAndView detalhes(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("cidade/detalhes");
		Cidade cidade = cr.findById(id);
		mv.addObject("cidade", cidade);
		Iterable<Pais> paises = pr.findAll();
		mv.addObject("paises", paises);
		return mv;
	}
	
	@RequestMapping(value="/atualiza-cidade/{id}", method=RequestMethod.POST)
	public String form(@PathVariable("id") long id, @RequestParam("nome") String nome, @RequestParam("pais_id") long pais_id, Cidade cidade) {
		cidade.setNome(nome);
		cidade.setPais(pr.findById(pais_id));
		cr.save(cidade);
		return "redirect:/listar-cidades";
	}
	
	@RequestMapping("/deletar-cidade/{id}")
	public String deletar(@PathVariable("id") long id, RedirectAttributes attributes) {
		Cidade cidade = cr.findById(id);
		if (dr.findAllByDestino(cidade).size() > 0 || dr.findAllByPartida(cidade).size() > 0) {
			attributes.addFlashAttribute("mensagem", "Esta cidade não pode ser deletada porque está relacionada à um ou mais Destinos ou Partidas!");
			return "redirect:/listar-cidades";
		}
		
		cr.delete(cidade);
		return "redirect:/listar-cidades"; 
	}
}
