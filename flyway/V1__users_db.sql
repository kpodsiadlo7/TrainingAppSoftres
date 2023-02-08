create table users_db
(
    id               bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username         varchar(100),
    password         varchar(255)
);