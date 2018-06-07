package br.senai.sp.informatica.senaipatrimonio.utils;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.senai.sp.informatica.senaipatrimonio.model.TipoUsuario;
import br.senai.sp.informatica.senaipatrimonio.model.Usuario;

public class JwtUtils {

	private static String secret = "17A449272F9A4818CC6C8C6CA091064AC2E21C948CD1A1E226328403761A3E75";
	
	/**
	 * Cria um token válido
	 * 
	 * @param usuario
	 * @return String (token)
	 * @throws IllegalArgumentException
	 * @throws JWTCreationException
	 * @throws UnsupportedEncodingException
	 */
	public static String gerarToken (Usuario usuario) throws IllegalArgumentException, JWTCreationException, UnsupportedEncodingException {
		
		//Calcula o tempo de expiração 
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR, 8);
		
		Date dataDeExpiracao = calendar.getTime();
		
		return JWT.create()
				.withIssuer("SENAI - Controle de Patrimonio")
				.withIssuedAt(new Date())
				.withSubject("Authentication")
				.withExpiresAt(dataDeExpiracao)
				
				.withClaim("id", usuario.getId())
				.withClaim("tipo", usuario.getTipo().toString())
				.withClaim("nome", usuario.getNome())
				.withClaim("email", usuario.getEmail())
				
				
				.sign(Algorithm.HMAC512(secret))
				;
	}
	
	/**
	 * Valida um token, caso não seja valido lançará uma excessão
	 * @param token
	 * @throws JWTVerificationException
	 * @throws IllegalArgumentException
	 * @throws UnsupportedEncodingException
	 */
	public static void validarToken(String token) throws JWTVerificationException, IllegalArgumentException, UnsupportedEncodingException {
		JWT
			.require(Algorithm.HMAC512(secret))
			.build()
			.verify(token);
	}
	
	/**
	 * Retorna o Usuario contido no token
	 * @param token
	 * @return Usuario
	 */
	public static Usuario extrairUsuario(String token) {
	
		Usuario usuario = new Usuario();
		
		DecodedJWT jwtDecodificado = JWT.decode(token);
		usuario.setId(jwtDecodificado.getClaim("id").asLong());
		usuario.setTipo(TipoUsuario.valueOf(jwtDecodificado.getClaim("tipo").asString()));
		usuario.setNome(jwtDecodificado.getClaim("nome").asString());
		usuario.setEmail(jwtDecodificado.getClaim("email").asString());
		
		return usuario;
		
	}
	
}

















