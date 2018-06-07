package br.senai.sp.informatica.senaipatrimonio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.AmbienteDAO;
import br.senai.sp.informatica.senaipatrimonio.exceptions.EntityNotFoundException;
import br.senai.sp.informatica.senaipatrimonio.model.Ambiente;
import br.senai.sp.informatica.senaipatrimonio.model.ItemPatrimonio;

@Service
public class AmbienteServices {

	@Autowired
	private AmbienteDAO ambienteDAO;
	
	public List<Ambiente> buscarTodos(){
		return ambienteDAO.buscarTodos();
	}
	
	public Ambiente buscarPorId(Long id) throws EntityNotFoundException {
		Ambiente ambienteBuscado = ambienteDAO.buscarPeloId(id);
		
		if(ambienteBuscado == null)
			throw new EntityNotFoundException();
		
		return ambienteBuscado;

	}
	
}
