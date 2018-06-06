package br.senai.sp.informatica.senaipatrimonio.utils;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import br.senai.sp.informatica.senaipatrimonio.model.Usuario;

public class JwtUtils {

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
				
				
				.sign(Algorithm.HMAC512("17A449272F9A4818CC6C8C6CA091064AC2E21C948CD1A1E226328403761A3E75"))
				;
		
	}
	
}
