package br.senai.sp.informatica.senaipatrimonio.ws.rest.jfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.informatica.senaipatrimonio.exceptions.EntityNotFoundException;
import br.senai.sp.informatica.senaipatrimonio.model.ItemPatrimonio;
import br.senai.sp.informatica.senaipatrimonio.model.Movimentacao;
import br.senai.sp.informatica.senaipatrimonio.services.ItemPatrimonioServices;
import br.senai.sp.informatica.senaipatrimonio.utils.RequestUtils;
import io.felipepoliveira.jserializer.JSerializer;
import io.felipepoliveira.jserializer.json.JfoObject;
import io.felipepoliveira.jserializer.json.JsonArray;
import io.felipepoliveira.jserializer.json.JsonObject;
import io.felipepoliveira.jserializer.json.JsonSerializationBuilder;

@RestController
@RequestMapping("/rest/jfo/itens")
public class ItemPatrimonioRestJfoController {

	@Autowired
	private ItemPatrimonioServices itemPatrimonioServices;
	
	@Autowired
	private RequestUtils requestUtils;
	
	@GetMapping
	public ResponseEntity<Object> buscarTodos(){
		try {
			List<ItemPatrimonio> itens = itemPatrimonioServices.buscarTodos();
			
			// Recupera o jfo da requesição
			JfoObject jfo = requestUtils.getJFO();

			// Inicializa o jsonbuilder
			JsonSerializationBuilder builder = JSerializer.json();

			// Confere se veio jfo, caso esteja vazio ele ignora o filtro
			if (jfo != null)
				builder.withJfo(jfo);
			
			//Gera o json
			JsonArray json = builder.serializeArray(itens.toArray());
			
			return ResponseEntity.ok(json.toString());
			
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.build();
		}
		
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> buscarItem(@PathVariable Long id){
		try {
			ItemPatrimonio item = itemPatrimonioServices.buscarPorId(id);
			
			
			// Recupera o jfo da requesição
			JfoObject jfo = requestUtils.getJFO();

			// Inicializa o jsonbuilder
			JsonSerializationBuilder builder = JSerializer.json();

			// Confere se veio jfo, caso esteja vazio ele ignora o filtro
			if (jfo != null)
				builder.withJfo(jfo);
			
			//Gera o json
			JsonObject json = builder.serialize(item);
			
			
			return ResponseEntity.ok(json.toString());
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
	
	
	@GetMapping("/{id}/movimentacoes")
	public ResponseEntity<Object> buscarMovimentacoes(@PathVariable Long id){
		try {
			
			List<Movimentacao> movs = itemPatrimonioServices.buscarMovimentacoes(id);
			
			// Recupera o jfo da requesição
			JfoObject jfo = requestUtils.getJFO();

			// Inicializa o jsonbuilder
			JsonSerializationBuilder builder = JSerializer.json();

			// Confere se veio jfo, caso esteja vazio ele ignora o filtro
			if (jfo != null)
				builder.withJfo(jfo);
			
			//Gera o json
			JsonArray json = builder.serializeArray(movs.toArray());
			
					
			return ResponseEntity
					.ok(json.toString());
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
