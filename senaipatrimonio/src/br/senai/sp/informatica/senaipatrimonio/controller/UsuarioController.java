package br.senai.sp.informatica.senaipatrimonio.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.UsuarioDAO;
import br.senai.sp.informatica.senaipatrimonio.model.Usuario;
import br.senai.sp.informatica.senaipatrimonio.utils.SessionHelper;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Autowired
	private SessionHelper session;

	@GetMapping({ "/", "" })
	public String abrirLogin(Model model) {
		if (session.getUsuarioLogado() != null)
			return "redirect:/app/home";
		
		model.addAttribute("usuario", new Usuario());

		return "usuario/login";
	}

	@PostMapping({ "/usuario/logar" })
	public String executarLogin(@Valid Usuario usuario, BindingResult brUsuario, Model model) {
		
		if (brUsuario.hasFieldErrors("senha") || brUsuario.hasFieldErrors("senha")) {
			System.out.println("CAPTUROU OS ERROS");
			return "usuario/login";
		}
		
		usuario.hashearSenha();
		Usuario usuarioAuten = usuarioDAO.buscarPorEmailESenha(usuario.getEmail(), usuario.getSenha());
		if (usuarioAuten != null) {
			session.setUsuarioLogado(usuarioAuten);
			return "redirect:/app/home";
		}

		model.addAttribute("usuario", usuario);
		brUsuario.addError(new FieldError("usuario","email", "Email ou/e senha inválidos!"));
		return "usuario/login";
	}

	@GetMapping("/app/home")
	public String abrirHome() {
		return "home";
	}
	
	

}
