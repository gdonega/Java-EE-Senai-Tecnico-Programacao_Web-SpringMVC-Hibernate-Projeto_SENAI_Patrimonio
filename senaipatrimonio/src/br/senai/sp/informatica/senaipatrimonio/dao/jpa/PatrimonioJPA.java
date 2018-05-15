package br.senai.sp.informatica.senaipatrimonio.dao.jpa;

import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.PatrimonioDAO;
import br.senai.sp.informatica.senaipatrimonio.model.Categoria;
import br.senai.sp.informatica.senaipatrimonio.model.Patrimonio;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Primary
@Repository
public class PatrimonioJPA implements PatrimonioDAO {

    /*
     * Obj de sess�o com o db
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

    @Override
    public List<Patrimonio> buscarPorCategoria(Categoria categoria) {
        String hql = "FROM Patrimonio p WHERE p.categoria.id = :categoriaId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("categoriaId", categoria.getId());

        return query.list();
    }

    @Override
    public Boolean jaEstaCadastrado(Patrimonio patrimonio) {
        String hql = "FROM Patrimonio p WHERE p.nome = :nome";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("nome", patrimonio.getNome());

        List<Patrimonio> resultados = query.list();

        if (!resultados.isEmpty())
            return true;

        return false;
    }

    @Override
    public Boolean existeOutroComEsseNome(Patrimonio patrimonio) {
        String hql = "FROM Patrimonio p WHERE p.nome = :nome and p.id != :id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("nome", patrimonio.getNome());
        query.setParameter("id", patrimonio.getId());

        List<Patrimonio> resultados = query.list();

        if (!resultados.isEmpty())
            return true;

        return false;
    }


}
