package br.senai.sp.informatica.senaipatrimonio.dao.interfaces;

import br.senai.sp.informatica.senaipatrimonio.model.Categoria;
import br.senai.sp.informatica.senaipatrimonio.model.Patrimonio;

import java.util.List;

public interface PatrimonioDAO extends DAO<Patrimonio> {

    public List<Patrimonio> buscarPorCategoria(Categoria categoria);

    public Boolean jaEstaCadastrado(Patrimonio patrimonio);
}
