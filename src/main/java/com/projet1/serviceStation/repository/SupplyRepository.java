package com.projet1.serviceStation.repository;

import com.projet1.serviceStation.DataBaseConnection;
import com.projet1.serviceStation.model.Supply;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        List<Supply> supplies = new ArrayList<>();
        Connection conn = dbConnection.getConnection();

        String query = "SELECT * FROM Supply";

        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                UUID id = UUID.fromString(rs.getString("id"));
                UUID id_station = UUID.fromString(rs.getString("id_station"));
                String product_type = rs.getString("product_type");
                LocalDateTime supply_date = rs.getTimestamp("supply_date").toLocalDateTime();
                double quantity = rs.getDouble("quantity");

                Supply supply = new Supply(id, id_station, product_type, supply_date, quantity);
                supplies.add(supply);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return supplies;
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
        Connection conn = dbConnection.getConnection();
        UUID idStation = toUpdate.getIdStation();
        LocalDateTime supplyDate = toUpdate.getSupplyDate();
        double quantitySupplied = toUpdate.getQuantity();

        String updateStockQuery = "UPDATE Stock SET quantity = quantity + ? WHERE id_station = ? AND supply_date = ?";

        try (PreparedStatement updateStmt = conn.prepareStatement(updateStockQuery)) {
            updateStmt.setDouble(1, quantitySupplied);
            updateStmt.setObject(2, idStation);
            updateStmt.setTimestamp(3, java.sql.Timestamp.valueOf(supplyDate));

            int rowsAffected = updateStmt.executeUpdate();

            if (rowsAffected > 0) {
                return toUpdate;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public Supply delete(Supply toDelete) {
        return null;
    }
}
