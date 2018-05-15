package br.senai.sp.informatica.senaipatrimonio.dao.interfaces;

import br.senai.sp.informatica.senaipatrimonio.model.Categoria;
import br.senai.sp.informatica.senaipatrimonio.model.Patrimonio;

import java.util.List;

public interface PatrimonioDAO extends DAO<Patrimonio> {

    List<Patrimonio> buscarPorCategoria(Categoria categoria);

    Boolean jaEstaCadastrado(Patrimonio patrimonio);

    Boolean existeOutroComEsseNome(Patrimonio patrimonio);
}
