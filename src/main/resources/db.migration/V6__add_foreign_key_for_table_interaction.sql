ALTER TABLE recipe_interaction ADD CONSTRAINT fk_recipe_interaction_recipe_user FOREIGN KEY (user_id) REFERENCES recipe_user(id);
ALTER TABLE recipe_interaction ADD CONSTRAINT fk_recipe_interaction_recipe FOREIGN KEY (recipe_id) REFERENCES recipe(id);
