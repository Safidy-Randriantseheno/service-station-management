package com.projet1.serviceStation.repository;

import com.projet1.serviceStation.DataBaseConnection;
import com.projet1.serviceStation.model.Transaction;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;
import java.util.UUID;

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
        Connection conn = dbConnection.getConnection();


        if (!isStockSufficient(toSave.getIdProduct(), toSave.getQuantitySold())) {
            System.out.println("Stock insuffisant pour la transaction.");
            return null;
        }

        if (toSave.getQuantitySold().compareTo(BigDecimal.valueOf(200)) > 0) {
            System.out.println("Quantité demandée supérieure à la limite par vente.");
            return null;
        }

        String insertTransactionQuery = "INSERT INTO Transaction (id, id_station, id_product, quantity_sold, sale_amount, transaction_date) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(insertTransactionQuery)) {
            stmt.setObject(1, toSave.getId());
            stmt.setObject(2, toSave.getIdStation());
            stmt.setObject(3, toSave.getIdProduct());
            stmt.setBigDecimal(4, toSave.getQuantitySold());
            stmt.setBigDecimal(5, toSave.getSaleAmount());
            stmt.setTimestamp(6, java.sql.Timestamp.valueOf(toSave.getTransactionDate()));

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                
                updateStock(toSave.getIdProduct(), toSave.getQuantitySold());
                return toSave;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void updateStock(UUID idProduct, BigDecimal quantitySold) {
        Connection conn = dbConnection.getConnection();
        String updateStockQuery = "UPDATE Stock SET quantity = quantity - ? WHERE id_product = ?";

        try (PreparedStatement stmt = conn.prepareStatement(updateStockQuery)) {
            stmt.setBigDecimal(1, quantitySold);
            stmt.setObject(2, idProduct);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean isStockSufficient(UUID idProduct, BigDecimal quantitySold) {
        Connection conn = dbConnection.getConnection();
        String checkStockQuery = "SELECT quantity FROM Stock WHERE id_product = ?";

        try (PreparedStatement stmt = conn.prepareStatement(checkStockQuery)) {
            stmt.setObject(1, idProduct);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                BigDecimal currentStock = rs.getBigDecimal("quantity");
                return currentStock.compareTo(quantitySold) >= 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
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
