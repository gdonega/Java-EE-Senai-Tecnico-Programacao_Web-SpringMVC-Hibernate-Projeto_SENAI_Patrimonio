package br.senai.sp.informatica.senaipatrimonio.ws.rest.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.informatica.senaipatrimonio.exceptions.EntityNotFoundException;
import br.senai.sp.informatica.senaipatrimonio.exceptions.ValidationException;
import br.senai.sp.informatica.senaipatrimonio.model.Usuario;
import br.senai.sp.informatica.senaipatrimonio.services.UsuarioServices;
import br.senai.sp.informatica.senaipatrimonio.utils.JwtUtils;
import br.senai.sp.informatica.senaipatrimonio.utils.MapUtils;

@RestController
@RequestMapping("/rest/auth")
public class AuthRestController {

	@Autowired
	private UsuarioServices usuarioServices;
	
	@PostMapping(value = {"/jwt", "/jwt/"})
	public ResponseEntity<Object> gerarJwt(@Valid @RequestBody Usuario usuario, BindingResult brUsuario){
		
		System.out.println(usuario);
		try {
			Usuario usuarioBuscado = usuarioServices.buscarPorEmailESenha(usuario, brUsuario);
			Map<String, String> mapaToken = new HashMap<>();
			mapaToken.put("token", JwtUtils.gerarToken(usuarioBuscado));
			
			return ResponseEntity
					.ok(mapaToken);
		} catch (ValidationException e) {
			
			
			return ResponseEntity
					.unprocessableEntity()
					.body(MapUtils.MapResult(brUsuario, new String[] {"senha","email"}));
			
		} catch (EntityNotFoundException e) {
			
			return ResponseEntity
				.notFound()
				.header("X-Reason", "Entidade não encontrada")
				.build();
				
			
		} catch (Exception e) {
			
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.build();
			
		}
		
	}
}
