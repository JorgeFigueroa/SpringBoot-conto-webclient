package com.conto.services;

import com.conto.Repository.Entity.Transaction;

import java.util.List;

public interface TransactionService {

    public List<Transaction> findAll();

    public Transaction findById(int id);

    public Transaction save(Transaction transaction);

    public void delete(Transaction transaction);
}
