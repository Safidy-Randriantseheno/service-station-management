CREATE TABLE IF NOT EXISTS Product (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    id_product UUID NOT NULL,
    quantity DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (id_product) REFERENCES Product(id)
);
CREATE INDEX IF NOT EXISTS product_id_index ON Product (id);