package br.senai.sp.informatica.senaipatrimonio.dao.interfaces;

import java.util.List;

import br.senai.sp.informatica.senaipatrimonio.model.TipoUsuario;
import br.senai.sp.informatica.senaipatrimonio.model.Usuario;

/**
 * Interface para fazer consultas/alterações na Tabela Usuario do Banco de Dados
 * 
 * @author Gustavo Donegá Queiroz
 *
 */
public interface UsuarioDAO extends DAO<Usuario> {

	/**
	 * Busca Usuario pelo email
	 * 
	 * @param email
	 * @return Usuario
	 */
	public Usuario buscarPorEmail(String email);

	/**
	 * Busca Usuario pelo email e senha
	 * 
	 * @param email
	 * @param senha
	 * @return Usuario
	 */
	public Usuario buscarPorEmailESenha(String email, String senha);

	/**
	 * Busca lista de usuarios pelo tipo
	 * 
	 * @param tipo
	 * @return Usuario
	 */
	public List<Usuario> buscarPorTipo(TipoUsuario tipo);
}
