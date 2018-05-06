package br.senai.sp.informatica.senaipatrimonio.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class ItemPatrimonio {
	
	//Columns
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_patrimonio", nullable = false)
	private Patrimonio patrimonio;
	
	@ManyToOne
	@JoinColumn(name = "id_ambiente_atual", nullable = false)
	private Ambiente ambienteAtual;
	
	@ManyToOne
	@JoinColumn(name = "id_cadastrante", nullable = false)
	private Usuario cadastrante;

	
	
	// Getters && Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Patrimonio getPatrimonio() {
		return patrimonio;
	}

	public void setPatrimonio(Patrimonio patrimonio) {
		this.patrimonio = patrimonio;
	}

	public Ambiente getAmbienteAtual() {
		return ambienteAtual;
	}

	public void setAmbienteAtual(Ambiente ambienteAtual) {
		this.ambienteAtual = ambienteAtual;
	}

	public Usuario getCadastrante() {
		return cadastrante;
	}

	public void setCadastrante(Usuario cadastrante) {
		this.cadastrante = cadastrante;
	}

	@Override
	public String toString() {
		return "ItemPatrimonio [id=" + id + ", patrimonio=" + patrimonio + ", ambienteAtual=" + ambienteAtual
				+ ", cadastrante=" + cadastrante + "]";
	}
	
	
	
	
}
