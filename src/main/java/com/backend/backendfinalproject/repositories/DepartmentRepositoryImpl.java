package com.backend.backendfinalproject.repositories;

import com.backend.backendfinalproject.models.Department;
import com.backend.backendfinalproject.models.Response;
import com.backend.backendfinalproject.repositories.interfaces.IDepartmentRepository;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class DepartmentRepositoryImpl implements IDepartmentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Department> getDepartments() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("FROM Department WHERE state = 1", Department.class).getResultList();
    }

    @Override
    public Object getDepartment(int id) {
        Session session = entityManager.unwrap(Session.class);
        List<Department> departmentsList = session.createQuery("FROM Department  WHERE state = 1 AND id = :id", Department.class).setParameter("id", id).getResultList();
        return !departmentsList.isEmpty() ? departmentsList.get(0) : new Response("Department not found", false);
    }

    @Override
    public Response register(Department department) {
        Department newDepartment = entityManager.merge(department);
        Response response = new Response();
        response.setStatus(false);

        if (newDepartment != null) {
            response.setMessage("Registration Success");
            response.setStatus(true);
        } else {
            response.setMessage("Registration Failed");
        }

        return response;
    }

    @Override
    public Response update(Department department) {
        Session session = entityManager.unwrap(Session.class);
        Response response = new Response();
        response.setStatus(false);

        Query query = session.createQuery("UPDATE Department SET name = :name, state = :state WHERE id = :id");
        query.setParameter("name", department.getName());
        query.setParameter("state", department.getState());
        query.setParameter("id", department.getId());

        if (query.executeUpdate() == 1) {
            response.setMessage("Update Success");
            response.setStatus(true);
        } else {
            response.setMessage("Update Failed");
        }

        return response;
    }
}
