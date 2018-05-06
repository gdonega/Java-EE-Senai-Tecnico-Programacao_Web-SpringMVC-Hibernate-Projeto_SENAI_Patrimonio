package br.senai.sp.informatica.senaipatrimonio.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.PatrimonioDAO;
import br.senai.sp.informatica.senaipatrimonio.model.Patrimonio;
import br.senai.sp.informatica.senaipatrimonio.model.Usuario;

@Primary
@Repository
public class PatrimonioJPA implements PatrimonioDAO {

	/*
	 * Obj de sessão com o db
	 */
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void persistir(Patrimonio obj) {
		sessionFactory.getCurrentSession().persist(obj);
	}

	@Override
	public void alterar(Patrimonio obj) {
		sessionFactory.getCurrentSession().update(obj);
	}

	@Override
	public void deletar(Patrimonio obj) {
		sessionFactory.getCurrentSession().delete(obj);

	}

	@Override
	public Patrimonio buscarPeloId(Long id) {
		String hql = "FROM Patrimonio p WHERE p.id = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);

		List<Patrimonio> resultados = query.list();

		if (!resultados.isEmpty())
			return resultados.get(0);

		return null;
	}

	@Override
	public List<Patrimonio> buscarTodos() {
		String hql = "FROM Patrimonio p";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	

}
