CREATE TABLE IF NOT EXISTS recipe_nutrition (
                                                id SERIAL PRIMARY KEY,
                                                recipe_id BIGINT,
                                                calories DECIMAL(10, 2),
                                                fat DECIMAL(10, 2),
                                                saturated_fat DECIMAL(10, 2),
                                                carbohydrates DECIMAL(10, 2),
                                                fiber DECIMAL(10, 2),
                                                protein DECIMAL(10, 2),
                                                sodium  DECIMAL(10, 2),
                                                FOREIGN KEY (recipe_id) REFERENCES recipe(id)

);
