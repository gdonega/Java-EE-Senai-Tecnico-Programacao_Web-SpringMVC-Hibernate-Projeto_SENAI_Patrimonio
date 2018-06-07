package br.senai.sp.informatica.senaipatrimonio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.ItemPatrimonioDAO;
import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.PatrimonioDAO;
import br.senai.sp.informatica.senaipatrimonio.exceptions.EntityNotFoundException;
import br.senai.sp.informatica.senaipatrimonio.model.ItemPatrimonio;
import br.senai.sp.informatica.senaipatrimonio.model.Patrimonio;

@Service
public class PatrimonioService {

	@Autowired
	private PatrimonioDAO patrimonioDAO;
	
	@Autowired
	private ItemPatrimonioDAO itemPatrimonioDAO;
	
	/**
	 * Lista Todos os Patrimonios
	 * @return List<Patrimonio>
	 */
	public List<Patrimonio> buscarTodos(){
		return patrimonioDAO.buscarTodos();
	}
	
	/**
	 * Retorna um patrimonio através do id
	 * @param id
	 * @return Patrimonio
	 * @throws EntityNotFoundException
	 */
	public Patrimonio buscarPeloId(Long id) throws EntityNotFoundException {
		Patrimonio patrimonio = patrimonioDAO.buscarPeloId(id);
		
		if(patrimonio == null)
			throw new EntityNotFoundException();
		
		return patrimonio;
	}
	
	
	/**
	 * Lista itens de um determinado patrimonio
	 * @param id
	 * @return List<ItemPatrimonio> 
	 * @throws EntityNotFoundException
	 */
	public List<ItemPatrimonio> buscarItens(Long id) throws EntityNotFoundException{
		Patrimonio patrimonioBuscado = buscarPeloId(id);
		return itemPatrimonioDAO.buscarPorPatrimonio(patrimonioBuscado);		
	}
}
