package br.senai.sp.informatica.senaipatrimonio.dao.interfaces;

import br.senai.sp.informatica.senaipatrimonio.model.ItemPatrimonio;
import br.senai.sp.informatica.senaipatrimonio.model.Movimentacao;

import java.util.List;

public interface MovimentacaoDAO extends DAO<Movimentacao>{


    List<Movimentacao> buscarPorItemPatrimonio(ItemPatrimonio itemPatrimonio);

}
