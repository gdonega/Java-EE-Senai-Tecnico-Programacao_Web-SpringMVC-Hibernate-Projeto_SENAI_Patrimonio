package br.senai.sp.informatica.senaipatrimonio.dao.interfaces;

import br.senai.sp.informatica.senaipatrimonio.model.Ambiente;
import br.senai.sp.informatica.senaipatrimonio.model.Categoria;

public interface CategoriaDAO extends DAO<Categoria> {
	public Boolean jaEstaCadastrado(Categoria categoria);
}
