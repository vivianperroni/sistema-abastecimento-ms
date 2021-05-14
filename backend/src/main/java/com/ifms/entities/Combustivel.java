package com.ifms.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_combustivel")
public class Combustivel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Combustivel gasolina;
	private Combustivel etanol;
	private Combustivel flex;
	private Combustivel GNV;
	private Combustivel diesel;
	
	@OneToMany(mappedBy = "combustivel")
	private List<Abastecimento> abastecimentos;
	
	public Combustivel() { }
	
	public Combustivel(Long id, Combustivel gasolina, Combustivel etanol, Combustivel flex, Combustivel GNV, Combustivel diesel) {
		this.id = id;
		this.gasolina = gasolina;
		this.etanol = etanol;
		this.flex = flex;
		this.GNV = GNV;
		this.diesel = diesel;
}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Combustivel getGasolina() {
		return gasolina;
	}

	public void setGasolina(Combustivel gasolina) {
		this.gasolina = gasolina;
	}

	public Combustivel getEtanol() {
		return etanol;
	}

	public void setEtanol(Combustivel etanol) {
		this.etanol = etanol;
	}

	public Combustivel getFlex() {
		return flex;
	}

	public void setFlex(Combustivel flex) {
		this.flex = flex;
	}

	public Combustivel getGNV() {
		return GNV;
	}

	public void setGNV(Combustivel gNV) {
		GNV = gNV;
	}

	public Combustivel getDiesel() {
		return diesel;
	}

	public void setDiesel(Combustivel diesel) {
		this.diesel = diesel;
	}

	public List<Abastecimento> getAbastecimentos() {
		return abastecimentos;
	}

	public void setAbastecimentos(List<Abastecimento> abastecimentos) {
		this.abastecimentos = abastecimentos;
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
		Combustivel other = (Combustivel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	

}
