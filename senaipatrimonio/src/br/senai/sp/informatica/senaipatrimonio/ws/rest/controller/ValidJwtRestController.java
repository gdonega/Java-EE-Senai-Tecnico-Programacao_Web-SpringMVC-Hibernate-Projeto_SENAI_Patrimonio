package br.senai.sp.informatica.senaipatrimonio.ws.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/jwt")
public class ValidJwtRestController {
	
	@GetMapping(value = {"/valid", "/valid/"})
	public ResponseEntity<Object> validJwt(){
		try {
			return ResponseEntity
					.noContent()
					.build();
		}catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.build();
			
		}
	}
}
