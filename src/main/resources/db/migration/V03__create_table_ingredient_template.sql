CREATE TABLE IF NOT EXISTS ingredient_template(
    id serial primary key,
    name varchar(250) not null,
    price double precision,
    unity_id integer references unity(id)
)