package br.senai.sp.informatica.senaipatrimonio.controller;

import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.AmbienteDAO;
import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.ItemPatrimonioDAO;
import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.MovimentacaoDAO;
import br.senai.sp.informatica.senaipatrimonio.model.ItemPatrimonio;
import br.senai.sp.informatica.senaipatrimonio.model.Movimentacao;
import br.senai.sp.informatica.senaipatrimonio.utils.Constantes;
import br.senai.sp.informatica.senaipatrimonio.utils.OutrosMetodos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.util.Date;

@Controller
public class MovimentacaoController {

	// Objetos injetados pelo Spring
	@Autowired
	private ItemPatrimonioDAO itemPatrimonioDAO;

	@Autowired
	private ServletContext context;

	@Autowired
	private MovimentacaoDAO movimentacaoDAO;

	@Autowired
	private AmbienteDAO ambienteDAO;

	/**
	 * Abre a lista de Movimentações de um Item
	 * 
	 * @param itemId
	 * @param model
	 * @param movimentacao
	 * @param result
	 * @return String
	 */
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

		//pega o bindResult vindo por um flashAttribuit e coloca ele no model atual
		if (model.asMap().containsKey("bindingResult")) {
			model.addAttribute("org.springframework.validation.BindingResult.movimentacao",
					model.asMap().get("bindingResult"));
		}

		return "movimentacao/visualizar";
	}

	/**
	 * Faz uma nova movimentação
	 * 
	 * @param movimentacao
	 * @param result
	 * @param idItemPatrimonio
	 * @param redirectAttributes
	 * @return String
	 */
	@PostMapping("app/movimentacao/nova")
	public String movimentar(@Valid Movimentacao movimentacao, BindingResult result, Long idItemPatrimonio,
			RedirectAttributes redirectAttributes) {

		//Validação (null)
		if (movimentacao.getAmbienteNovo().getId() == null) {
			result.addError(new FieldError("movimentacao", "ambienteNovo", "Selecione um local para a mover o item!"));
			redirectAttributes.addFlashAttribute("bindingResult", result);
			redirectAttributes.addFlashAttribute("movimentacao", movimentacao);
		} else {
			//Salva a movimentação
			movimentacao.setDataDaMovimentacao(new Date());
			movimentacaoDAO.persistir(movimentacao);

			//Atualiza o item
			ItemPatrimonio item = itemPatrimonioDAO.buscarPeloId(movimentacao.getItemMovido().getId());
			item.setDataMovimentacao(new Date());
			item.setAmbienteAtual(movimentacao.getAmbienteNovo());
			itemPatrimonioDAO.alterar(item);
		}

		return "redirect:/app/item/movimentacoes?id=" + movimentacao.getItemMovido().getId();
	}

}
