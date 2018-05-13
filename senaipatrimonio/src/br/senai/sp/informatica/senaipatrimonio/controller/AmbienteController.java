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

import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.AmbienteDAO;
import br.senai.sp.informatica.senaipatrimonio.model.Ambiente;
import br.senai.sp.informatica.senaipatrimonio.model.Usuario;

@Controller
public class AmbienteController {
	@Autowired	
	private AmbienteDAO ambienteDAO;
	
	@GetMapping("/app/ambiente/lista")
	public String abrirLista(Model model) {

		model.addAttribute("ambiente", new Ambiente());
		model.addAttribute("ambientes", ambienteDAO.buscarTodos());
		return "ambiente/lista";
	}
	
	
	@PostMapping("app/adm/ambiente/salvar")
	public String salvar(@Valid Ambiente ambiente,BindingResult brAmbiente ,Model model) {
		
		if(brAmbiente.hasErrors()) {
			model.addAttribute("ambientes", ambienteDAO.buscarTodos());
			return "ambiente/lista";
		}
		
		if(ambienteDAO.jaEstaCadastrado(ambiente)) {
			model.addAttribute("ambientes", ambienteDAO.buscarTodos());
			brAmbiente.addError(new FieldError("ambiente", "nome", "Esse ambiente já está cadastrado"));
			return "ambiente/lista";
		}
		
		
		ambienteDAO.persistir(ambiente);
		return "redirect:/app/ambiente/lista";
	}
	
	@GetMapping("app/adm/ambiente/alterar")
	public String abrirAlterar(@RequestParam(required = true) Long id ,Model model) {
		model.addAttribute("ambiente",ambienteDAO.buscarPeloId(id));
		return "ambiente/alterar_form";
	}

	@PostMapping("app/adm/ambiente/alterar")
	public String alterar(@Valid Ambiente ambiente,BindingResult result ,Model model) {
		
		if(result.hasErrors()) {
			return "ambiente/alterar_form";
		}
		
		if(ambienteDAO.jaEstaCadastrado(ambiente)) {
			result.addError(new FieldError("ambiente", "nome", "Esse ambiente já está cadastrado"));
			return "ambiente/alterar_form";
		}
		

		Ambiente ambienteAtualizar = ambienteDAO.buscarPeloId(ambiente.getId());
		BeanUtils.copyProperties(ambiente, ambienteAtualizar, "id");
		
		ambienteDAO.alterar(ambienteAtualizar);
		return "redirect:/app/ambiente/lista";
		
	}

	
	@GetMapping("app/adm/ambiente/excluir")
	public String excluirUsuario(@RequestParam(required = true) Long id) {
		ambienteDAO.deletar(new Ambiente(id));
		return "redirect:/app/ambiente/lista";
	}
}
