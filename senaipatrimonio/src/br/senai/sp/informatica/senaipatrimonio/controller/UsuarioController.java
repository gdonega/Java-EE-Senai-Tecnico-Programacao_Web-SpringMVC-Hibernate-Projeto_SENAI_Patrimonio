package br.senai.sp.informatica.senaipatrimonio.controller;

import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.UsuarioDAO;
import br.senai.sp.informatica.senaipatrimonio.model.TipoUsuario;
import br.senai.sp.informatica.senaipatrimonio.model.Usuario;
import br.senai.sp.informatica.senaipatrimonio.utils.OutrosMetodos;
import br.senai.sp.informatica.senaipatrimonio.utils.SessionHelper;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Autowired
	private SessionHelper session;

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
		brUsuario.addError(new FieldError("usuario", "senha", "Email ou/e senha inválidos!"));
		return "usuario/login";
	}

	@GetMapping({ "/app/usuario/logout" })
	public String executarLogout() {
		session.terminarSessao();
		return "redirect:/";
	}

	@GetMapping({ "/app/usuario/info" })
	public String usuarioInfo(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "usuario/ver_usuario";
	}

	@PostMapping("app/usuario/senha/alterar")
	public String usuarioAlterarSenha(@Valid Usuario usuario, BindingResult brUsuario) {

		if (brUsuario.hasFieldErrors("senha") || brUsuario.hasFieldErrors("senhaAntiga")
				|| brUsuario.hasFieldErrors("senhaNovaConfirmacao"))
			return "usuario/ver_usuario";

		Usuario usuarioAuten = usuarioDAO.buscarPorEmailESenha(session.getUsuarioLogado().getEmail(),
				OutrosMetodos.hashString(usuario.getSenhaAntiga()));

		if (usuarioAuten == null) {
			brUsuario.addError(new FieldError("usuario", "senhaAntiga", "Senha atual inválida!"));
			return "usuario/ver_usuario";
		}

		if (!usuario.getSenha().equals(usuario.getSenhaNovaConfirmacao())) {
			brUsuario.addError(new FieldError("usuario", "senhaNovaConfirmacao", "As senhas não são iguais!"));
			return "usuario/ver_usuario";
		}

		usuarioAuten.setSenha(usuario.getSenha());

		usuarioAuten.hashearSenha();

		usuarioDAO.alterar(usuarioAuten);

		session.setUsuarioLogado(usuarioDAO.buscarPeloId(usuarioAuten.getId()));

		return "redirect:/app/home";
	}

	@GetMapping("app/adm/usuario/lista")
	public String abrirLista(Model model) {
		model.addAttribute("usuarios", usuarioDAO.buscarTodos());
		return "usuario/lista";
	}

	@GetMapping("app/adm/usuario/excluir")
	public String excluirUsuario(@RequestParam(required = true) Long id) {
		usuarioDAO.deletar(new Usuario(id));
		return "redirect:/app/adm/usuario/lista";
	}

	@GetMapping("app/adm/usuario/form")
	public String abrirFormUsuario(@RequestParam(required = false) Long id, Model model) {

		Usuario usuario;
		if (id != null) {
			usuario = usuarioDAO.buscarPeloId(id);
		} else {
			usuario = new Usuario();
		}
		model.addAttribute("usuario", usuario);
		model.addAttribute("tipos", new ArrayList<TipoUsuario>(Arrays.asList(TipoUsuario.values())));
		return "usuario/form";
	}

	@PostMapping("app/adm/usuario/salvar")
	public String salvarUsuario(@Valid Usuario usuario, BindingResult result, Model model) {

		usuario.trimEmail();
		System.out.println(usuario);

		if (result.hasFieldErrors("nome") || result.hasFieldErrors("sobrenome") || result.hasFieldErrors("tipo")
				|| result.hasFieldErrors("email")) {
			model.addAttribute("tipos", new ArrayList<TipoUsuario>(Arrays.asList(TipoUsuario.values())));
			model.addAttribute(usuario);
			return "usuario/form";
		}
		
		
		Usuario usuarioProcurado = usuarioDAO.buscarPorEmail(usuario.getEmail());
		if(usuarioProcurado!=null) {
			if(usuarioProcurado.getId() != usuario.getId()) {
				result.addError(new FieldError("usuario", "email", "Email inválido!"));
				model.addAttribute("tipos", new ArrayList<TipoUsuario>(Arrays.asList(TipoUsuario.values())));
				return "usuario/form";
			}
		}

		if (usuario.getId() == null) {
			String senha = RandomStringUtils.randomAlphanumeric(18);

			usuario.setSenha(senha);

			System.out.println(senha);

			/*
			 * 
			 * Email com senha
			 * 
			 */

			usuario.hashearSenha();

			usuarioDAO.persistir(usuario);
		}else {
			
			Usuario usuarioAtualizar = usuarioDAO.buscarPeloId(usuario.getId());
			
			BeanUtils.copyProperties(usuario, usuarioAtualizar,"id","senha");
			
			usuarioDAO.alterar(usuarioAtualizar);
		}

		return "redirect:/app/adm/usuario/lista";
	}

}
