package br.senai.sp.informatica.senaipatrimonio.ws.rest.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.UsuarioDAO;
import br.senai.sp.informatica.senaipatrimonio.model.Usuario;

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
	
	@Autowired
	UsuarioDAO dao;
	
	@GetMapping(value = {"/procura"})
	public ResponseEntity<Object> buscarTodos() {
		return ResponseEntity.ok(dao.buscarTodos());
	}
	
	@PostMapping(value = {"/input"})
	public ResponseEntity<Object> busTodos(@RequestBody Usuario usuario) {		
		return ResponseEntity.ok(usuario);
	}
	
	@PostMapping(value = {"/input/lista"})
	public ResponseEntity<Object> busTodosA(@RequestBody List<Usuario> usuario) {		
		
		System.out.println(usuario);
		
		return ResponseEntity.ok(usuario);
	}
}
