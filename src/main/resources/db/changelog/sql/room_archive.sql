CREATE UNIQUE INDEX idx_archived_hotels_id ON archived_hotels (hotel_id);

CREATE TABLE archived_rooms (
     id bigserial primary key,
     room_id bigint not null,
     hotel_id bigint not null,
     room_number int NOT NULL ,
     price decimal(14,4),
     archived_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE UNIQUE INDEX idx_archived_rooms_id ON archived_rooms (room_id);

ALTER TABLE archived_rooms
    ADD CONSTRAINT fk_archived_rooms_hotel
        FOREIGN KEY (hotel_id)
            REFERENCES archived_hotels(hotel_id);

CREATE OR REPLACE FUNCTION archive_room()
    RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO archived_rooms (room_id, hotel_id, room_number, price, archived_at)
    VALUES (OLD.id, OLD.hotel_id, OLD.room_number, OLD.price, NOW());
    RETURN OLD;
END
$$ LANGUAGE plpgsql;

CREATE TRIGGER room_archive_trigger
    BEFORE DELETE ON rooms
    FOR EACH ROW
EXECUTE FUNCTION archive_room();