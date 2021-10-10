package com.backend.backendfinalproject.repositories;

import com.backend.backendfinalproject.models.*;
import com.backend.backendfinalproject.repositories.interfaces.IDetailExtraBallotRepository;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class DetailExtraBallotImpl implements IDetailExtraBallotRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<DetailExtraBallot> getDetailExtraBallots() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("FROM DetailExtraBallot", DetailExtraBallot.class).getResultList();
    }

    @Override
    public Object getDetailExtraBallot(int id) {
        Session session = entityManager.unwrap(Session.class);
        List<DetailExtraBallot> detailExtraBallotList = session.createQuery("FROM DetailExtraBallot WHERE id = :id", DetailExtraBallot.class).setParameter("id", id).getResultList();
        return !detailExtraBallotList.isEmpty() ? detailExtraBallotList.get(0) : new Response("DetailExtraBallot Not Found", false);
    }

    @Override
    public Response register(DetailExtraBallot detailExtraBallot) {
        DetailExtraBallot newDetailExtraBallot = entityManager.merge(detailExtraBallot);
        Response response = new Response("Registration Failed", false);

        boolean registerSuccess = true;

        if (newDetailExtraBallot != null && registerSuccess) {
            response.setMessage("Registration Success");
            response.setStatus(true);
        }
        return response;
    }
}
