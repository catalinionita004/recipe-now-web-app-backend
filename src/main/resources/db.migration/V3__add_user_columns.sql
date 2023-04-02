ALTER TABLE recipe_user ADD username varchar(255) UNIQUE;
ALTER TABLE recipe_user ADD enabled int;
