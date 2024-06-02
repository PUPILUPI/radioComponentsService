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
       ('INDIVIDUAL_CUSTOMER', 'Сидорова', 'Мария', 'Ивановна', 'maria.sidorova@example.com', '$2a$10$hUdKksBALtkypkdepSznYed8cx3Ehuq1YqxN8MLy43PkmNhOTW/1O', '+7 (456) 789-01-23', true),
       ('LEGAL_CUSTOMER', 'Белов', 'Егор', 'Владимирович', 'bev6889@gmail.com', '$2a$10$hUdKksBALtkypkdepSznYed8cx3Ehuq1YqxN8MLy43PkmNhOTW/1O', '+7 (321) 098-76-54', true),
       ('SUPPLIER', 'Смирнова', 'Екатерина', 'Владимировна', 'ekaterina.smirnova@example.com', '$2a$10$hUdKksBALtkypkdepSznYed8cx3Ehuq1YqxN8MLy43PkmNhOTW/1O', '+7 (876) 543-21-09', true),
       ('MANUFACTURER', 'Егоров', 'Егор', 'Егорович', 'belov@gmail.com', '$2a$10$hUdKksBALtkypkdepSznYed8cx3Ehuq1YqxN8MLy43PkmNhOTW/1O', '+7 (876) 555-35-35', true),
       ('SUPPLIER', 'Иванов', 'Иван', 'Иванович', 'ivaiva@example.com', '$2a$10$hUdKksBALtkypkdepSznYed8cx3Ehuq1YqxN8MLy43PkmNhOTW/1O', '+7 (876) 111-11-11', true),
       ('MANUFACTURER', 'Петров', 'Петр', 'Петрович', 'petropetro@example.com', '$2a$10$hUdKksBALtkypkdepSznYed8cx3Ehuq1YqxN8MLy43PkmNhOTW/1O', '+7 (876) 222-22-22', true),
       ('SUPPLIER', 'Сидоров', 'Алексей', 'Алексеевич', 'sidoroalex@example.com', '$2a$10$hUdKksBALtkypkdepSznYed8cx3Ehuq1YqxN8MLy43PkmNhOTW/1O', '+7 (876) 333-33-33', true),
       ('MANUFACTURER', 'Кузнецова', 'Мария', 'Ивановна', 'kuznet.maria@example.com', '$2a$10$hUdKksBALtkypkdepSznYed8cx3Ehuq1YqxN8MLy43PkmNhOTW/1O', '+7 (876) 444-44-44', true),
       ('SUPPLIER', 'Волкова', 'Ольга', 'Петровна', 'volkolga@example.com', '$2a$10$hUdKksBALtkypkdepSznYed8cx3Ehuq1YqxN8MLy43PkmNhOTW/1O', '+7 (876) 555-55-55', true),
       ('MANUFACTURER', 'Новиков', 'Николай', 'Александрович', 'novikovolay@example.com', '$2a$10$hUdKksBALtkypkdepSznYed8cx3Ehuq1YqxN8MLy43PkmNhOTW/1O', '+7 (876) 666-66-66', true),
       ('SUPPLIER', 'Федорова', 'Анна', 'Сергеевна', 'fedoroanna@example.com', '$2a$10$hUdKksBALtkypkdepSznYed8cx3Ehuq1YqxN8MLy43PkmNhOTW/1O', '+7 (876) 777-77-77', true),
       ('MANUFACTURER', 'Морозов', 'Андрей', 'Андреевич', 'morozovandrey@example.com', '$2a$10$hUdKksBALtkypkdepSznYed8cx3Ehuq1YqxN8MLy43PkmNhOTW/1O', '+7 (876) 888-88-88', true),
       ('SUPPLIER', 'Зайцева', 'Елена', 'Викторовна', 'zaitsevaelena@example.com', '$2a$10$hUdKksBALtkypkdepSznYed8cx3Ehuq1YqxN8MLy43PkmNhOTW/1O', '+7 (876) 999-99-99', true),
       ('MANUFACTURER', 'Борисов', 'Борис', 'Борисович', 'borisboris@example.com', '$2a$10$hUdKksBALtkypkdepSznYed8cx3Ehuq1YqxN8MLy43PkmNhOTW/1O', '+7 (876) 101-01-01', true);

