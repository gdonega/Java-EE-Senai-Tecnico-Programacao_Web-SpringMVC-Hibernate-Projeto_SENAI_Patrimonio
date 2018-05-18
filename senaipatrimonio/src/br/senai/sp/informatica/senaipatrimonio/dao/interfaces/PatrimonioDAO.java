package br.senai.sp.informatica.senaipatrimonio.dao.interfaces;

import br.senai.sp.informatica.senaipatrimonio.model.Categoria;
import br.senai.sp.informatica.senaipatrimonio.model.Patrimonio;

import java.util.List;

public interface PatrimonioDAO extends DAO<Patrimonio> {

	/**
	 * Busca patrimonios por categoria
	 * 
	 * @param categoria
	 * @return List<Patrimonio>
	 */
	List<Patrimonio> buscarPorCategoria(Categoria categoria);

	/**
	 * Confere se já foi cadastrado
	 * 
	 * @param patrimonio
	 * @return Boolean
	 */
	Boolean jaEstaCadastrado(Patrimonio patrimonio);

	/**
	 * Confere se existe outro com o mesmo nome
	 * 
	 * @param patrimonio
	 * @return Boolean
	 */
	Boolean existeOutroComEsseNome(Patrimonio patrimonio);
}
