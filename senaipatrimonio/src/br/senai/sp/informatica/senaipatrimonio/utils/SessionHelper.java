package br.senai.sp.informatica.senaipatrimonio.utils;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.senai.sp.informatica.senaipatrimonio.model.Usuario;

@Component
public class SessionHelper {
	@Autowired
	private HttpSession session;
	
	public Usuario getUsuarioLogado() {
		return (Usuario) session.getAttribute(Constantes.USUARIO_DA_SESSAO);
	}
	
	public void setUsuarioLogado(Usuario usuario) {
		session.setAttribute(Constantes.USUARIO_DA_SESSAO, usuario);
	}

}
