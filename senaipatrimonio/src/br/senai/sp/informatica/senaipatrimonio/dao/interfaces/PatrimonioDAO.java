package br.senai.sp.informatica.senaipatrimonio.dao.interfaces;

import java.util.List;

import br.senai.sp.informatica.senaipatrimonio.model.Categoria;
import br.senai.sp.informatica.senaipatrimonio.model.Patrimonio;

public interface PatrimonioDAO extends DAO<Patrimonio> {

	public List<Patrimonio> buscarPorCategoria(Categoria categoria);

}
