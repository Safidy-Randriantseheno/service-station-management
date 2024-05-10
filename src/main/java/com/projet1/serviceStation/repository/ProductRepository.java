package com.projet1.serviceStation.repository;

import com.projet1.serviceStation.DataBaseConnection;
import com.projet1.serviceStation.model.Product;
import com.projet1.serviceStation.model.ProductTemplate;
import com.projet1.serviceStation.model.Station;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM Product";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                ProductTemplate productTemplate = (ProductTemplate) rs.getObject("productTemplate");
                Station station = (Station) rs.getObject("station");
                Double quantity = rs.getDouble("quantity");

                Product product = new Product(productTemplate, station, quantity);
                productList.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }

    @Override
    public List<Product> saveAll(List<Product> toSave) {
        return null;
    }

    @Override
    public Product save(Product toSave) {
        String query = "INSERT INTO Product (productTemplate, idStation, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setObject(1, toSave.getProductTemplate());
            pstmt.setObject(1, toSave.getIdStation());
            pstmt.setDouble(1, toSave.getQuantity());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                return toSave;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Product update(Product toUpdate) {
        String query = "UPDATE Product quantity = quantity - (evaporation_rat * DATEDIFF(CURDATE(), lastUpdate))" + " WHERE evaporation_rate > 0";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setObject(1, toUpdate.getProductTemplate());
            pstmt.setDouble(2, toUpdate.getQuantity());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                return toUpdate;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Product delete(Product toDelete) {
        return null;

    }
}
