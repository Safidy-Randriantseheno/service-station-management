CREATE TABLE IF NOT EXISTS StockMovement (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    id_station UUID NOT NULL,
    id_product UUID NOT NULL,
    movement_date TIMESTAMP NOT NULL,
    movement_type VARCHAR(255) NOT NULL,
    quantity DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (id_station) REFERENCES Station(id),
    FOREIGN KEY (id_product) REFERENCES Product(id)
);

CREATE INDEX IF NOT EXISTS stock_movement_id_product_index ON StockMovement (id_product);
