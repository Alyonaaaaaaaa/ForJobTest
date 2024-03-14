create table wallet
(
    id      uuid primary key,
    balance money not null
);


create table "user"
(
    id    uuid primary key,
    name  varchar(255) not null,
    email varchar(255) not null unique
);

create table "order"
(
    id      uuid primary key,
    product varchar(255)                NOT NULL,
    price   money                       NOT NULL,
    status  varchar(255)                NOT NULL,
    user_id uuid references "user" (id) NOT NULL
);

create table book
(
    id        uuid primary key,
    title     varchar(255)                NOT NULL,
    author_id uuid references author (id) NOT NULL
);

create table author
(
    id          uuid primary key,
    author_name varchar(255) UNIQUE NOT NULL
);