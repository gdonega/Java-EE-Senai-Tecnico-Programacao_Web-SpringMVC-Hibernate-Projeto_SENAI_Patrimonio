package br.senai.sp.informatica.senaipatrimonio.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.AmbienteDAO;
import br.senai.sp.informatica.senaipatrimonio.model.Ambiente;
import br.senai.sp.informatica.senaipatrimonio.model.Patrimonio;

@Primary
@Repository
public class AmbienteJPA implements AmbienteDAO{
	
	/*
	 * Obj de sessão com o db
	 */
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void persistir(Ambiente obj) {
		sessionFactory.getCurrentSession().persist(obj); 
		
	}

	@Override
	public void alterar(Ambiente obj) {
		sessionFactory.getCurrentSession().update(obj);
		
	}

	@Override
	public void deletar(Ambiente obj) {
		sessionFactory.getCurrentSession().delete(obj);
		
	}

	@Override
	public Ambiente buscarPeloId(Long id) {
		String hql = "FROM Ambiente a WHERE a.id = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);

		List<Ambiente> resultados = query.list();

		if (!resultados.isEmpty())
			return resultados.get(0);

		return null;
	}

	@Override
	public List<Ambiente> buscarTodos() {
		String hql = "FROM Ambiente u";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public Boolean jaEstaCadastrado(Ambiente ambiente) {
		String hql = "FROM Ambiente a WHERE a.nome = :nome";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("nome", ambiente.getNome());

		List<Ambiente> resultados = query.list();

		if (!resultados.isEmpty())
			return true;

		return false;
	}

}
