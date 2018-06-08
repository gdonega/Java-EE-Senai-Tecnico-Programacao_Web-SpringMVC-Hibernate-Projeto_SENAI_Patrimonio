package br.senai.sp.informatica.senaipatrimonio.ws.rest.jfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.informatica.senaipatrimonio.model.Ambiente;
import br.senai.sp.informatica.senaipatrimonio.services.AmbienteServices;
import br.senai.sp.informatica.senaipatrimonio.utils.RequestUtils;
import io.felipepoliveira.jserializer.JSerializer;
import io.felipepoliveira.jserializer.json.JfoObject;
import io.felipepoliveira.jserializer.json.JsonArray;
import io.felipepoliveira.jserializer.json.JsonSerializationBuilder;

@RestController
@RequestMapping("/rest/jfo/ambientes")
public class AmbienteRestJfoController {

	@Autowired
	private AmbienteServices ambienteServices;

	@Autowired
	private RequestUtils requestUtils;

	@GetMapping
	public ResponseEntity<Object> listarTodos() {
		try {

			// Pega a lista de ambientes através do service
			List<Ambiente> ambientes = ambienteServices.buscarTodos();
			
			// Recupera o jfo da requesição
			JfoObject jfo = requestUtils.getJFO();

			// Inicializa o jsonbuilder
			JsonSerializationBuilder builder = JSerializer.json();

			// Confere se veio jfo, caso esteja vazio ele ignora o filtro
			if (jfo != null)
				builder.withJfo(jfo);
			
			//Gera o json
			JsonArray json = builder.serializeArray(ambientes.toArray());

			//Resposta
			return ResponseEntity
					.ok(json.toString());
			
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.build();
		}
	}

}
