CREATE TABLE IF NOT EXISTS recipe_interaction (
                                           id SERIAL PRIMARY KEY,
                                           user_id bigint,
                                           recipe_id bigint,
                                           date timestamp,
                                           rating integer,
                                           review TEXT
);
