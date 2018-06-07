package br.senai.sp.informatica.senaipatrimonio.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.DigestUtils;

@Entity
public class Usuario implements Authentication{
	
	public Usuario(Long id) {
		super();
		this.id = id;
	}

	public Usuario() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usu")
	private Long id;

	@Column(name = "nome", length = 20, nullable = false, unique = false)
	@NotNull
	@Size(min = 1, max = 20)
	private String nome;


	@Column(name = "sobrenome", length = 40, nullable = false, unique = false)
	@NotNull
	@Size(min = 1, max = 40)
	private String sobrenome;

	@Column(name = "email", length = 120, nullable = false, unique = true)
	@NotNull
	@Size(min = 1,max=120)
	private String email;

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
	
	@Column(name = "tipo", nullable = false, unique = false)
	@NotNull
	private TipoUsuario tipo;

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

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	

	public String getSenhaAntiga() {
		return senhaAntiga;
	}

	public void setSenhaAntiga(String senhaAntiga) {
		this.senhaAntiga = senhaAntiga;
	}

	public String getSenhaNovaConfirmacao() {
		return senhaNovaConfirmacao;
	}

	public void setSenhaNovaConfirmacao(String senhaNovaConfirmacao) {
		this.senhaNovaConfirmacao = senhaNovaConfirmacao;
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}
	
	public void hashearSenha() {
		this.senha = DigestUtils.md5DigestAsHex(this.senha.getBytes());
	}

	public void trimEmail() {
		this.email.trim();
	}
	
	public Boolean getAdmConfirm() {
		if(this.tipo.equals(TipoUsuario.ADMIN) ) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", sobrenome=" + sobrenome + ", email=" + email + ", senha="
				+ senha + ", tipo=" + tipo + "]";
	}


	
	@Override
	public String getName() {
		return nome;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getDetails() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return null;
	}

	@Override
	public boolean isAuthenticated() {
		return true;
	}

	@Override
	public void setAuthenticated(boolean arg0) throws IllegalArgumentException {		
	}

}
