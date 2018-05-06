package br.senai.sp.informatica.senaipatrimonio.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.CategoriaDAO;
import br.senai.sp.informatica.senaipatrimonio.model.Categoria;
import br.senai.sp.informatica.senaipatrimonio.model.Usuario;

@Primary
@Repository
public class CategoriaJPA implements CategoriaDAO {

	/*
	 * Obj de sessão com o db
	 */
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public void persistir(Categoria obj) {
		sessionFactory.getCurrentSession().persist(obj);

	}

	@Override
	public void alterar(Categoria obj) {
		sessionFactory.getCurrentSession().update(obj);

	}

	@Override
	public void deletar(Categoria obj) {
		sessionFactory.getCurrentSession().delete(obj);

	}

	@Override
	public Categoria buscarPeloId(Long id) {
		String hql = "FROM Categoria c WHERE c.id = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);

		List<Categoria> resultados = query.list();

		if (!resultados.isEmpty())
			return resultados.get(0);

		return null;
	}

	@Override
	public List<Categoria> buscarTodos() {
		String hql = "FROM Categoria c";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

}
