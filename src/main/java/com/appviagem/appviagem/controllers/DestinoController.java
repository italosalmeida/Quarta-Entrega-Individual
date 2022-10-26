package com.appviagem.appviagem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.appviagem.appviagem.models.Cidade;
import com.appviagem.appviagem.models.Destino;
import com.appviagem.appviagem.models.Pais;
import com.appviagem.appviagem.repository.CidadeRepository;
import com.appviagem.appviagem.repository.DestinoRepository;
import com.appviagem.appviagem.repository.PaisRepository;

@Controller
public class DestinoController {
	@Autowired
	private DestinoRepository dr;
	@Autowired
	private CidadeRepository cr;
	@Autowired
	private PaisRepository pr;
	
	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("destino/lista");
		Iterable<Destino> destinos = dr.findAll();
		mv.addObject("destinos", destinos);
		return mv;
	}
	
	@RequestMapping(value="/cadastrar-destino", method=RequestMethod.GET)
	public ModelAndView form() {
		ModelAndView mv = new ModelAndView("destino/form");
		Iterable<Cidade> cidades = cr.findAll();
		mv.addObject("cidades", cidades);
		return mv;
	}
	
	@RequestMapping(value="/cadastrar-destino", method=RequestMethod.POST)
	public String form(@RequestParam("cidade_partida_id") int cidade_partida_id, @RequestParam("cidade_destino_id") int cidade_destino_id, Destino destino, RedirectAttributes attributes) {
		if (cidade_partida_id == cidade_destino_id) {
			attributes.addFlashAttribute("mensagem", "A cidade de destino e partida não podem ser a mesma!");
			return "redirect:/cadastrar-destino";
		}
		destino.setPartida(cr.findById(cidade_partida_id));
		destino.setDestino(cr.findById(cidade_destino_id));
		dr.save(destino);
		return "redirect:/";
	}
	
	@RequestMapping("/deletar-destino/{id}")
	public String deletar(@PathVariable("id") long id, RedirectAttributes attributes) {
		Destino destino = dr.findById(id);
		dr.delete(destino);
		return "redirect:/";
	}
}
