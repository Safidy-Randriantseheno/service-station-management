package com.projet1.serviceStation.repository;

import com.projet1.serviceStation.DataBaseConnection;
import com.projet1.serviceStation.model.Product;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
@Repository
public class ProductRepository implements CrudOperation<Product> {
    String userName = System.getenv("DB_USERNAME");
    String password = System.getenv("DB_PASSWORD");
    String databaseName = System.getenv("DB_NAME");
    DataBaseConnection dbConnection = new DataBaseConnection(userName, password, databaseName);
    Connection conn = dbConnection.getConnection();
    public Statement statement;
    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public List<Product> saveAll(List<Product> toSave) {
        return null;
    }

    @Override
    public Product save(Product toSave) {
        return null;
    }

    @Override
    public Product update(Product toUpdate) {
        return null;
    }

    @Override
    public Product delete(Product toDelete) {
        return null;
    }
}
