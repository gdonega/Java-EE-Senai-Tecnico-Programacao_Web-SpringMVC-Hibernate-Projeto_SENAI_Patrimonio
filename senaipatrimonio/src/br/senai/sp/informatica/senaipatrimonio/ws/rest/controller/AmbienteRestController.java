package br.senai.sp.informatica.senaipatrimonio.ws.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.informatica.senaipatrimonio.services.AmbienteServices;

@RestController
@RequestMapping("/rest/ambientes")
public class AmbienteRestController {

	@Autowired
	private AmbienteServices ambienteServices;
	
	@GetMapping
	public ResponseEntity<Object> listarTodos(){
		return ResponseEntity.ok(ambienteServices.buscarTodos());
	}
}
