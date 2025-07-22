package org.example.dao;

import org.example.model.Student;
import org.example.model.University;
import org.example.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UniversityDaoImpl {

    public static University getUniversityById(Long id) {
        Session session = HibernateUtil.getSession();
        University university = null;
        try {
            university = session.getReference(University.class, id);
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return university;
    }

    public void createUniversity(University university) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(university);
            transaction.commit();
        }
        catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }
}
