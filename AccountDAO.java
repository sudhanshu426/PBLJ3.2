package com.example.app.dao;

import com.example.app.entity.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAO {
    @Autowired
    private SessionFactory factory;

    public Account findById(int id) {
        try (Session session = factory.openSession()) {
            return session.get(Account.class, id);
        }
    }

    public void update(Account account) {
        try (Session session = factory.openSession()) {
            var tx = session.beginTransaction();
            session.merge(account);
            tx.commit();
        }
    }
}
