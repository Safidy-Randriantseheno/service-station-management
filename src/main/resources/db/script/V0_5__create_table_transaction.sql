CREATE TABLE IF NOT EXISTS Transaction (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    id_station UUID NOT NULL,
    id_product UUID NOT NULL,
    quantity_sold DECIMAL(10, 2) NOT NULL,
    sale_amount DECIMAL(10, 2) NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_station) REFERENCES Station(id),
    FOREIGN KEY (id_product) REFERENCES Product(id)
);
CREATE INDEX IF NOT EXISTS transaction_id_station_index ON Transaction (id_station);
