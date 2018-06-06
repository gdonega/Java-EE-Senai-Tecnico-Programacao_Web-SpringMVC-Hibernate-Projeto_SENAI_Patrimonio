package br.senai.sp.informatica.senaipatrimonio.ws.rest.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest/teste")
public class TesteController {

	
	static List<String> bancoDeDados = new LinkedList<>();
	static Long ultimoId = 3L;

	public TesteController() {


		bancoDeDados.add("adsfa");
		bancoDeDados.add("asdf");
		bancoDeDados.add("kkkkkBATATA");
	}
	
	@GetMapping
	public ResponseEntity<Object> buscarTodos() {
		return ResponseEntity.ok("asd");
	}
	
}
