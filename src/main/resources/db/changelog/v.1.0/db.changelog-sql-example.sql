CREATE TABLE IF NOT EXISTS public."user"
(
    id           SERIAL PRIMARY KEY,
    surname      varchar(32)  NOT NULL,
    first_name   varchar(32)  NOT NULL,
    middle_name  varchar(32)  NOT NULL,
    email        varchar(256) NOT NULL UNIQUE,

    password     varchar(256) NOT NULL,
    phone_number varchar(32)  NOT NULL
);

