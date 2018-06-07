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
	 * Analiza a requisição e permite que o usuario devidamente autenticado consiga
	 * fazer qualquer ação requirida
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		// Pega a requesição
		HttpServletRequest request = (HttpServletRequest) req;

		String authorization = request.getHeader("Authorization");

		// Caso tenha enviado o token
		if (authorization != null) {

			// Caso o token esteja no padrão correto
			if (authorization.matches("(Bearer )(\\w|\\.|\\-)+")) {

				// Pega o token em si (retira o "Bearer "
				String token = authorization.split(" ")[1];

				try {
					// 1º valida o token, e caso haja alguma falcificação, lança uma excessão
					JwtUtils.validarToken(token);

					// 2º Extrair o Usuario do token
					UsuarioJWT usuarioToken = (UsuarioJWT) JwtUtils.extrairUsuario(token);

					// 3º Determina que o usuairo do token está autenticado
					SecurityContextHolder.getContext().setAuthentication(usuarioToken);
				} catch (Exception e) {
					System.out.println("Erro: erro de validação na Classe JwtFilter");
				}
				
			} else {
				System.out.println("Erro: token inválido na Classe JwtFilter");
			}
			
		} else {
			System.out.println("Erro: Authorization não informado na Classe JwtFilter");
		}

		
		chain.doFilter(req, res);
	}

}
