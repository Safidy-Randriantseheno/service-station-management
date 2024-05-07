package com.projet1.serviceStation.repository;

import com.projet1.serviceStation.DataBaseConnection;
import com.projet1.serviceStation.model.stock.Stock;
import com.projet1.serviceStation.model.stock.StockMovement;
import com.projet1.serviceStation.model.stock.StockReport;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class StockRepository implements CrudOperation<Stock> {
    String userName = System.getenv("DB_USERNAME");
    String password = System.getenv("DB_PASSWORD");
    String databaseName = System.getenv("DB_NAME");
    DataBaseConnection dbConnection = new DataBaseConnection(userName, password, databaseName);
    Connection conn = dbConnection.getConnection();
    @Override
    public List<Stock> findAll() {
        List<Stock> stocks = new ArrayList<>();
        String query = "SELECT * FROM Stock";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                UUID id = UUID.fromString(rs.getString("id"));
                UUID idStation = UUID.fromString(rs.getString("id_station"));
                UUID idProduct = UUID.fromString(rs.getString("id_product"));
                BigDecimal quantity = rs.getBigDecimal("quantity");
                BigDecimal evaporationRate = rs.getBigDecimal("evaporation_rate");
                Timestamp lastUpdated = rs.getTimestamp("last_updated");

                Stock stock = new Stock(id, idStation, idProduct, quantity, evaporationRate, lastUpdated.toLocalDateTime());
                stocks.add(stock);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stocks;
    }
    public List<StockMovement> getStockMovementsForDateRange(UUID stationId, LocalDate startDate, LocalDate endDate) {
        List<StockMovement> movements = new ArrayList<>();
        String query = "SELECT * FROM StockMovement WHERE id_station = ? AND movement_date >= ? AND movement_date <= ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setObject(1, stationId);
            stmt.setDate(2, Date.valueOf(startDate));
            stmt.setDate(3, Date.valueOf(endDate));

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                UUID id = UUID.fromString(rs.getString("id"));
                UUID idStation = UUID.fromString(rs.getString("id_station"));
                UUID idProduct = UUID.fromString(rs.getString("id_product"));
                LocalDateTime movementDate = rs.getTimestamp("movement_date").toLocalDateTime();
                UUID productId = UUID.fromString(rs.getString("id_product"));
                String movementType = rs.getString("movement_type");
                BigDecimal quantity = rs.getBigDecimal("quantity");


                StockMovement movement = new StockMovement(id, idStation, idProduct,movementDate, movementType, quantity);
                movements.add(movement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movements;
    }
    public StockReport generateStockReport(UUID stationId, LocalDate startDate, LocalDate endDate) {
        List<StockMovement> movements = getStockMovementsForDateRange(stationId, startDate, endDate);
        List<Stock> currentStock = findAllByStationId(stationId);

        Map<UUID, BigDecimal> productQuantities = new HashMap<>();


        for (Stock stock : currentStock) {
            productQuantities.put(stock.getIdProduct(), BigDecimal.ZERO);
        }


        for (StockMovement movement : movements) {
            UUID productId = movement.getIdProduct();
            BigDecimal quantity = movement.getQuantity();

            if (movement.getMovementType().equals("Entrée")) {

                productQuantities.put(productId, productQuantities.get(productId).add(quantity));
            } else if (movement.getMovementType().equals("Sortie")) {

                productQuantities.put(productId, productQuantities.get(productId).subtract(quantity));
            }
        }

        for (UUID productId : productQuantities.keySet()) {
            BigDecimal currentQuantity = productQuantities.get(productId);
            BigDecimal evaporationRate = findEvaporationRateForProduct(productId);

            if (evaporationRate != null) {
                BigDecimal evaporatedQuantity = currentQuantity.multiply(evaporationRate).divide(BigDecimal.valueOf(100));
                BigDecimal remainingQuantity = currentQuantity.subtract(evaporatedQuantity);
                productQuantities.put(productId, remainingQuantity);
            }
        }

        StockReport stockReport = new StockReport();
        stockReport.setStationId(stationId);
        stockReport.setStartDate(startDate);
        stockReport.setEndDate(endDate);
        stockReport.setProductQuantities(productQuantities);

        return stockReport;
    }
    private BigDecimal findEvaporationRateForProduct(UUID productId) {
        BigDecimal evaporationRate = null;
        String query = "SELECT evaporation_rate FROM Product WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setObject(1, productId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                evaporationRate = rs.getBigDecimal("evaporation_rate");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return evaporationRate != null ? evaporationRate : BigDecimal.ZERO;
    }

    private List<Stock> findAllByStationId(UUID stationId) {
        List<Stock> stocks = new ArrayList<>();
        String query = "SELECT * FROM Stock WHERE id_station = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setObject(1, stationId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                UUID id = UUID.fromString(rs.getString("id"));
                UUID idProduct = UUID.fromString(rs.getString("id_product"));
                BigDecimal quantity = rs.getBigDecimal("quantity");
                BigDecimal evaporationRate = rs.getBigDecimal("evaporation_rate");
                Timestamp lastUpdated = rs.getTimestamp("last_updated");
                Stock stock = new Stock(id, stationId, idProduct, quantity, evaporationRate, lastUpdated.toLocalDateTime());
                stocks.add(stock);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stocks;
    }

    @Override
    public List<Stock> saveAll(List<Stock> toSave) {
        return null;
    }

    @Override
    public Stock save(Stock toSave) {
        return null;
    }

    @Override
    public Stock update(Stock toUpdate) {
        return null;
    }

    @Override
    public Stock delete(Stock toDelete) {
        return null;
    }
}
