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

import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.CategoriaDAO;
import br.senai.sp.informatica.senaipatrimonio.model.Categoria;

/**
 * @author gdonega (Gustavo Donegá Queiroz)
 *
 */
@Controller
public class CategoriaController {

	/**
	 * Objetos injetados pelo Spring
	 */
	@Autowired
	private CategoriaDAO categoriaDAO;

	/**
	 * Abrir pagina de listagem de categorias
	 * 
	 * @param model
	 * 
	 * @return String
	 */
	@GetMapping("/app/categoria/lista")
	public String abrirLista(Model model) {
		model.addAttribute("categoria", new Categoria()); //Categoria para ser utilzada pelo formulario
		model.addAttribute("categorias", categoriaDAO.buscarTodos()); //Lista de categorias para serem utilizadas
		
		return "categoria/lista";
	}

	/**
	 * Metodo para salvar uma categoria
	 * 
	 * @param categoria
	 * @param brCategoria
	 * @param model
	 * 
	 * @return String
	 */
	@PostMapping("app/adm/categoria/salvar")
	public String salvar(@Valid Categoria categoria, BindingResult brCategoria, Model model) {

		//Valida as regras de negócio
		if (brCategoria.hasErrors()) {
			model.addAttribute("categorias", categoriaDAO.buscarTodos());
			return "categoria/lista";
		}

		//Valida se já foi cadastrado
		if (categoriaDAO.jaEstaCadastrado(categoria)) {
			model.addAttribute("categorias", categoriaDAO.buscarTodos());
			brCategoria.addError(new FieldError("categoria", "nome", "Essa categoria já está cadastrada"));
			return "categoria/lista";
		}

		//Persiste
		categoriaDAO.persistir(categoria);
		
		return "redirect:/app/categoria/lista";
	}

	/**
	 * Excluir uma categoria
	 * 
	 * @param Long id
	 * @param RedirectAttribuites redirectAttributes 
	 * @return String
	 */
	@GetMapping("app/adm/categoria/excluir")
	public String excluirCategoria(@RequestParam(required = true) Long id, RedirectAttributes redirectAttributes) {

		//Tenta Deletar, caso não consiga: manda uma mensagem para o usuario
		try {
			categoriaDAO.deletar(new Categoria(id));
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("outrosErros",
					"Você não pode excluir essa categoria, pois um ou mais patrimonios estão utilizando-a");
		}
		
		return "redirect:/app/categoria/lista";
	}

	/**
	 * Abrir pagina de alteração de categoria
	 * 
	 * @param Long id
	 * @param Model model
	 * @return String
	 */
	@GetMapping("app/adm/categoria/alterar")
	public String abrirAlterar(@RequestParam(required = true) Long id, Model model) {
		Categoria categoria = categoriaDAO.buscarPeloId(id);
		
		model.addAttribute("categoria", categoria);
		model.addAttribute("nomeOriginal", "Nome original: " + categoria.getNome());
		
		return "categoria/alterar_form";
	}

	/**
	 * Alterar uma categoria 
	 * 
	 * @param categoria
	 * @param result
	 * @param model
	 * 
	 * @return String
	 */
	@PostMapping("app/adm/categoria/alterar")
	public String alterar(@Valid Categoria categoria, BindingResult result, Model model) {

		//Busca a categoria no banco de dados
		Categoria categoriaOriginal = categoriaDAO.buscarPeloId(categoria.getId());

		//Valida as regras de negócio
		if (result.hasErrors()) {
			
			model.addAttribute("categoria", categoria);
			model.addAttribute("nomeOriginal", "Nome original: " + categoriaOriginal.getNome());
			
			return "categoria/alterar_form";
		}

		//Valida se a categoria já foi cadastrada ou não
		if (categoriaDAO.jaEstaCadastrado(categoria)) {
			
			result.addError(new FieldError("categoria", "nome", "Essa categoria já está cadastrada"));
			model.addAttribute("nomeOriginal", "Nome original: " + categoriaOriginal.getNome());
			
			return "categoria/alterar_form";
		}

		
		Categoria categoriaAtualizar = categoriaDAO.buscarPeloId(categoria.getId()); //Busca a categoria mais uma vez, para fazer a alteração
		BeanUtils.copyProperties(categoria, categoriaAtualizar, "id");//faz a as alterações 
		categoriaDAO.alterar(categoriaAtualizar); //salva as alterações
		
		return "redirect:/app/categoria/lista";

	}

}
