CREATE TABLE IF NOT EXISTS recipe (
                                                  id SERIAL PRIMARY KEY,
                                                  name text,
                                                  minutes integer,
                                                  author_id bigint,
                                                  submitted timestamp,
                                                  description text
);
