package br.senai.sp.informatica.senaipatrimonio.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.senai.sp.informatica.senaipatrimonio.model.Usuario;
import br.senai.sp.informatica.senaipatrimonio.utils.Constantes;

@Component
public class AutenticacaoInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
	
		Usuario usuarioLogado = (Usuario) request.getSession().getAttribute(Constantes.USUARIO_DA_SESSAO);
		String uri = request.getRequestURI();
		
		Boolean necessitaAutenticacao = uri.contains("/app");
		Boolean necessitaSerAdm = uri.contains("/adm");
		
		Boolean usuarioNaoEstaLogado = usuarioLogado == null;
		
		
		if(necessitaAutenticacao && usuarioNaoEstaLogado) {
			response.sendRedirect(request.getContextPath() + "/");
			return false;
		}
		if(necessitaSerAdm && !usuarioLogado.isAdm()) {
			response.sendRedirect(request.getContextPath() + "/");
			return false;
		}
		
		return true;	
		
		
		
	}

}
