create table authorities_db
(
    id        bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    authority varchar(255),
    user_id bigint not null
);