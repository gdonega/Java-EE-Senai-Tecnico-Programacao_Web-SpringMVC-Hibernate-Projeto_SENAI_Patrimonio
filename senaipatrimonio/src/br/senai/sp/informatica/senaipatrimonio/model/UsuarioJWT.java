package br.senai.sp.informatica.senaipatrimonio.model;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class UsuarioJWT extends UsuarioBase implements Authentication{

	@Override
	public String getName() {
		return this.getNome();
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
		return this;
	}

	@Override
	public boolean isAuthenticated() {
		return true;
	}

	@Override
	public void setAuthenticated(boolean arg0) throws IllegalArgumentException {		
	}

	@Override
	public String toString() {
		return "UsuarioJWT [id=" + id + ", getId()=" + getId() + ", getNome()=" + getNome() + ", getTipo()=" + getTipo()
				+ ", getEmail()=" + getEmail() + "]";
	}

	
	
	

}
