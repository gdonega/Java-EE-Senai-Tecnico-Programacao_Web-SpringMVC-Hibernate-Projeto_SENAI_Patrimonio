package br.senai.sp.informatica.senaipatrimonio.controller;

import java.io.File;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.AmbienteDAO;
import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.ItemPatrimonioDAO;
import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.MovimentacaoDAO;
import br.senai.sp.informatica.senaipatrimonio.model.ItemPatrimonio;
import br.senai.sp.informatica.senaipatrimonio.model.Movimentacao;
import br.senai.sp.informatica.senaipatrimonio.model.Patrimonio;
import br.senai.sp.informatica.senaipatrimonio.utils.Constantes;
import br.senai.sp.informatica.senaipatrimonio.utils.OutrosMetodos;
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
	@Autowired
	private MovimentacaoDAO movimentacaoDAO;


	@GetMapping("app/item/form")
	public String formItem(@RequestParam(required = false) Long idPatrimonio, Model model) {

		Patrimonio patrimonio = new Patrimonio();
		patrimonio.setId(idPatrimonio);

		ItemPatrimonio item = new ItemPatrimonio();

		item.setPatrimonio(patrimonio);

		model.addAttribute("itemPatrimonio", item);
		model.addAttribute("ambientes", ambienteDAO.buscarTodos());

		return "item_patrimonio/formNovo";
	}

	@PostMapping("app/item/salvar")
	public String salvarItem(@Valid ItemPatrimonio itemPatrimonio, BindingResult result,
			@RequestPart(name = "fotoItemPatrimonio", required = false) MultipartFile arquivo, Model model) {

		if (result.hasFieldErrors("patrimonio") || result.hasFieldErrors("ambienteAtual")) {
			model.addAttribute("itemPatrimonio", itemPatrimonio);
			model.addAttribute("ambientes", ambienteDAO.buscarTodos());
			return "item_patrimonio/formNovo";
		}

		itemPatrimonio.setCadastrante(sessionHelper.getUsuarioLogado());
		itemPatrimonioDAO.persistir(itemPatrimonio);

		// Upload da foto

		System.err.println(arquivo);

		if (!arquivo.isEmpty()) {
			try {

				// Diretório das fotos de patrimonio
				String caminhoDaPastaPatrimonioFotos = context.getRealPath(Constantes.URL_BASE_FOTO_ITEM_PATRIMONIO);

				// Cria as pastas
				File pasta = new File(caminhoDaPastaPatrimonioFotos);
				if (!pasta.exists())
					pasta.mkdirs();

				// Define o caminho do arquivo
				// + "."+ FilenameUtils.getExtension(arquivo.getOriginalFilename()
				String caminhoArquivo = caminhoDaPastaPatrimonioFotos + "foto_" + itemPatrimonio.getId();

				// Criar um obj File - classe responsavel por gerenciar arquivos e pastas
				File file = new File(caminhoArquivo);

				// Cria o arquivo caso ele n�o exista
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
			System.err.println("Arquivo não enviado");
		}

		return "redirect:/app/patrimonio/itens?id=" + itemPatrimonio.getPatrimonio().getId();
	}

	@GetMapping("app/adm/item/excluir")
	public String excluirItem(@RequestParam(required = true) Long id) {

		Long idPatrimonio;

		ItemPatrimonio item = itemPatrimonioDAO.buscarPeloId(id);

		idPatrimonio = item.getPatrimonio().getId();

		itemPatrimonioDAO.deletar(item);

		OutrosMetodos.excluirFotoFile(id, context.getRealPath(Constantes.URL_BASE_FOTO_ITEM_PATRIMONIO));

		return "redirect:/app/patrimonio/itens?id=" + idPatrimonio;
	}

	@GetMapping("app/item/movimentacoes")
	public String listaItensPatrimonio(@RequestParam(required = true, name = "id") Long itemId, Model model,
			@RequestParam(required = false, name = "movimentacao") Movimentacao movimentacao,
			@RequestParam(required = false, name = "bindingResult") BindingResult result) {
		ItemPatrimonio item = itemPatrimonioDAO.buscarPeloId(itemId);
		model.addAttribute("itemPatrimonio", item);
		model.addAttribute("caminhoImagem", OutrosMetodos.getCaminhoItemPatrimonioImagem(item,
				context.getRealPath(Constantes.URL_BASE_FOTO_ITEM_PATRIMONIO)));

		model.addAttribute("movimentacoes", movimentacaoDAO.buscarPorItemPatrimonio(item));
		model.addAttribute("movimentacao", new Movimentacao());
		model.addAttribute("ambientes", ambienteDAO.buscarEliminandoUm(item.getAmbienteAtual()));

		if (model.asMap().containsKey("bindingResult")) {
			model.addAttribute("org.springframework.validation.BindingResult.movimentacao",
					model.asMap().get("bindingResult"));
		}

		return "movimentacao/visualizar";
	}

	@PostMapping("app/movimentacao/nova")
	public String movimentar(@Valid Movimentacao movimentacao, BindingResult result, Long idItemPatrimonio,
			RedirectAttributes redirectAttributes) {

		System.err.println(movimentacao);

		if (movimentacao.getAmbienteNovo().getId() == null) {
			result.addError(new FieldError("movimentacao", "ambienteNovo", "Selecione um local para a mover o item!"));
			redirectAttributes.addFlashAttribute("bindingResult", result);
			redirectAttributes.addFlashAttribute("movimentacao", movimentacao);
		} else {
			movimentacao.setDataDaMovimentacao(new Date());
			movimentacaoDAO.persistir(movimentacao);
			
			ItemPatrimonio item = itemPatrimonioDAO.buscarPeloId(movimentacao.getItemMovido().getId());
			item.setDataMovimentacao(new Date());
			item.setAmbienteAtual(movimentacao.getAmbienteNovo());
			itemPatrimonioDAO.alterar(item);
		}

		return "redirect:/app/item/movimentacoes?id=" + movimentacao.getItemMovido().getId();
	}

	// //Futura alterao de foto
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
