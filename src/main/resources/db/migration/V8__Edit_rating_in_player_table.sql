ALTER TABLE player
    DROP COLUMN rating;

ALTER TABLE player
    ADD COLUMN rating NUMERIC(4, 2);