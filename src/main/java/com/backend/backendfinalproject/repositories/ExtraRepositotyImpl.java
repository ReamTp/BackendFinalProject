package com.backend.backendfinalproject.repositories;

import com.backend.backendfinalproject.models.Extra;
import com.backend.backendfinalproject.models.request.Response;
import com.backend.backendfinalproject.repositories.interfaces.IExtraRepository;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ExtraRepositotyImpl implements IExtraRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Extra> getExtras() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("FROM Extra WHERE state = 1", Extra.class).getResultList();
    }

    @Override
    public Object getExtra(int id) {
        Session session = entityManager.unwrap(Session.class);
        List<Extra> extraList = session.createQuery("FROM Extra WHERE id = :id AND state = 1", Extra.class).setParameter("id", id).getResultList();
        return !extraList.isEmpty() ? extraList.get(0) : new Response("Extra not found", false);
    }

    @Override
    public Response register(Extra extra) {
        Extra newExtra = entityManager.merge(extra);
        Response response = new Response("Registration Failed", false);

        if(newExtra != null) {
            response.setMessage("Registration Success");
            response.setStatus(true);
        }

        return response;
    }

    @Override
    public Response update(Extra extra) {
        Session session = entityManager.unwrap(Session.class);
        Response response = new Response("Update Failed", false);

        Query query = session.createQuery("UPDATE Extra SET name = :name, price = :price, state = :state WHERE id = :id");
        query.setParameter("name", extra.getName());
        query.setParameter("price", extra.getPrice());
        query.setParameter("state", extra.isState());
        query.setParameter("id", extra.getId());

        if (query.executeUpdate() == 1) {
            response.setMessage("Update Success");
            response.setStatus(true);
        }

        return response;
    }
}
