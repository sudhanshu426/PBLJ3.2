package com.example.app.service;

import com.example.app.dao.AccountDAO;
import com.example.app.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    @Autowired
    private AccountDAO dao;

    @Transactional
    public void transferMoney(int fromId, int toId, double amount) {
        Account from = dao.findById(fromId);
        Account to = dao.findById(toId);

        if (from == null || to == null) throw new RuntimeException("Invalid Account");
        if (from.getBalance() < amount) throw new RuntimeException("Insufficient balance");

        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);

        dao.update(from);
        dao.update(to);

        System.out.println("Transferred " + amount + " from " + from.getName() + " to " + to.getName());
    }
}
