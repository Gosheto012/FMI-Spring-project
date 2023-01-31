CREATE TABLE team
(
    id            SERIAL NOT NULL PRIMARY KEY,
    name          VARCHAR(255),
    tournament_id INTEGER,
    group_name    VARCHAR(255),
    team_status   VARCHAR(30),
    FOREIGN KEY (tournament_id) REFERENCES tournament (tournament_id)
);