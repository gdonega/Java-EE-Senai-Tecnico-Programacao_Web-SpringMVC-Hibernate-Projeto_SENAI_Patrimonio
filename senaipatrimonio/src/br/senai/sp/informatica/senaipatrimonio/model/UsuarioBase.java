package br.senai.sp.informatica.senaipatrimonio.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@MappedSuperclass
public abstract class UsuarioBase {

	
//	private Long id;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usu")
	protected Long id;
		
	
//	private String nome;
	@Column(name = "nome", length = 20, nullable = false, unique = false)
	@NotNull
	@Size(min = 1, max = 20)
	private String nome;

//	private TipoUsuario tipo;
	@Column(name = "tipo", nullable = false, unique = false)
	@NotNull
	private TipoUsuario tipo;

//	private String email;
	@Column(name = "email", length = 120, nullable = false, unique = true)
	@NotNull
	@Size(min = 1,max=120)
	private String email;

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

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public void trimEmail() {
		this.email.trim();
	}
	
}
