CREATE TABLE IF NOT EXISTS stock_movement(
    id serial primary key,
    id_resto integer references resto(id),
    quantity double precision not null,
    id_ingredient integer references ingredient_template(id),
    movement_type varchar(5) not null,
    unity_id integer references unity(id)
)