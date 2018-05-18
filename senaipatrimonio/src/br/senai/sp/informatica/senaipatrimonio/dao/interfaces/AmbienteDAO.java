package br.senai.sp.informatica.senaipatrimonio.dao.interfaces;

import java.util.List;

import br.senai.sp.informatica.senaipatrimonio.model.Ambiente;

public interface AmbienteDAO extends DAO<Ambiente> {

	/**
	 * Confere se j� cadastrado
	 * 
	 * @param ambiente
	 * @return Boolean
	 */
	public Boolean jaEstaCadastrado(Ambiente ambiente);

	/**
	 * Lista os ambientes excluindo o que passado
	 * 
	 * @param ambiente
	 * @return List<Ambiente>
	 */
	public List<Ambiente> buscarEliminandoUm(Ambiente ambiente);

}
