CREATE OR REPLACE FUNCTION archive_room()
    RETURNS TRIGGER AS $$
BEGIN
    IF EXISTS (SELECT 1 FROM archived_hotels WHERE hotel_id = OLD.hotel_id) THEN
        INSERT INTO archived_rooms (room_id, hotel_id, room_number, price, archived_at)
        VALUES (OLD.id, OLD.hotel_id, OLD.room_number, OLD.price, NOW());
    END IF;
    RETURN OLD;
END;
$$ LANGUAGE plpgsql;