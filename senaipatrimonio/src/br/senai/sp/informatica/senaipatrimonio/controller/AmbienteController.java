package br.senai.sp.informatica.senaipatrimonio.controller;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.AmbienteDAO;
import br.senai.sp.informatica.senaipatrimonio.model.Ambiente;

@Controller
public class AmbienteController {

	// Variveis injetadas pelo Spring
	@Autowired
	private AmbienteDAO ambienteDAO;

	/**
	 * Abre a pagina de listagem de ambientes
	 *
	 * @param model
	 * @return String
	 */
	@GetMapping("/app/ambiente/lista")
	public String abrirLista(Model model) {

		model.addAttribute("ambiente", new Ambiente());
		model.addAttribute("ambientes", ambienteDAO.buscarTodos());
		return "ambiente/lista";
	}

	/**
	 * Salva um ambiente no banco de dados
	 * 
	 * @param ambiente
	 * @param brAmbiente
	 * @param model
	 * @return String
	 */
	@PostMapping("app/adm/ambiente/salvar")
	public String salvar(@Valid Ambiente ambiente, BindingResult brAmbiente, Model model) {

		// confere os erros das regras de negócio
		if (brAmbiente.hasErrors()) {
			model.addAttribute("ambientes", ambienteDAO.buscarTodos());
			return "ambiente/lista";
		}

		// confere se já esta cadastrado
		if (ambienteDAO.jaEstaCadastrado(ambiente)) {
			model.addAttribute("ambientes", ambienteDAO.buscarTodos());
			brAmbiente.addError(new FieldError("ambiente", "nome", "Esse ambiente já está cadastrado"));
			return "ambiente/lista";
		}

		// Persiste o obj
		ambienteDAO.persistir(ambiente);
		return "redirect:/app/ambiente/lista";
	}

	/**
	 * Abre a pagina de alteração do objeto
	 * 
	 * @param id
	 * @param model
	 * @return String
	 */
	@GetMapping("app/adm/ambiente/alterar")
	public String abrirAlterar(@RequestParam(required = true) Long id, Model model) {
		Ambiente ambiente = ambienteDAO.buscarPeloId(id);
		model.addAttribute("ambiente", ambiente);
		model.addAttribute("nomeOriginal", "Nome original: " + ambiente.getNome());
		return "ambiente/alterar_form";
	}

	/**
	 * Faz a alteração do objeto ambiente
	 * 
	 * @param ambiente
	 * @param result
	 * @param model
	 * 
	 * @return String
	 */
	@PostMapping("app/adm/ambiente/alterar")
	public String alterar(@Valid Ambiente ambiente, BindingResult result, Model model) {

		//Recupera o ambiente do banco
		Ambiente ambienteOriginal = ambienteDAO.buscarPeloId(ambiente.getId());

		//Valida as regras de negócio
		if (result.hasErrors()) {
			model.addAttribute("categoria", ambiente);
			model.addAttribute("nomeOriginal", "Nome original: " + ambienteOriginal.getNome());
			return "ambiente/alterar_form";
		}

		//Valida se já está cadastrado
		if (ambienteDAO.jaEstaCadastrado(ambiente)) {
			result.addError(new FieldError("ambiente", "nome", "Esse ambiente já está cadastrado"));
			model.addAttribute("nomeOriginal", "Nome original: " + ambienteOriginal.getNome());
			return "ambiente/alterar_form";
		}

		Ambiente ambienteAtualizar = ambienteDAO.buscarPeloId(ambiente.getId());
		BeanUtils.copyProperties(ambiente, ambienteAtualizar, "id");

		ambienteDAO.alterar(ambienteAtualizar);
		return "redirect:/app/ambiente/lista";

	}

	/**
	 * Excluir um ambiente
	 * 
	 * @param id
	 * @param redirectAttributes
	 * 
	 * @return String
	 */
	@GetMapping("app/adm/ambiente/excluir")
	public String excluirUsuario(@RequestParam(required = true) Long id, RedirectAttributes redirectAttributes) {
		//Tenta fazer exclusão, caso não consiga, envia uma mensagem para o usuario
		try {
			ambienteDAO.deletar(new Ambiente(id));
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("outrosErros",
					"Você não pode excluir esse ambiente, pois um ou mais items-patrimonios ou Movimentações estão utilizando-o");
		}
		return "redirect:/app/ambiente/lista";
	}
}
