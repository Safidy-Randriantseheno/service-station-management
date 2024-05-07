package com.projet1.serviceStation.repository;

import com.projet1.serviceStation.DataBaseConnection;
import com.projet1.serviceStation.model.Station;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
@Repository
public class StationRepository implements CrudOperation<Station> {
    String userName = System.getenv("DB_USERNAME");
    String password = System.getenv("DB_PASSWORD");
    String databaseName = System.getenv("DB_NAME");
    DataBaseConnection dbConnection = new DataBaseConnection(userName, password, databaseName);
    Connection conn = dbConnection.getConnection();
    public Statement statement;
    @Override
    public List<Station> findAll() {
        return null;
    }

    @Override
    public List<Station> saveAll(List<Station> toSave) {
        return null;
    }

    @Override
    public Station save(Station toSave) {
        return null;
    }

    @Override
    public Station update(Station toUpdate) {
        return null;
    }

    @Override
    public Station delete(Station toDelete) {
        return null;
    }
}
