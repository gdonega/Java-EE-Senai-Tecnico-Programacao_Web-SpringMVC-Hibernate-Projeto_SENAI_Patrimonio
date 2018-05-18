package br.senai.sp.informatica.senaipatrimonio.dao.interfaces;

import br.senai.sp.informatica.senaipatrimonio.model.Categoria;

public interface CategoriaDAO extends DAO<Categoria> {

	/**
	 * Confere se já foi cadastrado
	 * 
	 * @param categoria
	 * @return Boolean
	 */
	public Boolean jaEstaCadastrado(Categoria categoria);
}
