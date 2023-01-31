CREATE TABLE player
(
    id          SERIAL NOT NULL PRIMARY KEY,
    first_name  VARCHAR(255),
    second_name VARCHAR(255),
    team_id     INTEGER,
    rating      NUMERIC(2, 2),
    FOREIGN KEY (team_id) REFERENCES team (id)
);