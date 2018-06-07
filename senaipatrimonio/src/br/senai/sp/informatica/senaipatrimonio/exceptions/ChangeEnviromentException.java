package br.senai.sp.informatica.senaipatrimonio.exceptions;

import java.util.HashMap;
import java.util.Map;

public class ChangeEnviromentException extends Exception{

	public static Map<String, String> getErrorMensage() {
		Map<String, String> mapaErro = new HashMap<>();
		mapaErro.put("Erro", "Você não pode mover o item para o ambiente que ele se encontra");
		return mapaErro;
	}
	
}
