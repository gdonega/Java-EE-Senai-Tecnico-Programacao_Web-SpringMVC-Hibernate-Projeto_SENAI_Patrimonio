package br.senai.sp.informatica.senaipatrimonio.utils;

import org.springframework.util.DigestUtils;

public class OutrosMetodos {

	public static String hashString(String string) {
		return DigestUtils.md5DigestAsHex(string.getBytes());
	}
	
	
	public static String gerarCorpoDoEmail(String nome, String senha) {
		return "Olá, " + nome + "! Bem-vindo ao Sistema SENAI Patrimonio.\n\n\n\n"
				+ "A sua senha inicial é: "+senha
				+"\n\n\n\n"
				+ "Ela poderá ser trocada quando você quizer" ;
	}
}
