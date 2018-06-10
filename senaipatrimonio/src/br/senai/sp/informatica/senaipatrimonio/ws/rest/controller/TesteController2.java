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
import br.senai.sp.informatica.senaipatrimonio.utils.RequestUtils;
import io.felipepoliveira.jserializer.JSerializer;
import io.felipepoliveira.jserializer.json.JfoObject;
import io.felipepoliveira.jserializer.json.JsonArray;
import io.felipepoliveira.jserializer.json.JsonSerializationBuilder;

@RestController
@RequestMapping("/teste")
public class TesteController2 {

	@Autowired
	private RequestUtils requestUtils;

	static List<String> bancoDeDados = new LinkedList<>();
	static Long ultimoId = 3L;

	public TesteController2() {

		bancoDeDados.add("adsfa");
		bancoDeDados.add("asdf");
		bancoDeDados.add("kkkkkBATATA");
	}

	@Autowired
	UsuarioDAO dao;

	@GetMapping(value = { "/procura" })
	public ResponseEntity<Object> buscarTodos() {
		return ResponseEntity.ok(dao.buscarTodos());
	}

	@PostMapping(value = { "/input" })
	public ResponseEntity<Object> busTodos(@RequestBody Usuario usuario) {
		return ResponseEntity.ok(usuario);
	}

	@PostMapping(value = { "/input/lista" })
	public ResponseEntity<Object> busTodosA(@RequestBody List<Usuario> usuario) {

		// Recupera o jfo da requesição
		JfoObject jfo = requestUtils.getJFO();

		// Inicializa o jsonbuilder
		JsonSerializationBuilder builder = JSerializer.json();

		// Confere se veio jfo, caso esteja vazio ele ignora o filtro
		if (jfo != null)
			builder.withJfo(jfo);

		// Gera o json
		JsonArray json = builder.serializeArray(usuario.toArray());

		System.out.println("TOP: "+json.toString());
		
		return ResponseEntity.ok(json.toString());
	}
}
