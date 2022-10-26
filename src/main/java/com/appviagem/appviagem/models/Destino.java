package com.appviagem.appviagem.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Destino implements Serializable {
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="cidade_destino_id")
	private Cidade destino;
	
	@ManyToOne
	@JoinColumn(name="cidade_partida_id")
	private Cidade partida;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Cidade getDestino() {
		return destino;
	}

	public void setDestino(Cidade destino) {
		this.destino = destino;
	}

	public Cidade getPartida() {
		return partida;
	}

	public void setPartida(Cidade partida) {
		this.partida = partida;
	}
    
    
}
