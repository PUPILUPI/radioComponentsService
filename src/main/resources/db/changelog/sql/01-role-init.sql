CREATE TABLE IF NOT EXISTS public.user_role
(
    user_role   VARCHAR(32) PRIMARY KEY,
    description varchar(256) NOT NULL
);
INSERT INTO user_role (user_role, description)
VALUES ('ADMIN', 'Роль со всеми правами'),
       ('USER', 'Тестовая роль'),
       ('INDIVIDUAL_CUSTOMER', 'физ. лицо - покупатель'),
       ('LEGAL_CUSTOMER','юр.лицо - покупатель'),
       ('SUPPLIER', 'роль поставщика'),
       ('MANUFACTURER', 'роль производителя'),
       ('SUB_SELLER','роль работника продавца');
