CREATE TABLE IF NOT EXISTS Supply (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    id_station UUID NOT NULL,
    product_type VARCHAR(255) NOT NULL,
    supply_date TIMESTAMP NOT NULL,
    quantity DOUBLE PRECISION NOT NULL
);

CREATE INDEX IF NOT EXISTS supply_id_station_index ON Supply (id_station);