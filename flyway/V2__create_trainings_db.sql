create table trainings_db
(
    id        bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id bigint null,
    hours int not null ,
    minutes int not null ,
    seconds int not null ,
    kcal int,
    kilometers double precision,
    avg_speed double precision,
    description varchar(255),
    training_date date,
    training_time time
);