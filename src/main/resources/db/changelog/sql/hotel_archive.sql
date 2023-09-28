CREATE TABLE archived_hotels (
     id BIGSERIAL PRIMARY KEY,
     hotel_id BIGINT NOT NULL,
     name VARCHAR(64) NOT NULL,
     city VARCHAR(64) NOT NULL,
     archived_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE OR REPLACE FUNCTION archive_hotel()
    RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO archived_hotels (hotel_id, name, city, archived_at)
    VALUES (OLD.id, OLD.name, OLD.city, NOW());
    RETURN OLD;
END
$$ LANGUAGE plpgsql;

CREATE TRIGGER hotel_archive_trigger
    BEFORE DELETE ON hotels
    FOR EACH ROW
EXECUTE FUNCTION archive_hotel();