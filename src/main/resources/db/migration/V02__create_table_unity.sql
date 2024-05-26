CREATE TABLE IF NOT EXISTS unity(
    id serial primary key,
    name varchar(20) not null
);
insert into unity (name) values ('kg'),('pcs'),('litre');