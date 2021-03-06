package br.senai.sp.informatica.senaipatrimonio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.senai.sp.informatica.senaipatrimonio.model.Usuario;
import br.senai.sp.informatica.senaipatrimonio.utils.SessionHelper;

@Controller
public class IndexController {

	//Objetos injetados pelo Spring
	@Autowired
	private SessionHelper session;
	
	/**
	 * Abrir pagina de login
	 * 
	 * @param model
	 * @return String
	 */
	@GetMapping({ "/", "" })
	public String abrirLogin(Model model) {
		if (session.getUsuarioLogado() != null)
			return "redirect:/app/home";
		
		model.addAttribute("usuario", new Usuario());

		return "usuario/login";
	}

	/**
	 * Abrir pagina home do usuario
	 * 
	 * @return
	 */
	@GetMapping("/app/home")
	public String abrirHome() {
		return "home";
	}
	
}
