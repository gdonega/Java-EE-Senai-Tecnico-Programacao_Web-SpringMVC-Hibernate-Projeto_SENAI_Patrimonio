package br.senai.sp.informatica.senaipatrimonio.controller;

import java.util.Date;
import java.util.List;

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
import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.PatrimonioDAO;
import br.senai.sp.informatica.senaipatrimonio.model.Categoria;
import br.senai.sp.informatica.senaipatrimonio.model.Patrimonio;
import br.senai.sp.informatica.senaipatrimonio.utils.SessionHelper;

@Controller
public class PatrimonioController {

	@Autowired
	private PatrimonioDAO patrimonioDAO;

	@Autowired
	private CategoriaDAO categoriaDAO;

	@Autowired
	private SessionHelper session;

	@GetMapping("app/adm/patrimonio/lista")
	public String abrirLista(Model model, @RequestParam(name = "filtro", required = false) Long categoriaId) {

		Categoria filtro = categoriaDAO.buscarPeloId(categoriaId);

		List<Patrimonio> patrimonios;

		if (filtro == null) {
			patrimonios = patrimonioDAO.buscarTodos();
		} else {
			patrimonios = patrimonioDAO.buscarPorCategoria(filtro);
		}

		model.addAttribute("patrimonios", patrimonios);

		model.addAttribute("tiposBusca", categoriaDAO.buscarTodos());

		return "patrimonio/lista";
	}

	@GetMapping("app/adm/patrimonio/form")
	public String abrirForm(@RequestParam(required = false) Long id, Model model) {

		Patrimonio patrimonio;
		if (id != null) {
			patrimonio = patrimonioDAO.buscarPeloId(id);
		} else {
			patrimonio = new Patrimonio();
		}
		model.addAttribute("patrimonio", patrimonio);
		model.addAttribute("categorias", categoriaDAO.buscarTodos());
		return "patrimonio/form";
	}

	@PostMapping("app/adm/patrimonio/salvar")
	public String salvarUsuario(@Valid Patrimonio patrimonio, BindingResult result, Model model) {
		System.err.println(patrimonio);
		if (result.hasFieldErrors("nome") || result.hasFieldErrors("categoria")) {
			model.addAttribute("categorias", categoriaDAO.buscarTodos());
			model.addAttribute(patrimonio);
			return "patrimonio/form";
		}

		if (patrimonioDAO.jaEstaCadastrado(patrimonio)) {
			model.addAttribute("categorias", categoriaDAO.buscarTodos());
			model.addAttribute(patrimonio);
			result.addError(new FieldError("patrimonio", "nome", "Esse patrimonio já está cadastrado!"));
			return "patrimonio/form";
		}

		if (patrimonio.getId() == null) {

			patrimonio.setDt_cadastro(new Date());
			patrimonio.setCadastrante(session.getUsuarioLogado());

			System.err.println(patrimonio);
			patrimonioDAO.persistir(patrimonio);

		} else {

			Patrimonio patrimonioAtualizar = patrimonioDAO.buscarPeloId(patrimonio.getId());

			BeanUtils.copyProperties(patrimonio, patrimonioAtualizar, "id", "dt_cadastro", "cadastrante");

			System.out.println(patrimonioAtualizar);

			patrimonioDAO.alterar(patrimonioAtualizar);
		}

		return "redirect:/app/adm/patrimonio/lista";
	}

	@GetMapping("app/adm/patrimonio/excluir")
	public String excluirUsuario(@RequestParam(required = true) Long id) {
		patrimonioDAO.deletar(new Patrimonio(id));
		return "redirect:/app/adm/patrimonio/lista";
	}

}
