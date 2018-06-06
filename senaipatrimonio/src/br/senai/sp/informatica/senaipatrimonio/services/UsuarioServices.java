package br.senai.sp.informatica.senaipatrimonio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.UsuarioDAO;
import br.senai.sp.informatica.senaipatrimonio.exceptions.EntityNotFoundException;
import br.senai.sp.informatica.senaipatrimonio.exceptions.ValidationException;
import br.senai.sp.informatica.senaipatrimonio.model.Usuario;

@Service
public class UsuarioServices {

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	/**
	 * Procura um Usuario através da DAO
	 * 
	 * @param usuario
	 * @param brUsuario
	 * 
	 * @return Usuario
	 * 
	 * @throws ValidationException
	 * @throws EntityNotFoundException
	 */
	public Usuario buscarPorEmailESenha(Usuario usuario, BindingResult brUsuario) throws ValidationException, EntityNotFoundException {
		//Valida os dados
		if (brUsuario.hasFieldErrors("senha") || brUsuario.hasFieldErrors("email")) {
			
			throw new ValidationException();
		}

		//hashea a senha do usuairo
		usuario.hashearSenha();
		
		//Busca o usuario através da dao
		Usuario usuarioBuscado = usuarioDAO.buscarPorEmailESenha(usuario.getEmail(), usuario.getSenha());
		
		
		//Confere se o usuario foi encontrado
		if (usuarioBuscado == null) {
			throw new EntityNotFoundException();
		}
		
		//retorna o usuario
		return usuarioBuscado;
	}
	
}
