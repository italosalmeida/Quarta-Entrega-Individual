package com.appviagem.appviagem.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Cidade implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	private String nome;
	
	@ManyToOne
	private Pais pais;
	
	@OneToMany(mappedBy="destino", fetch = FetchType.EAGER)
	private List<Destino> destinos;
	
	@OneToMany(mappedBy="partida", fetch = FetchType.EAGER)
	private List<Destino> partidas;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public List<Destino> getDestinos() {
		return destinos;
	}

	public void setDestinos(List<Destino> destinos) {
		this.destinos = destinos;
	}

	public List<Destino> getPartidas() {
		return partidas;
	}

	public void setPartidas(List<Destino> partidas) {
		this.partidas = partidas;
	}
	
	
}
