CREATE TABLE IF NOT EXISTS Station (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL
);
CREATE INDEX  IF NOT EXISTS station_name_index ON Station (name);

