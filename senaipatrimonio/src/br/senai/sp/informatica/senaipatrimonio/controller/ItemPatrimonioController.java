package br.senai.sp.informatica.senaipatrimonio.controller;

import java.io.File;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.AmbienteDAO;
import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.ItemPatrimonioDAO;
import br.senai.sp.informatica.senaipatrimonio.model.ItemPatrimonio;
import br.senai.sp.informatica.senaipatrimonio.model.Patrimonio;
import br.senai.sp.informatica.senaipatrimonio.utils.Constantes;
import br.senai.sp.informatica.senaipatrimonio.utils.SessionHelper;

@Controller
public class ItemPatrimonioController {

	@Autowired
	private ItemPatrimonioDAO itemPatrimonioDAO;
	@Autowired
	private AmbienteDAO ambienteDAO;
	@Autowired
	private SessionHelper sessionHelper;

	@Autowired
	private ServletContext context;

	@GetMapping("app/item/form")
	public String formItem(@RequestParam(required = false) Long idPatrimonio,
			@RequestPart(name = "fotoItem", required = false) MultipartFile arquivo, Model model) {

		Patrimonio patrimonio = new Patrimonio();
		patrimonio.setId(idPatrimonio);

		ItemPatrimonio item = new ItemPatrimonio();

		item.setPatrimonio(patrimonio);

		model.addAttribute("itemPatrimonio", item);
		model.addAttribute("ambientes", ambienteDAO.buscarTodos());

		// Upload da foto
		if (arquivo != null) {
			try {

				// DiretÃ³rio das fotos de patrimonio
				String caminhoDaPastaPatrimonioFotos = context.getRealPath(Constantes.URL_BASE_FOTO_ITEM_PATRIMONIO);

				// Cria as pastas
				File pasta = new File(caminhoDaPastaPatrimonioFotos);
				if (!pasta.exists())
					pasta.mkdirs();

				// Define o caminho do arquivo
				// + "."+ FilenameUtils.getExtension(arquivo.getOriginalFilename()
				String caminhoArquivo = caminhoDaPastaPatrimonioFotos + "foto_" + patrimonio.getId();

				// Criar um obj File - classe responsavel por gerenciar arquivos e pastas
				File file = new File(caminhoArquivo);

				// Cria o arquivo caso ele nï¿½o exista
				if (!file.exists())
					file.createNewFile();

				// Transfere os dados do aruqivo upado (multipart) para um arquivo na maquina
				// (File)
				arquivo.transferTo(file);

				// BufferedImage <- classe que manipula imagens no java
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("Arquivo nÃ£o enviado");
		}

		return "item_patrimonio/formNovo";
	}

	@PostMapping("app/item/salvar")
	public String salvarItem(@Valid ItemPatrimonio itemPatrimonio, BindingResult result, Model model) {
		if (result.hasFieldErrors("patrimonio") || result.hasFieldErrors("ambienteAtual")) {
			model.addAttribute("itemPatrimonio", itemPatrimonio);
			model.addAttribute("ambientes", ambienteDAO.buscarTodos());
			return "item_patrimonio/formNovo";
		}

		itemPatrimonio.setCadastrante(sessionHelper.getUsuarioLogado());
		itemPatrimonioDAO.persistir(itemPatrimonio);

		return "redirect:/app/patrimonio/itens?id=" + itemPatrimonio.getPatrimonio().getId();
	}

	// //Futura alteração de foto
	//
	// @GetMapping("app/adm/item/alterarForm")
	// public String formItemAlterar(@RequestParam(required = false) Long id, Model
	// model) {
	// model.addAttribute("itemPatrimonio", itemPatrimonioDAO.buscarPeloId(id));
	// model.addAttribute("ambientes", ambienteDAO.buscarTodos());
	//
	// return "item_patrimonio/formAlterar";
	// }
	//
	// @PostMapping("app/adm/item/alterar")
	// public String salvartem(@Valid ItemPatrimonio itemPatrimonio, Model model) {
	//
	//
	//
	//
	// return "redirect:/app/patrimonio/itens?id=" +
	// itemPatrimonio.getPatrimonio().getId();
	//
	// }
}
