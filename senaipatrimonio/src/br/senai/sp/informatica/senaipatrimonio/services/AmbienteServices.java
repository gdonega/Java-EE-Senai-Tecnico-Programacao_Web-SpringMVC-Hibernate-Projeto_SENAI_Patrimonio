package br.senai.sp.informatica.senaipatrimonio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.AmbienteDAO;
import br.senai.sp.informatica.senaipatrimonio.model.Ambiente;

@Service
public class AmbienteServices {

	@Autowired
	private AmbienteDAO ambienteDAO;
	
	public List<Ambiente> buscarTodos(){
		return ambienteDAO.buscarTodos();
	}
	
}
