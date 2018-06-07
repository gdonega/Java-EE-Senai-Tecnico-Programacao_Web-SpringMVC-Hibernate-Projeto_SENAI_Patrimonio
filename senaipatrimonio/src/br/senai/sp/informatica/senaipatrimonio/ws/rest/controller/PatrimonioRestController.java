package br.senai.sp.informatica.senaipatrimonio.ws.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.informatica.senaipatrimonio.exceptions.EntityNotFoundException;
import br.senai.sp.informatica.senaipatrimonio.services.PatrimonioService;

@RestController
@RequestMapping("/rest/patrimonios")
public class PatrimonioRestController {

	@Autowired
	private PatrimonioService patrimonioServices;
	
	@GetMapping
	public ResponseEntity<Object> buscarTodos(){
		return ResponseEntity
				.ok(patrimonioServices.buscarTodos());
	}
	
	@GetMapping("/{id}/itens")
	public ResponseEntity<Object> buscarTodosItems(@PathVariable Long id){
		try {
			return ResponseEntity.ok(patrimonioServices.buscarItens(id));
		} catch (EntityNotFoundException e) {
			return ResponseEntity
					.notFound()
					.header("X-Reason", "Entidade não encontrada")
					.build();
		}catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
