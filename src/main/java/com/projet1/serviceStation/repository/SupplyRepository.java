package com.projet1.serviceStation.repository;

import com.projet1.serviceStation.DataBaseConnection;
import com.projet1.serviceStation.model.Supply;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
@Repository
public class SupplyRepository implements CrudOperation<Supply> {
    String userName = System.getenv("DB_USERNAME");
    String password = System.getenv("DB_PASSWORD");
    String databaseName = System.getenv("DB_NAME");
    DataBaseConnection dbConnection = new DataBaseConnection(userName, password, databaseName);
    Connection conn = dbConnection.getConnection();
    public Statement statement;
    @Override
    public List<Supply> findAll() {
        return null;
    }

    @Override
    public List<Supply> saveAll(List<Supply> toSave) {
        return null;
    }

    @Override
    public Supply save(Supply toSave) {
        return null;
    }

    @Override
    public Supply update(Supply toUpdate) {
        return null;
    }

    @Override
    public Supply delete(Supply toDelete) {
        return null;
    }
}
