ALTER TABLE recipe ADD CONSTRAINT fk_recipe_recipe_user FOREIGN KEY (author_id) REFERENCES recipe_user(id);
