CREATE TABLE IF NOT EXISTS recipe_ingredient (
                                          recipe_id BIGINT,
                                          ingredient_id BIGINT,
                                          PRIMARY KEY (recipe_id, ingredient_id),
                                          FOREIGN KEY (recipe_id) REFERENCES recipe(id),
                                          FOREIGN KEY (ingredient_id) REFERENCES ingredient(id)
);
