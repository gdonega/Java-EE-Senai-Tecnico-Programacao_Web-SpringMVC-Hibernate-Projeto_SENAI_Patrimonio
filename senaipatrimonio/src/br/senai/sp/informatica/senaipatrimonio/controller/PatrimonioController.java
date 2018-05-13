package br.senai.sp.informatica.senaipatrimonio.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.CategoriaDAO;
import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.PatrimonioDAO;
import br.senai.sp.informatica.senaipatrimonio.model.Categoria;
import br.senai.sp.informatica.senaipatrimonio.model.Patrimonio;

@Controller
public class PatrimonioController {

	@Autowired
	private PatrimonioDAO patrimonioDAO;

	@Autowired
	private CategoriaDAO categoriaDAO;

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

}
