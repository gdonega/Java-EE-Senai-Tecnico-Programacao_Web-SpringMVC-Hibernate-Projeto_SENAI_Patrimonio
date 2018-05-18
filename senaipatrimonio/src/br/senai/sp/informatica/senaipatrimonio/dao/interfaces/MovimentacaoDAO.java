package br.senai.sp.informatica.senaipatrimonio.dao.interfaces;

import java.util.List;

import br.senai.sp.informatica.senaipatrimonio.model.ItemPatrimonio;
import br.senai.sp.informatica.senaipatrimonio.model.Movimentacao;

public interface MovimentacaoDAO extends DAO<Movimentacao> {

	/**
	 * Lista movimentações de um item
	 * 
	 * @param itemPatrimonio
	 * @return List<Movimentacao>
	 */
	List<Movimentacao> buscarPorItemPatrimonio(ItemPatrimonio itemPatrimonio);

}
