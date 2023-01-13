create table categories (
    id                  bigserialp rimary key,
    title               varchar(255) unique,
    create_at           timestamp default current_timestamp,
    update_at           timestamp default current_timestamp
);

CREATE TABLE products
(
    id                  bigserial primary key,
    title               varchar(255),
    category_id         bigint reference categories (id),
    price               int,
    create_at           timestamp default current_timestamp,
    update_at           timestamp default current_timestamp
);

INSERT into products (title, price, category_id) values
('Milk', 80, 1), ('Bread', 25, 1, ('Cheese', 300, 1);

create table users (
    id                  bigserial primary key,
    username            varchar(30) not null unique,
    password            varchar(80) not null,
  );

create table roles (
    id                  bigserial primary key,
    name                varchar(50) not null,
   );
CREATE TABLE users_roles (
    user_id             bigint not null,
    role_id             int not null,
    primary key (user_id, role_id),
);
CREATE TABLE products (
    products-id         bigserial,
    products_title      int not null,
    products_price      int not null
);
CREATE TABLE orders (
    user_id             bigserial,
    products_id         bigserial,
    products_title      int not null,
    products_price      int not null,
    orders_date         int not null
)

insert into roles (name)
values
('ROLE_USER'), ('ROLE_ADMIN');

insert into users (username, password)
values
('bob', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i');
('John', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i');

insert into users_roles (user_id, role_id)
values
(1, 1);

insert into products (products_title, products_price)
values
('title', 'price');

