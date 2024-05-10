CREATE TABLE IF NOT EXISTS Product_Template (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    current_price DECIMAL(10, 2) NOT NULL
);
CREATE INDEX IF NOT EXISTS product_name_index ON Product_Template (name);
