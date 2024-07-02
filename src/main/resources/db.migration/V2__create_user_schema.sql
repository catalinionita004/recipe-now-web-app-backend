CREATE TABLE IF NOT EXISTS recipe_user (
    id SERIAL PRIMARY KEY,
    first_name varchar(256),
    last_name varchar(256),
    email varchar(256),
    password varchar(256),
    role varchar(256),
    UNIQUE(email)
);
