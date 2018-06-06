package br.senai.sp.informatica.senaipatrimonio.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class MapUtils {

	/**
	 * Converte o BindingResult em Map
	 * @param result
	 * @return Map<String, String>
	 */
	public static Map<String, String> MapResult(BindingResult result){
		//Criar um mapa par armazenar os erros do binding result
		Map<String, String> mapaErros = new HashMap<>();
		
		//Pegar cada erro do binding result e aplica-lo no mapa
		for (FieldError erro : result.getFieldErrors()) {
			
			mapaErros.put(erro.getField(), erro.getDefaultMessage());
			
		}
		
		
		return mapaErros;
	}
	
	
	/**
	 * Converte o BindingResult em Map
	 * @param result
	 * @return Map<String, String>
	 */
	public static Map<String, String> MapResult(BindingResult result, String[] requiredFields){
		//Criar um mapa par armazenar os erros do binding result
		Map<String, String> mapaErros = new HashMap<>();
		
		//Pegar cada erro do binding result e aplica-lo no mapa
		for (FieldError erro : result.getFieldErrors()) {
			for (String item : requiredFields) {
				if(erro.getField().equals(item)) {
					
					mapaErros.put(erro.getField(), erro.getDefaultMessage());
					
				}
			}
		}
		
		
		return mapaErros;
	}
	
	
	
}
