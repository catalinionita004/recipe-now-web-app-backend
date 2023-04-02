CREATE TABLE IF NOT EXISTS recipe_steps (
                                                id SERIAL PRIMARY KEY,
                                                recipe_id BIGINT,
                                                step_number int,
                                                step_description text,
                                                FOREIGN KEY (recipe_id) REFERENCES recipe(id)

);
