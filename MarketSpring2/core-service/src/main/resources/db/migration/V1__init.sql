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
    price               numeric (8, 2),
    category_id         bigint reference categories (id)
    create_at           timestamp default current_timestamp,
    update_at           timestamp default current_timestamp
);

INSERT into products (title, price, category_id) values
('Milk', 80.00, 1), ('Bread', 25.00, 1, ('Cheese', 300.00, 1);


CREATE TABLE orders (
    id                  bigserial primary key,
    username            varchar (255) not null,
    total_price         numeric (8, 2) not null,
    address             varchar (255),
    phone               varchar (255),
    create_at           timestamp default current_timestamp,
    update_at           timestamp default current_timestamp
);

create table order_items (
    id                  bigserial primary key,
    product_id          bigint not null references products (id),
    order_id            bigint not null references orders (id),
    quantity            int not null,
    price_per_product   numeric (8, 2) not null,
    price               numeric (8, 2) not null,
    create_at           timestamp default current_timestamp,
    update_at           timestamp default current_timestamp
);




