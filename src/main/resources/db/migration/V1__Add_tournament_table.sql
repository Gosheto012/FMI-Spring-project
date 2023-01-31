CREATE TABLE tournament
(
    tournament_id   SERIAL NOT NULL PRIMARY KEY,
    tournament_name VARCHAR(255),
    number_groups   INTEGER,
    description     VARCHAR(255)
);