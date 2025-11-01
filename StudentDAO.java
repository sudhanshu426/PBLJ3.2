package com.example.app.dao;

import com.example.app.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class StudentDAO {
    @Autowired
    private SessionFactory factory;

    public void saveStudent(Student s) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(s);
            tx.commit();
        }
    }

    public List<Student> getAll() {
        try (Session session = factory.openSession()) {
            return session.createQuery("from Student", Student.class).list();
        }
    }
}
