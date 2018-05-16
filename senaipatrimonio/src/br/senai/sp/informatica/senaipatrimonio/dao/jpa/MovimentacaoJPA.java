package br.senai.sp.informatica.senaipatrimonio.dao.jpa;

import java.util.List;

import br.senai.sp.informatica.senaipatrimonio.model.ItemPatrimonio;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.MovimentacaoDAO;
import br.senai.sp.informatica.senaipatrimonio.model.Movimentacao;
import br.senai.sp.informatica.senaipatrimonio.model.Patrimonio;


@Primary
@Repository
public class MovimentacaoJPA implements MovimentacaoDAO{
	/*
	 * Obj de sess�o com o db
	 */
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void persistir(Movimentacao obj) {
		sessionFactory.getCurrentSession().persist(obj);
		
	}

	@Override
	public void alterar(Movimentacao obj) {
		sessionFactory.getCurrentSession().update(obj);
		
	}

	@Override
	public void deletar(Movimentacao obj) {
		sessionFactory.getCurrentSession().delete(obj);
		
	}

	@Override
	public Movimentacao buscarPeloId(Long id) {
		String hql = "FROM Movimentacao m WHERE m.id = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);

		List<Movimentacao> resultados = query.list();

		if (!resultados.isEmpty())
			return resultados.get(0);

		return null;
	}

	@Override
	public List<Movimentacao> buscarTodos() {
		String hql = "FROM Movimentacao m";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public List<Movimentacao> buscarPorItemPatrimonio(ItemPatrimonio itemPatrimonio) {
		String hql = "FROM Movimentacao m WHERE m.itemMovido.id = :itemId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("itemId", itemPatrimonio.getId());

		return query.list();
	}
}
