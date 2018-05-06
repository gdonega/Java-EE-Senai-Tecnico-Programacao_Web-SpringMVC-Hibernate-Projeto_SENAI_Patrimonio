package br.senai.sp.informatica.senaipatrimonio.dao.interfaces;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * Interface genérica para toda classe/inteface DAO
 * 
 * 
 * @author Gustavo Donegá Queiroz
 *
 * @param <Obj>
 *            Model da DAO que implementar essa interface
 */
@Transactional
public interface DAO<Obj> {

	/**
	 * Persiste um Objeto no banco de dados
	 * 
	 * @param obj
	 */
	public void persistir(Obj obj);

	/**
	 * Altera um Objeto do banco de dados
	 * 
	 * @param obj
	 */
	public void alterar(Obj obj);

	/**
	 * Deleta um Objeto do banco de dados
	 * 
	 * @param obj
	 */
	public void deletar(Obj obj);

	/**
	 * Recupera um Objeto do banco de dados pelo ID
	 * 
	 * @param id
	 * @return Objeto
	 */
	public Obj buscarPeloId(Long id);

	/**
	 * Pega todos os objs do banco de dados
	 * 
	 * @return Lista de Objetos
	 */
	public List<Obj> buscarTodos();

}
