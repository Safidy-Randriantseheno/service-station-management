
CREATE TABLE IF NOT EXISTS Stock (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    id_product UUID NOT NULL,
    quantity DECIMAL(10, 2) NOT NULL,
    evaporation_rate DECIMAL(10, 2) NOT NULL,
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_product) REFERENCES Product(id)
);
CREATE INDEX IF NOT EXISTS stock_id_product_index ON Stock (id_product);
