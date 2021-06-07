package com.ifms.dto;

import java.io.Serializable;

import com.ifms.entities.Marca;

public class MarcaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String descricao;
	
	public MarcaDTO() { }

	public MarcaDTO(Long id, String descricao) {
		this.id = id;
		this.descricao = descricao;
}
	
	public MarcaDTO(Marca entity) {
		this.id = entity.getId();
		this.descricao = entity.getDescricao();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
