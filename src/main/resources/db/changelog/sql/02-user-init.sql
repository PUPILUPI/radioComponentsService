CREATE TABLE IF NOT EXISTS public."user"
(
    user_id           BIGSERIAL PRIMARY KEY,
    user_role         VARCHAR(32)             NOT NULL REFERENCES user_role (user_role),
    surname           VARCHAR(32)             NOT NULL,
    first_name        VARCHAR(32)             NOT NULL,
    middle_name       VARCHAR(32)             NOT NULL,
    email             VARCHAR(256)            NOT NULL UNIQUE,
    password          VARCHAR(256)            NOT NULL,
    phone_number      VARCHAR(32)             NOT NULL,
    submit_flag       BOOLEAN   DEFAULT FALSE NOT NULL ,
    date_registration TIMESTAMP DEFAULT now() NOT NULL
);

INSERT INTO "user" (user_role, surname, first_name, middle_name, email, password, phone_number, submit_flag)
VALUES ('ADMIN', 'Иванов', 'Иван', 'Иванович', 'ivan.ivanov@example.com', 'password123', '+7 (123) 456-78-90', true),
       ('USER', 'Петров', 'Петр', 'Петрович', 'petr.petrov@example.com', 'qwerty456', '+7 (987) 654-32-10', true),
       ('INDIVIDUAL_CUSTOMER', 'Сидорова', 'Мария', 'Ивановна', 'maria.sidorova@example.com', '$2a$12$TCZnL2QT2Ano8TkR3aShPeIjMgPIAHHqKlt4sEWc5KdL7aRtP/LM2', '+7 (456) 789-01-23', true),
       ('LEGAL_CUSTOMER', 'Кузнецов', 'Алексей', 'Сергеевич', 'alexey.kuznetsov@example.com', '$2a$12$TCZnL2QT2Ano8TkR3aShPeIjMgPIAHHqKlt4sEWc5KdL7aRtP/LM2', '+7 (321) 098-76-54', true),
       ('SUPPLIER', 'Смирнова', 'Екатерина', 'Владимировна', 'ekaterina.smirnova@example.com', '$2a$12$TCZnL2QT2Ano8TkR3aShPeIjMgPIAHHqKlt4sEWc5KdL7aRtP/LM2', '+7 (876) 543-21-09', true),
       ('MANUFACTURER', 'Егоров', 'Егор', 'Егорович', 'bev6889@gmail.com', '$2a$12$TCZnL2QT2Ano8TkR3aShPeIjMgPIAHHqKlt4sEWc5KdL7aRtP/LM2', '+7 (876) 555-35-35', true);

