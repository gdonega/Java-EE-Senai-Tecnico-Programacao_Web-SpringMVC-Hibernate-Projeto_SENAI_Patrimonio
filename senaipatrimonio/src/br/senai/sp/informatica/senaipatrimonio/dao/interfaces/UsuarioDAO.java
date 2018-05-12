package br.senai.sp.informatica.senaipatrimonio.dao.interfaces;

import java.util.List;

import br.senai.sp.informatica.senaipatrimonio.model.TipoUsuario;
import br.senai.sp.informatica.senaipatrimonio.model.Usuario;

/**
 * Interface para fazer consultas/altera��es na Tabela Usuario do Banco de Dados
 * 
 * @author Gustavo Doneg� Queiroz
 *
 */
public interface UsuarioDAO extends DAO<Usuario> {

	public Usuario buscarPorEmail(String email);

	public Usuario buscarPorEmailESenha(String email, String senha);
	
	public List<Usuario> buscarPorTipo(TipoUsuario tipo);
}
