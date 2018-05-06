package br.senai.sp.informatica.senaipatrimonio.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.ItemPatrimonioDAO;
import br.senai.sp.informatica.senaipatrimonio.model.ItemPatrimonio;

@Primary
@Repository
public class ItemPatrimonioJPA implements ItemPatrimonioDAO {

	/*
	 * Obj de sessão com o db
	 */
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void persistir(ItemPatrimonio obj) {
		sessionFactory.getCurrentSession().persist(obj);

	}

	@Override
	public void alterar(ItemPatrimonio obj) {
		sessionFactory.getCurrentSession().update(obj);

	}

	@Override
	public void deletar(ItemPatrimonio obj) {
		sessionFactory.getCurrentSession().delete(obj);

	}

	@Override
	public ItemPatrimonio buscarPeloId(Long id) {
		String hql = "FROM ItemPatrimonio i WHERE i.id = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);

		List<ItemPatrimonio> resultados = query.list();

		if (!resultados.isEmpty())
			return resultados.get(0);

		return null;
	}

	@Override
	public List<ItemPatrimonio> buscarTodos() {
		String hql = "FROM ItemPatrimonio u";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

}
