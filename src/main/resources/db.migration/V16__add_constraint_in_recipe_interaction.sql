ALTER TABLE recipe_interaction
    ADD CONSTRAINT uc_interactions_recipe_user UNIQUE (recipe_id, user_id);
