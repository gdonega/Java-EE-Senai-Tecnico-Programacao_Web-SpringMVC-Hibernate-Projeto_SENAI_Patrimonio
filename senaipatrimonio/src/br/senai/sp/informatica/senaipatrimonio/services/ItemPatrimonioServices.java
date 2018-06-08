package br.senai.sp.informatica.senaipatrimonio.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.ItemPatrimonioDAO;
import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.MovimentacaoDAO;
import br.senai.sp.informatica.senaipatrimonio.exceptions.ChangeEnviromentException;
import br.senai.sp.informatica.senaipatrimonio.exceptions.EntityNotFoundException;
import br.senai.sp.informatica.senaipatrimonio.exceptions.ValidationException;
import br.senai.sp.informatica.senaipatrimonio.model.Ambiente;
import br.senai.sp.informatica.senaipatrimonio.model.ItemPatrimonio;
import br.senai.sp.informatica.senaipatrimonio.model.Movimentacao;
import br.senai.sp.informatica.senaipatrimonio.model.Usuario;
import br.senai.sp.informatica.senaipatrimonio.model.UsuarioBase;
import br.senai.sp.informatica.senaipatrimonio.model.UsuarioJWT;

@Service
public class ItemPatrimonioServices {

	@Autowired
	private ItemPatrimonioDAO itemPatrimonioDAO;
	
	@Autowired
	private MovimentacaoDAO movimentacaoDAO;
	
	@Autowired 
	private AmbienteServices ambienteServices;
	
	

	/**
	 * Retorna a lista completa de items de todos os patrimonios
	 * 
	 * @return List<ItemPatrimonio>
	 */
	public List<ItemPatrimonio> buscarTodos(){
		return itemPatrimonioDAO.buscarTodos();
	}
	
	
	/**
	 * Retorna um Item de patrimonio a partir do id
	 * 
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
	
	/**
	 * Retorna a lista de movimentações de um item
	 * 
	 * @param id
	 * @return List<Movimentacao>
	 * @throws EntityNotFoundException
	 */
	public List<Movimentacao> buscarMovimentacoes (Long id) throws EntityNotFoundException{
		ItemPatrimonio itemBuscado = buscarPorId(id);
		return movimentacaoDAO.buscarPorItemPatrimonio(itemBuscado);
	}
	
	/**
	 * Muda o ambiente atual do item
	 * 
	 * @param idItem
	 * @param novoAmbienteId
	 * @return ItemPatrimonio
	 * @throws EntityNotFoundException
	 * @throws ChangeEnviromentException
	 * @throws ValidationException 
	 */
	public ItemPatrimonio moverItem(Long idItem, Movimentacao movimentacao) throws EntityNotFoundException, ChangeEnviromentException, ValidationException {
		ItemPatrimonio itemBuscado = buscarPorId(idItem);
		
		Ambiente ambienteNovo = movimentacao.getAmbienteNovo();
		if(ambienteNovo == null || ambienteNovo.getId() == null)
			throw new ValidationException();
		
		Ambiente ambienteBuscado = ambienteServices.buscarPorId(ambienteNovo.getId());
		
		if(itemBuscado.getAmbienteAtual().getId() == ambienteBuscado.getId()) 
			throw new ChangeEnviromentException();
		
		
		UsuarioJWT usuarioJWT = (UsuarioJWT) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Usuario usuarioAuth = new Usuario();
		usuarioAuth.setId(usuarioJWT.getId());
		
		
		

		
		movimentacao.setAmbienteOriginal(itemBuscado.getAmbienteAtual());
		movimentacao.setDataDaMovimentacao(new Date());
		movimentacao.setExecutou(usuarioAuth);
		movimentacao.setItemMovido(itemBuscado);
		
		
		itemBuscado.setAmbienteAtual(ambienteBuscado);
		itemBuscado.setDataMovimentacao(new Date());
		
		itemPatrimonioDAO.alterar(itemBuscado);
		movimentacaoDAO.persistir(movimentacao);
		
		return itemBuscado;
	}
}
