package br.senai.sp.informatica.senaipatrimonio.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import br.senai.sp.informatica.senaipatrimonio.model.UsuarioJWT;
import br.senai.sp.informatica.senaipatrimonio.utils.JwtUtils;

@Component
public class JwtFilter extends GenericFilterBean {

	/**
	 * Analiza a requisi��o e permite que o usuario devidamente autenticado consiga
	 * fazer qualquer a��o requirida
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		// Pega a requesi��o
		HttpServletRequest request = (HttpServletRequest) req;

		String authorization = request.getHeader("Authorization");

		// Caso tenha enviado o token
		if (authorization != null) {

			// Caso o token esteja no padr�o correto
			if (authorization.matches("(Bearer )(\\w|\\.|\\-)+")) {

				// Pega o token em si (retira o "Bearer "
				String token = authorization.split(" ")[1];

				try {
					// 1� valida o token, e caso haja alguma falcifica��o, lan�a uma excess�o
					JwtUtils.validarToken(token);

					// 2� Extrair o Usuario do token
					UsuarioJWT usuarioToken = (UsuarioJWT) JwtUtils.extrairUsuario(token);

					// 3� Determina que o usuairo do token est� autenticado
					SecurityContextHolder.getContext().setAuthentication(usuarioToken);
				} catch (Exception e) {
					System.out.println("Erro: erro de valida��o na Classe JwtFilter");
				}
				
			} else {
				System.out.println("Erro: token inv�lido na Classe JwtFilter");
			}
			
		} else {
			System.out.println("Erro: Authorization n�o informado na Classe JwtFilter");
		}

		
		chain.doFilter(req, res);
	}

}
