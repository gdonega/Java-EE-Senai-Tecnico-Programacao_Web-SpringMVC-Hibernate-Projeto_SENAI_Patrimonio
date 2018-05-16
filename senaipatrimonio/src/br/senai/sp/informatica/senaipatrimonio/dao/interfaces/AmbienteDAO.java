package br.senai.sp.informatica.senaipatrimonio.dao.interfaces;

import java.util.List;

import br.senai.sp.informatica.senaipatrimonio.model.Ambiente;

public interface AmbienteDAO extends DAO<Ambiente>{

	public Boolean jaEstaCadastrado(Ambiente ambiente);
	
	public List<Ambiente> buscarEliminandoUm(Ambiente ambiente);
	
}
