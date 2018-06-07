package br.senai.sp.informatica.senaipatrimonio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.ItemPatrimonioDAO;
import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.MovimentacaoDAO;
import br.senai.sp.informatica.senaipatrimonio.exceptions.EntityNotFoundException;
import br.senai.sp.informatica.senaipatrimonio.model.ItemPatrimonio;
import br.senai.sp.informatica.senaipatrimonio.model.Movimentacao;

@Service
public class ItemPatrimonioServices {

	@Autowired
	private ItemPatrimonioDAO itemPatrimonioDAO;
	
	@Autowired
	private MovimentacaoDAO movimentacaoDAO;

	/**
	 * Retorna a lista completa de items de todos os patrimonios
	 * @return List<ItemPatrimonio>
	 */
	public List<ItemPatrimonio> buscarTodos(){
		return itemPatrimonioDAO.buscarTodos();
	}
	
	
	/**
	 * Retorna um Item de patrimonio a partir do id
	 * @param id
	 * @return ItemPatrimonio
	 * @throws EntityNotFoundException
	 */
	public ItemPatrimonio buscarPorId(Long id) throws EntityNotFoundException {
		ItemPatrimonio itemBuscado = itemPatrimonioDAO.buscarPeloId(id);
		
		if(itemBuscado == null)
			throw new EntityNotFoundException();
		
		return itemBuscado;

	}
	
	
	public List<Movimentacao> buscarMovimentacoes (Long id) throws EntityNotFoundException{
		ItemPatrimonio itemBuscado = buscarPorId(id);
		return movimentacaoDAO.buscarPorItemPatrimonio(itemBuscado);
	}
}
