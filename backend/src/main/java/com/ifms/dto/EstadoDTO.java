package com.ifms.dto;

import java.io.Serializable;

import com.ifms.entities.Estado;

public class EstadoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String UF;
	
	public EstadoDTO() { }

	public EstadoDTO(Long id, String UF) {
		this.id = id;
		this.UF = UF;
	}
	
	public EstadoDTO(Estado entity) {
		this.id = entity.getId();
		this.UF = entity.getUF();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUF() {
		return UF;
	}

	public void setUF(String uF) {
		UF = uF;
	}

	
}
