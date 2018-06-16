package br.senai.sp.informatica.senaipatrimonio.utils;

import javax.servlet.http.HttpServletRequest;

import org.adataq.jserializer.JSerializer;
import org.adataq.jserializer.json.JfoObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RequestUtils {

	@Autowired
	private HttpServletRequest req;

	public JfoObject getJFO() {

		String filter = req.getHeader("X-Filter");

		if (filter != null) {

			if (filter.matches("(JFO ).+")) {
				
				String jfo = filter.substring(4).trim();
				
				try {
					return JSerializer.json().parseJfo(jfo);
				} catch (Exception e) {
					System.out.println("Ocorreu um erro ao converter JFO");
				}
				
			} else {
				System.out.println("Não está no formato JFO");
			}

		} else {
			System.out.println("JFO não enviado");
		}

		return null;
	}

}
