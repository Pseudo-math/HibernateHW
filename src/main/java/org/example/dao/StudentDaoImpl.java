package org.example.dao;

import org.example.model.Student;
import org.example.model.University;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.example.util.HibernateUtil;
import org.hibernate.Transaction;
import org.example.dao.UniversityDaoImpl;

import java.util.List;



public class StudentDaoImpl implements StudentDao{
    @Override
    public void createStudent(Student student) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(student);
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

    @Override
    public Student getStudentById(Long id) {
        Session session = HibernateUtil.getSession();
        Student student = null;
        try {
            student = session.getReference(Student.class, 52);
            return student;
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return student;
    }

    @Override
    public List<Student> getAllStudent() {
        Session session = HibernateUtil.getSession();
        List<Student> allStudents = null;
        try {
            allStudents = session.createQuery("FROM students", Student.class).getResultList();
            return allStudents;
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return allStudents;
    }

    @Override
    public void updateStudent(Student student) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.merge(student);
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

    @Override
    public void deleteStudent(Student student) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.remove(student);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    public void assignStudentToUniversity(Long studentId, Long universityId) {
        Student student = getStudentById(studentId);
        University university = UniversityDaoImpl.getUniversityById(universityId);

        if (student != null && university != null) {
            student.setUniversity(university);
            updateStudent(student);
        }
    }

    public List<Student> getStudentsByUniversity(Long universityId) {
        University university = UniversityDaoImpl.getUniversityById(universityId);
        return university.getStudents();
    }
}
