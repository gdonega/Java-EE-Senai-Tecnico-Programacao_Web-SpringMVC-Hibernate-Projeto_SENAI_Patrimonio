package br.senai.sp.informatica.senaipatrimonio.ws.rest.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.informatica.senaipatrimonio.exceptions.ChangeEnviromentException;
import br.senai.sp.informatica.senaipatrimonio.exceptions.EntityNotFoundException;
import br.senai.sp.informatica.senaipatrimonio.exceptions.ValidationException;
import br.senai.sp.informatica.senaipatrimonio.model.Ambiente;
import br.senai.sp.informatica.senaipatrimonio.model.Movimentacao;
import br.senai.sp.informatica.senaipatrimonio.services.ItemPatrimonioServices;
import br.senai.sp.informatica.senaipatrimonio.utils.MapUtils;

@RestController
@RequestMapping("/rest/itens")
public class ItemPatrimonioRestController {

	@Autowired
	private ItemPatrimonioServices itemPatrimonioServices;
	
	@GetMapping
	public ResponseEntity<Object> buscarTodos(){
		return ResponseEntity.ok(itemPatrimonioServices.buscarTodos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> buscarItem(@PathVariable Long id){
		try {
			return ResponseEntity.ok(itemPatrimonioServices.buscarPorId(id));
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
			return ResponseEntity
					.ok(itemPatrimonioServices.buscarMovimentacoes(id));
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
	
	@PatchMapping("/{idItem}/movimentacoes")
	public ResponseEntity<Object> moverItem(@PathVariable Long idItem, @RequestBody Movimentacao movimentacao){
		try {
			System.out.println(movimentacao);
			
			return ResponseEntity.ok(itemPatrimonioServices.moverItem(idItem, movimentacao));
		} catch (EntityNotFoundException e) {
			return ResponseEntity
					.notFound()
					.header("X-Reason", "Entidade não encontrada")
					.build();
		} catch (ChangeEnviromentException e) {
			Map<String, String> mapa = new HashMap<>();
			return ResponseEntity
					.unprocessableEntity() 
					.body(ChangeEnviromentException.getErrorMensage());
			
		} catch (ValidationException e) {
			return ResponseEntity
					.unprocessableEntity()
					.build();
		} catch(Exception e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.build();
		}
	}
	
	
	
}
