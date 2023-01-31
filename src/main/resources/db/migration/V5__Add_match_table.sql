CREATE TABLE match
(
    id                       SERIAL NOT NULL PRIMARY KEY,
    first_team_name          VARCHAR(255),
    second_team_name         VARCHAR(255),
    first_team_scored_goals  INTEGER,
    second_team_scored_goals INTEGER,
    is_played                BOOLEAN,
    is_group_stage_match     BOOLEAN,
    group_in_which_is_played VARCHAR(255),
    tournament_id            INTEGER,
    FOREIGN KEY (tournament_id) REFERENCES tournament (tournament_id)
);