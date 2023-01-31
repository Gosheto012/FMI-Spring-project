CREATE TABLE goal
(
    id            SERIAL NOT NULL PRIMARY KEY,
    player_id     INTEGER,
    minute_scored INTEGER,
    match_id      INTEGER,
    FOREIGN KEY (match_id) REFERENCES match (id)
);