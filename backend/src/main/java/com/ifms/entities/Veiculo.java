package com.ifms.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_veiculo")
public class Veiculo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer ano;
	private String placa;
	private String renavan;
	private String patrimonio;
	private String chassi;
	private String versao;
	private String capacidadeTanque;
	private Combustivel tipoCombustivel;
	
	public Veiculo() { }
	
	public Veiculo(Long id, Integer ano, String placa, String renavan, String patrimonio, 
			String chassi, String versao, String capacidadeTanque, Combustivel tipoCombustivel) {
		this.id = id;
		this.ano = ano;
		this.placa = placa;
		this.renavan = renavan;
		this.patrimonio = patrimonio;
		this.chassi = chassi;
		this.versao = versao;
		this.capacidadeTanque = capacidadeTanque;
		this.tipoCombustivel = tipoCombustivel;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getRenavan() {
		return renavan;
	}

	public void setRenavan(String renavan) {
		this.renavan = renavan;
	}

	public String getPatrimonio() {
		return patrimonio;
	}

	public void setPatrimonio(String patrimonio) {
		this.patrimonio = patrimonio;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	public String getCapacidadeTanque() {
		return capacidadeTanque;
	}

	public void setCapacidadeTanque(String capacidadeTanque) {
		this.capacidadeTanque = capacidadeTanque;
	}

	public Combustivel getTipoCombustivel() {
		return tipoCombustivel;
	}

	public void setTipoCombustivel(Combustivel tipoCombustivel) {
		this.tipoCombustivel = tipoCombustivel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Veiculo other = (Veiculo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
	
}
