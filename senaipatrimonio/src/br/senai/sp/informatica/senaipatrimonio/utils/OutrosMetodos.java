package br.senai.sp.informatica.senaipatrimonio.utils;

import org.springframework.util.DigestUtils;

public class OutrosMetodos {

	public static String hashString(String string) {
		return DigestUtils.md5DigestAsHex(string.getBytes());
	}
	
}
