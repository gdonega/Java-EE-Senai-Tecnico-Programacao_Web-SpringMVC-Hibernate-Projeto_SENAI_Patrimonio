package br.senai.sp.informatica.senaipatrimonio.model;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Patrimonio {

	// Columns
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "nome", length = 40, nullable = false, unique = false)
	@NotNull
	@Size(min = 1, max = 40)
	private String nome;

	@Column(name = "data_cadastro", nullable = false, unique = false)
	private Date dt_cadastro;

	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false) // alterar informações sobre a chave estrangeira
	private Usuario cadastrante;

	@ManyToOne
	@JoinColumn(name = "id_categoria", nullable = false)
	private Categoria categoria;

	// Getters && Setters
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

	public Date getDt_cadastro() {
		return dt_cadastro;
	}

	public void setDt_cadastro(Date dt_cadastro) {
		this.dt_cadastro = dt_cadastro;
	}

	public Usuario getCadastrante() {
		return cadastrante;
	}

	public void setCadastrante(Usuario cadastrante) {
		this.cadastrante = cadastrante;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Patrimonio [id=" + id + ", nome=" + nome + ", dt_cadastro=" + dt_cadastro + ", cadastrante="
				+ cadastrante + ", categoria=" + categoria + "]";
	}

	
	
	
}
