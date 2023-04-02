CREATE TABLE IF NOT EXISTS recipe_tag (
                                          recipe_id BIGINT,
                                          tag_id BIGINT,
                                          PRIMARY KEY (recipe_id, tag_id),
                                          FOREIGN KEY (recipe_id) REFERENCES recipe(id),
                                          FOREIGN KEY (tag_id) REFERENCES tag(id)
);
