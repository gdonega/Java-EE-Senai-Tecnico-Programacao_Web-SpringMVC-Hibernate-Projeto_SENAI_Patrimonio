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

import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.CategoriaDAO;
import br.senai.sp.informatica.senaipatrimonio.model.Categoria;

@Controller
public class CategoriaController {

	@Autowired	
	private CategoriaDAO categoriaDAO;
	
	@GetMapping("/app/categoria/lista")
	public String abrirLista(Model model) {

		model.addAttribute("categoria", new Categoria());
		model.addAttribute("categorias", categoriaDAO.buscarTodos());
		return "categoria/lista";
	}
	
	@PostMapping("app/adm/categoria/salvar")
	public String salvar(@Valid Categoria categoria,BindingResult brCategoria ,Model model) {
		
		if(brCategoria.hasErrors()) {
			model.addAttribute("categorias", categoriaDAO.buscarTodos());
			return "categoria/lista";
		}
		
		if(categoriaDAO.jaEstaCadastrado(categoria)) {
			model.addAttribute("categorias", categoriaDAO.buscarTodos());
			brCategoria.addError(new FieldError("categoria", "nome", "Essa categoria já está cadastrada"));
			return "categoria/lista";
		}
		
		
		categoriaDAO.persistir(categoria);
		return "redirect:/app/categoria/lista";
	}
	
	@GetMapping("app/adm/categoria/excluir")
	public String excluirUsuario(@RequestParam(required = true) Long id) {
		categoriaDAO.deletar(new Categoria(id));
		return "redirect:/app/categoria/lista";
	}
	
	
	
	
	
	
	@GetMapping("app/adm/categoria/alterar")
	public String abrirAlterar(@RequestParam(required = true) Long id ,Model model) {
		model.addAttribute("categoria",categoriaDAO.buscarPeloId(id));
		return "categoria/alterar_form";
	}

	@PostMapping("app/adm/categoria/alterar")
	public String alterar(@Valid Categoria categoria,BindingResult result ,Model model) {
		
		if(result.hasErrors()) {
			return "categoria/alterar_form";
		}
		
		if(categoriaDAO.jaEstaCadastrado(categoria)) {
			result.addError(new FieldError("categoria", "nome", "Essa categoria já está cadastrada"));
			return "categoria/alterar_form";
		}
		

		Categoria categoriaAtualizar = categoriaDAO.buscarPeloId(categoria.getId());
		BeanUtils.copyProperties(categoria, categoriaAtualizar, "id");
		
		categoriaDAO.alterar(categoriaAtualizar);
		return "redirect:/app/categoria/lista";
		
	}
	
}
