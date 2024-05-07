CREATE TABLE IF NOT EXISTS StockReport (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    station_id UUID NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    product_quantities JSONB
);

CREATE INDEX IF NOT EXISTS stock_report_station_index ON StockReport (station_id);
