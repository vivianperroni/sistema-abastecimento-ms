package com.ifms.dto;

import java.io.Serializable;

import com.ifms.entities.Cidade;
import com.ifms.entities.Estado;

public class CidadeDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private Estado UF;
	
	public CidadeDTO() { }

	public CidadeDTO(Long id, String nome, Estado UF) {
		this.id = id;
		this.nome = nome;
		this.UF = UF;
}	
	
	public CidadeDTO(Cidade entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.UF = entity.getUF();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getUF() {
		return UF;
	}

	public void setUF(Estado uF) {
		UF = uF;
	}
	
}
