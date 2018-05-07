package br.senai.sp.informatica.senaipatrimonio.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.UsuarioDAO;
import br.senai.sp.informatica.senaipatrimonio.model.Usuario;

@Primary
@Repository
public class UsuarioJPA implements UsuarioDAO {

	/*
	 * Obj de sessão com o db
	 */
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void persistir(Usuario obj) {
		sessionFactory.getCurrentSession().persist(obj);
	}

	@Override
	public void alterar(Usuario obj) {
		sessionFactory.getCurrentSession().update(obj);
	}

	@Override
	public void deletar(Usuario obj) {
		sessionFactory.getCurrentSession().delete(obj);

	}

	@Override
	public Usuario buscarPeloId(Long id) {
		String hql = "FROM Usuario u WHERE u.id = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);

		List<Usuario> resultados = query.list();

		if (!resultados.isEmpty())
			return resultados.get(0);

		return null;
	}

	@Override
	public List<Usuario> buscarTodos() {
		String hql = "FROM Usuario u";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public Usuario buscarPorEmail(String email) {
		String hql = "FROM Usuario u WHERE u.email = :email";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("email", email);

		List<Usuario> resultados = query.list();

		if (!resultados.isEmpty())
			return resultados.get(0);

		return null;

	}

	@Override
	public Usuario buscarPorEmailESenha(String email, String senha) {
		String hql = "FROM Usuario u WHERE u.email = :email and u.senha = :senha";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("email", email);
		query.setParameter("senha", senha);

		List<Usuario> resultados = query.list();

		if (!resultados.isEmpty())
			return resultados.get(0);

		return null;
	}

}
