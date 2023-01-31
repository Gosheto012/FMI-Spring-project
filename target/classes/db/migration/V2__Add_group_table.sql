CREATE TABLE "group"
(
    group_name    VARCHAR(255),
    tournament_id INTEGER,
    is_finished   BOOLEAN,
    PRIMARY KEY (group_name, tournament_id),
    FOREIGN KEY (tournament_id) REFERENCES tournament (tournament_id)
);