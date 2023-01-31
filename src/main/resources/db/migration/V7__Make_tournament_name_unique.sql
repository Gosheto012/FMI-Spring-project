ALTER TABLE tournament
    ADD CONSTRAINT name_unique UNIQUE(tournament_name);