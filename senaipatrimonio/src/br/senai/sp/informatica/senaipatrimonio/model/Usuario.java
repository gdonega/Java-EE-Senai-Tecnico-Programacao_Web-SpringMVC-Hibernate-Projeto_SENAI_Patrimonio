package br.senai.sp.informatica.senaipatrimonio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.util.DigestUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Usuario extends UsuarioBase
//implements Authentication
{	
	public Usuario(Long id) {
		super();
		this.setId(id);
	}

	public Usuario() {
		super();
	}
	
	
	

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id_usu")
//	private Long id;

//	@Column(name = "nome", length = 20, nullable = false, unique = false)
//	@NotNull
//	@Size(min = 1, max = 20)
//	private String nome;


	@Column(name = "sobrenome", length = 40, nullable = false, unique = false)
	@NotNull
	@Size(min = 1, max = 40)
	private String sobrenome;

//	@Column(name = "email", length = 120, nullable = false, unique = true)
//	@NotNull
//	@Size(min = 1,max=120)
//	private String email;

	@Column(name = "senha", length = 32, nullable = false, unique = false)
	@NotNull
	@Size(min = 6, max = 32)
	private String senha;

	@Transient
	@Size(min = 6, max = 32)
	private String senhaAntiga;

	@Transient
	@Size(min = 6, max = 32)
	private String senhaNovaConfirmacao;
	
//	@Column(name = "tipo", nullable = false, unique = false)
//	@NotNull
//	private TipoUsuario tipo;

//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}

//	public String getNome() {
//		return nome;
//	}
//
//	public void setNome(String nome) {
//		this.nome = nome;
//	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}

	@JsonIgnore
	public String getSenha() {
		return senha;
	}

	
	@JsonProperty
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@JsonIgnore
	public String getSenhaAntiga() {
		return senhaAntiga;
	}
	@JsonProperty
	public void setSenhaAntiga(String senhaAntiga) {
		this.senhaAntiga = senhaAntiga;
	}
	@JsonIgnore
	public String getSenhaNovaConfirmacao() {
		return senhaNovaConfirmacao;
	}
	@JsonProperty
	public void setSenhaNovaConfirmacao(String senhaNovaConfirmacao) {
		this.senhaNovaConfirmacao = senhaNovaConfirmacao;
	}

//	public TipoUsuario getTipo() {
//		return tipo;
//	}
//
//	public void setTipo(TipoUsuario tipo) {
//		this.tipo = tipo;
//	}
	
	@JsonIgnore
	public void hashearSenha() {
		this.senha = DigestUtils.md5DigestAsHex(this.senha.getBytes());
	}


	@JsonIgnore
	public Boolean getAdmConfirm() {
		if(this.getTipo().equals(TipoUsuario.ADMIN) ) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "Usuario [sobrenome=" + sobrenome + ", senha=" + senha + ", senhaAntiga=" + senhaAntiga
				+ ", senhaNovaConfirmacao=" + senhaNovaConfirmacao + ", getId()=" + getId() + ", getNome()=" + getNome()
				+ ", getTipo()=" + getTipo() + ", getEmail()=" + getEmail() + "]";
	}



	
	
	


//	
//	@Override
//	public String getName() {
//		return nome;
//	}
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return null;
//	}
//
//	@Override
//	public Object getCredentials() {
//		return null;
//	}
//
//	@Override
//	public Object getDetails() {
//		return null;
//	}
//
//	@Override
//	public Object getPrincipal() {
//		return null;
//	}
//
//	@Override
//	public boolean isAuthenticated() {
//		return true;
//	}
//
//	@Override
//	public void setAuthenticated(boolean arg0) throws IllegalArgumentException {		
//	}

}
