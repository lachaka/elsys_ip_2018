package org.elsys.ip.rest.repository;

import org.elsys.ip.rest.model.Test;
import org.elsys.ip.rest.utility.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class TestRepositoryHibernate {
    public List<Test> getTestList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List tests = session.createQuery("FROM Test").list();
        session.getTransaction().commit();

        return tests;
    }
}
