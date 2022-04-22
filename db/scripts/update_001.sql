create table body (
                      id serial primary key,
                      type varchar (20)
);

create table brand (
                       id serial primary key,
                       type varchar (20)
);

create table car (
                     id serial primary key,
                     brand_id int references brand(id),
                     body_id int references body(id),
);

create table driver (
                        id serial primary key,
                        name varchar(15),
                        email varchar(40),
                        password varchar (20)
);

create table post (
                      id serial primary key,
                      description text,
                      car_id int references car(id),
                      driver_id int references driver(id),
                      photo byte,
                      sales boolean
);