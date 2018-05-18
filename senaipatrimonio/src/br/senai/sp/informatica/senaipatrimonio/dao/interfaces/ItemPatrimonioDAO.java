package br.senai.sp.informatica.senaipatrimonio.dao.interfaces;

import br.senai.sp.informatica.senaipatrimonio.model.ItemPatrimonio;
import br.senai.sp.informatica.senaipatrimonio.model.Patrimonio;

import java.util.List;

public interface ItemPatrimonioDAO extends DAO<ItemPatrimonio> {

	/**
	 * Lista Itens de um patrimonio
	 * 
	 * @param patrimonio
	 * @return List<ItePatrimonio>
	 */
	List<ItemPatrimonio> buscarPorPatrimonio(Patrimonio patrimonio);

}
