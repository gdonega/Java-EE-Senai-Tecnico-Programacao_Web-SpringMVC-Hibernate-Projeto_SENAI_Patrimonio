package br.senai.sp.informatica.senaipatrimonio.ws.rest.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class TesteController2 {

	
	static List<String> bancoDeDados = new LinkedList<>();
	static Long ultimoId = 3L;

	public TesteController2() {


		bancoDeDados.add("adsfa");
		bancoDeDados.add("asdf");
		bancoDeDados.add("kkkkkBATATA");
	}
	
	@GetMapping(value = {"/a","/b"})
	public ResponseEntity<Object> buscarTodos() {
		return ResponseEntity.ok("asd");
	}
	
}
