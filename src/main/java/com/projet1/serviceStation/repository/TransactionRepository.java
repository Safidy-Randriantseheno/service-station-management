package com.projet1.serviceStation.repository;

import com.projet1.serviceStation.DataBaseConnection;
import com.projet1.serviceStation.model.Transaction;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
@Repository
public class TransactionRepository implements CrudOperation<Transaction> {
    String userName = System.getenv("DB_USERNAME");
    String password = System.getenv("DB_PASSWORD");
    String databaseName = System.getenv("DB_NAME");
    DataBaseConnection dbConnection = new DataBaseConnection(userName, password, databaseName);
    Connection conn = dbConnection.getConnection();
    public Statement statement;
    @Override
    public List<Transaction> findAll() {
        return null;
    }

    @Override
    public List<Transaction> saveAll(List<Transaction> toSave) {
        return null;
    }

    @Override
    public Transaction save(Transaction toSave) {
        return null;
    }

    @Override
    public Transaction update(Transaction toUpdate) {
        return null;
    }

    @Override
    public Transaction delete(Transaction toDelete) {
        return null;
    }
}
