-- card_id - айди карты
-- user_id - айди физ. лица из таблицы user
-- masked_number - маскированный номер карты в формате 123456******1234
-- exp_date - дата истечения срока действия карты (хранится в виде 05/25)
-- auth_token - токен авторзации карты на стороне банка
CREATE TABLE IF NOT EXISTS public.card
(
    card_id       uuid PRIMARY KEY,
    user_id       BIGINT      NOT NULL REFERENCES public."user" (user_id),
    masked_number VARCHAR(20) NOT NULL,
    exp_date      VARCHAR(10) NOT NULL,
    auth_token    VARCHAR(32) NOT NULL
);

-- legal_entity - таблица для хранения юр.данных продавца (производителя, поставщика,)
-- user_id - айди юр. лица из таблицы user
-- bank_name - наименование банка получателя (обяз. поле)
-- rc_bic - БИК банка (обяз. поле)
-- сorr_acc - корреспондентский счет банка (обяз. поле)
-- company_name - наименование организации (обяз. поле, в случае ИП указывается
-- полное имя "ИП Белов Егор Владимирович")  1
-- zip - индекс юр.адреса - (обяз. поле, но не для ИП) 1
-- city - физ. расположение продавца (обяз. поле) 1
-- business_address - юр. адрес продавца полностью (обяз. поле, не для ИП, г. Москва, Каширское шоссе, д55,к5) 1
-- inn - инн продавца (обяз. поле) 1
-- kpp - кпп продавца (обяз. поле, но не для ИП) 1
-- acc - расчетный счет продавца (куда пересылать деньги, обяз. поле)
-- phone_number - номер телефона для связи 1
-- email - почта для связи 1
-- flag_w_with_ind - флаг работают ли они с физ. лицами 1
-- flag_manufacturer - является ли прозводителем - проставляется исходя из роли пользователя автоматически  1
-- rating - рейтинг 1
-- api_address - адрес апи продавца - обязательное поле для всех, кроме производителя.
-- agreement - ссылка, по которой будет лежать подписанный договор
-- agreement_flag - флаг подтверждение что договор заполнен корректно и продавец может
-- осуществлять продажу
CREATE TABLE IF NOT EXISTS public.legal_entity
(
    seller_id         BIGINT PRIMARY KEY REFERENCES public."user" (user_id),
    bank_name         VARCHAR(128),
    rc_bic            VARCHAR(9),
    сorr_acc          VARCHAR(20),
    company_name      VARCHAR(256),
    zip               VARCHAR(6),
    city              VARCHAR(128),
    business_address  VARCHAR(256),
    inn               VARCHAR(12),
    kpp               VARCHAR(9),
    acc               VARCHAR(20),
    phone_number      VARCHAR(32),
    email             VARCHAR(64),
    flag_w_with_ind   BOOLEAN,
    flag_manufacturer BOOLEAN,
    rating            NUMERIC(3, 2),
    api_address       VARCHAR(256),
    agreement         VARCHAR(256),
    agreement_flag    BOOLEAN DEFAULT FALSE
);

INSERT INTO public.legal_entity (seller_id, bank_name, rc_bic, сorr_acc, company_name,
                                 zip, city, business_address, inn, kpp, acc, phone_number,
                                 email, flag_w_with_ind, flag_manufacturer, rating, api_address,
                                 agreement_flag)
VALUES (5, 'АО Тинькофф', '044525974', '30101810145250000974','OOO Электротехника','124482', 'Москва',
        'город Москва, город Зеленоград, Савёлкинский пр-д, д.4, офис 511', '7735516193', '773501001',
        '40702810680060657001','+79001234567', 'electrotechnica@mail.ru', true, false, 4.89,
        'http://localhost:8081/api1', true),
       (6, 'АО Тинькофф', '044525974', '30101810145250000974','OOO Техэнерго','124482', 'Москва',
        'город Москва, город Зеленоград, Березовая аллея, д.5, офис 512', '7735516193', '773501001',
        '40702810680060657001','+79001234567', 'electrotechnica@mail.ru', false, true, 4.79,
        'http://localhost:8082/api2', true),
       (7, 'АО Тинькофф', '044525974', '30101810145250000974','OOO ЧипФайнд','124482', 'Санкт-Петербург',
        'Санкт-Петербург, Невский проспект, д.22, офис 101', '7735516193', '773501001',
        '40702810680060657001','+79001234567', 'electrotechnica@mail.ru', false, false, 4.69,
        'http://localhost:8083/api3', true),
       (8, 'АО Тинькофф', '044525974', '30101810145250000974','OOO Гига','124482', 'Санкт-Петербург',
        'Санкт-Петербург, Литейный проспект, д.15, офис 102', '7735516193', '773501001',
        '40702810680060657001','+79001234567', 'electrotechnica@mail.ru', false, true, 4.59,
        null, true),
       (9, 'АО Тинькофф', '044525974', '30101810145250000974','OOO Тера','124482', 'Санкт-Петербург',
        'Санкт-Петербург, Средний проспект, д.8, офис 103', '7735516193', '773501001',
        '40702810680060657001','+79001234567', 'electrotechnica@mail.ru', false, false, 4.59,
        null, true),
       (10, 'ПАО Сбербанк', '044525974', '30101810145250000974','OOO Мили','124482', 'Казань',
        'Казань, ул. Баумана, д.35, офис 201', '7735516193', '773501001',
        '40702810680060657001','+79001234567', 'electrotechnica@mail.ru', false, true, 4.60,
        null, true),
       (11, 'ПАО Сбербанк', '044525974', '30101810145250000974','OOO Кило','124482', 'Казань',
        'Казань, проспект Ямашева, д.10, офис 202', '7735516193', '773501001',
        '40702810680060657001','+79001234567', 'electrotechnica@mail.ru', false, false, 4.50,
        null, true),
       (12, 'ПАО Сбербанк', '044525974', '30101810145250000974','OOO Микро','124482', 'Казань',
        'Казань, ул. Карла Маркса, д.50, офис 203', '7735516193', '773501001',
        '40702810680060657001','+79001234567', 'electrotechnica@mail.ru', false, true, 4.20,
        null, true),
       (13, 'ПАО Сбербанк', '044525974', '30101810145250000974','OOO Пико','124482', 'Москва',
        'Москва, Тверская ул., д.10, офис 410', '7735516193', '773501001',
        '40702810680060657001','+79001234567', 'electrotechnica@mail.ru', true, false, 4.13,
        null, true),
       (14, 'ПАО Сбербанк', '044525974', '30101810145250000974','OOO Нано','124482', 'Москва',
        'Москва, Арбат, д.25, офис 502', '7735516193', '773501001',
        '40702810680060657001','+79001234567', 'electrotechnica@mail.ru', true, true, 4.08,
        null, true),
       (15, 'ПАО Сбербанк', '044525974', '30101810145250000974','OOO Портехника','124482', 'Москва',
        'Москва, Новая Басманная ул., д.14, офис 215', '7735516193', '773501001',
        '40702810680060657001','+79001234567', 'electrotechnica@mail.ru', true, false, 4.19,
        null, true),
       (16, 'ПАО Сбербанк', '044525974', '30101810145250000974','OOO Радио','124482', 'Москва',
        'Москва, Ленинский проспект, д.38, офис 707', '7735516193', '773501001',
        '40702810680060657001','+79001234567', 'electrotechnica@mail.ru', true, true, 3.90,
        null, true);

-- consumer_entity - таблица для хранения данных, которые будут использованы при выставлении счета на
-- оплату (и в запросах продавцу) если вдруг покупатель хочет чтобы счет выставлялся на юр. лицо, и вместо ФИО, была указана
-- юридическая информация о покупателе.
-- consumer_id - айди покупателя из таблицы user
-- company_name - имя компании или ип
-- zip, city - индекс и город соответсвенно
-- business_address - юр.адрес
-- inn, kpp - ИНН и КПП
-- phone_number, email - номер телефона и почта, куда отправлять ссылку для получения счета
CREATE TABLE IF NOT EXISTS public.consumer_entity
(
    consumer_id      BIGINT PRIMARY KEY REFERENCES public."user" (user_id),
    company_name     VARCHAR(256),
    zip              VARCHAR(6),
    city             VARCHAR(128),
    business_address VARCHAR(256),
    inn              VARCHAR(12),
    kpp              VARCHAR(9),
    phone_number     VARCHAR(32),
    email            VARCHAR(64)
);
INSERT INTO public.consumer_entity(consumer_id, company_name, zip, city, business_address, inn, kpp, phone_number,
                                   email)
VALUES (3, 'Сидорова Мария', '124480', 'Зеленоград', 'г. Зеленоград, к. 1213, кв. 160', null, null, '8(916)555-35-35',
        'bev6889@test.ru'),
       (4, 'ООО МАКС ПРО', '115211', 'Москва', 'г. Москва, Кашрское шоссе, 57к6, кв. 216', '7731347089', '773101001',
        '8(916)123-45-67',
        'begemot6889@test.ru');

-- delivery_address - адреса доставок клиента
-- address_id - айди адреса
-- user_id - айди клиента
-- name - название для адреса
-- city, zip - город и индекс адреса (необзательное поле для клиента)
-- address - полный адрес клиента, включая индекс
CREATE TABLE IF NOT EXISTS public.delivery_address
(
    address_id uuid PRIMARY KEY,
    user_id    BIGINT       NOT NULL REFERENCES public."user" (user_id),
    name       VARCHAR(64)  NOT NULL,
    city       VARCHAR(64),
    zip        VARCHAR(6),
    address    VARCHAR(256) NOT NULL
);

-- sellers_list - список избранных/находящихся в черном списке продавцов
-- consumer_id - айди покупателя
-- seller_id - айди продавца
-- favorite_flag - флаг - находится ли он в списке избранных продавцов у покупателя
-- blacklist_flag - флаг - находится ли он в черном списке у покупателя
CREATE TABLE IF NOT EXISTS public.sellers_list
(
    consumer_id    BIGINT REFERENCES public."user" (user_id),
    seller_id      BIGINT REFERENCES public.legal_entity (seller_id),
    favorite_flag  BOOLEAN NOT NULL DEFAULT FALSE,
    blacklist_flag BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT primary_key PRIMARY KEY (consumer_id, seller_id)
);

-- goods_basket - корзина товаров покупателя
-- consumer_id - айди покупателя - чья корзина
-- product_id - айди товара в корзине
-- seller_id - айди продавца товара
-- product_name - наименование товара
-- quantity - кол-во товара
-- price - стоимость за единицу.
-- данные по количеству можно полноценно менять только в момент добавления, в корзине - только
-- уменьшать, при добавлении товара происходит поиск по корзине по наименованию товара и id продавца если есть
-- то данные по кол-ву затираются
CREATE TABLE IF NOT EXISTS public.goods_basket
(
    product_id   uuid PRIMARY KEY,
    consumer_id  BIGINT         NOT NULL REFERENCES public."user" (user_id),
    seller_id    BIGINT         NOT NULL REFERENCES public."user" (user_id),
    product_name VARCHAR(128)   NOT NULL,
    quantity     INTEGER        NOT NULL,
    price        NUMERIC(10, 2) NOT NULL
);

-- request_basket - корзина запросов покупателя
-- consumer_id - -- consumer_id - айди покупателя - чья корзина
-- product_id - айди товара в корзине
-- seller_id - айди продавца товара
-- product_name - наименование товара
-- quantity - кол-во товара
-- price - стоимость за единицу.
-- message - пожелания по количеству, срокам, иные детали
CREATE TABLE IF NOT EXISTS public.request_basket
(
    product_id   uuid PRIMARY KEY,
    consumer_id  BIGINT       NOT NULL REFERENCES public."user" (user_id),
    seller_id    BIGINT       NOT NULL REFERENCES public."user" (user_id),
    product_name VARCHAR(128) NOT NULL,
    quantity     INTEGER      NOT NULL,
    price        NUMERIC(10, 2),
    message      VARCHAR(256)
);
-- order_status - таблица со статусами заказа
-- order_status_id - айдишник статуса
-- order_status - его короткое название - значение enum
-- description - описание статуса
CREATE TABLE IF NOT EXISTS public.order_status
(
    order_status_id SERIAL PRIMARY KEY,
    order_status    VARCHAR(128) NOT NULL,
    description     VARCHAR(512) NOT NULL
);

-- client_order - таблица для хранения заказов клиента
-- client_order_id - уникальный номер заказа в системе,
-- который используется для генерации payment_gate_uuid который выдает платежный шлюз
-- payment_number - номер счета на оплату, который используется для генерации invoice_uuid
-- consumer_id - айди покупателя
-- total_sum - общая сумма заказа
-- client_status - общий статус заказа
-- delivery_method - способ доставки (самовывоз пвз, напрямую через продавца, через наш сервис)
-- pickup_address - адрес пвз/доставки
-- payment_invoice - ссылка на счет на оплату
-- payment_gate_uuid - uuid заказа в платежной системе сгенерированный платежным шлюзом при оплате онлайн
-- invoice_uuid - uuid сгенерированный системой выставления счета при создании счета
-- creation_date - дата создания
-- payment_date - дата оплаты (только для оплаченных онлайн)
-- receive_date - дата получения заказа
-- paid_flag - флаг оплачен заказ или нет
CREATE TABLE IF NOT EXISTS public.client_order
(
    client_order_id   uuid PRIMARY KEY,
    payment_number    VARCHAR(15),
    consumer_id       BIGINT         NOT NULL REFERENCES public."user" (user_id),
    total_sum         NUMERIC(10, 2) NOT NULL,
    client_status     INTEGER        NOT NULL REFERENCES order_status (order_status_id),
    delivery_method   VARCHAR(64),
    pickup_address    VARCHAR(128),
    payment_invoice   VARCHAR(128),
    payment_gate_uuid uuid,
    invoice_uuid      uuid,
    creation_date     TIMESTAMP      NOT NULL DEFAULT now(),
    payment_date      TIMESTAMP,
    receive_date      TIMESTAMP,
    paid_flag         BOOLEAN        NOT NULL DEFAULT FALSE
);

-- review - таблица с отзывами
-- review_id - айди отзыва
-- consumer_id - айди покупателя
-- message - отзыв
-- creation_date - дата создания отзыва
-- seller_id айди продавца
-- score - оценка
CREATE TABLE IF NOT EXISTS public.review
(
    review_id     uuid PRIMARY KEY,
    consumer_id   BIGINT       NOT NULL REFERENCES public."user" (user_id),
    message       VARCHAR(512) NOT NULL,
    creation_date TIMESTAMP    NOT NULL DEFAULT now(),
    seller_id     BIGINT       NOT NULL REFERENCES public."user" (user_id),
    score         INTEGER      NOT NULL CHECK (score in (1, 2, 3, 4, 5))
);

-- Создаем функцию для пересчета оценки продавца
CREATE OR REPLACE FUNCTION update_seller_rating()
    RETURNS TRIGGER AS
$$
BEGIN
    UPDATE public.legal_entity
    SET rating = (SELECT AVG(score)
                  FROM public.review
                  WHERE seller_id = NEW.seller_id)
    WHERE seller_id = NEW.seller_id;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Создаем триггер, который будет вызывать функцию при добавлении нового отзыва
CREATE TRIGGER update_rating_trigger
    AFTER INSERT
    ON public.review
    FOR EACH ROW
EXECUTE FUNCTION update_seller_rating();

-- seller_order - таблица для отображения заказа продавцу
-- seller_order_id - айди заказа айди заказа у продавца
-- client_order_id - номер заказа у покупателя
-- seller_id - айди продавца
-- creation_date - дата создания заказа
-- seller_status - статус заказа продавца внутри заказа покупателя
-- total_sum - общая сумма заказа
-- tracking_number - трек номер
-- delivery_service - сервис доставки
CREATE TABLE IF NOT EXISTS public.seller_order
(
    seller_order_id  uuid PRIMARY KEY,
    client_order_id  uuid           NOT NULL REFERENCES public.client_order (client_order_id),
    seller_id        BIGINT         NOT NULL REFERENCES public."user" (user_id),
    creation_date    TIMESTAMP      NOT NULL DEFAULT now(),
    seller_status    INTEGER        NOT NULL REFERENCES order_status (order_status_id),
    total_sum        NUMERIC(10, 2) NOT NULL,
    tracking_number  VARCHAR(64),
    delivery_service VARCHAR(64),
    review_id        uuid REFERENCES public.review (review_id)
);

-- order_history - таблица с историей заказа
-- order_id - айди заказа из таблицы client_order
-- change_date - дата изменнеия статуса
-- new_status - новый статус
CREATE TABLE IF NOT EXISTS public.order_history
(
    order_id    uuid      NOT NULL REFERENCES seller_order (seller_order_id),
    change_date TIMESTAMP NOT NULL DEFAULT now(),
    new_status  INTEGER   NOT NULL REFERENCES order_status (order_status_id)
);

--триггер, который заполняет историю заказа при изменении статуса
CREATE OR REPLACE FUNCTION update_order_history()
    RETURNS TRIGGER AS
$$
BEGIN
    IF TG_OP = 'INSERT' THEN
        INSERT INTO order_history (order_id, new_status)
        VALUES (NEW.seller_order_id, NEW.seller_status);
    ELSIF TG_OP = 'UPDATE' THEN
        INSERT INTO order_history (order_id, new_status)
        VALUES (NEW.seller_order_id, NEW.seller_status);
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER order_status_trigger
    AFTER INSERT OR UPDATE
    ON seller_order
    FOR EACH ROW
EXECUTE FUNCTION update_order_history();



-- order_item - таблица содержимого заказа -
-- одна запись - один товар в заказе у продавца и покупателя
-- product_id - айди продукта, присваивается в момент добавленя товара в корзину
-- и когда берется из товара в корзине в процессе создания заказа,
-- чтобы лишний раз не генерировать
-- seller_order_id - айди заказа у продавца, к которому отнесен товар
-- seller_id - айди продавца, кому принадлежит заказ
-- product_name - наименование товара
-- quantity - количество товара
-- price - цена товара за единицу
CREATE TABLE IF NOT EXISTS public.order_item
(
    product_id      uuid PRIMARY KEY,
    seller_order_id uuid           NOT NULL REFERENCES public.seller_order (seller_order_id),
    seller_id       BIGINT         NOT NULL REFERENCES public."user" (user_id),
    product_name    VARCHAR(128)   NOT NULL,
    quantity        INTEGER        NOT NULL,
    price           NUMERIC(10, 2) NOT NULL
);

-- client_request - таблица с выполненными запросами
-- client_request_id - уникальный номер запроса для отображения клиенту
-- consumer_id - айди покупателя, сделавшего запрос
-- creation_date - дата создания запроса
CREATE TABLE IF NOT EXISTS public.client_request
(
    client_request_id uuid PRIMARY KEY,
    consumer_id       BIGINT    NOT NULL REFERENCES public."user" (user_id),
    creation_date     TIMESTAMP NOT NULL DEFAULT now()
);

-- seller_request - таблица для отображения запроса у продавца
-- seller_request_id - уникальный номер запроса для отображения продавцу
-- client_request_id - номер запроса покупателя
-- seller_id - айди пордавца
-- status - статус запроса
-- creation_date - дата создания
CREATE TABLE IF NOT EXISTS public.seller_request
(
    seller_request_id uuid PRIMARY KEY,
    client_request_id uuid        NOT NULL REFERENCES public.client_request (client_request_id),
    seller_id         BIGINT      NOT NULL REFERENCES public."user" (user_id),
    status            VARCHAR(64) NOT NULL CHECK (status IN ('ACTIVE', 'VIEWED_BY_SELLER')) DEFAULT 'ACTIVE',
    creation_date     TIMESTAMP   NOT NULL                                                  DEFAULT now()
);

-- request_item - таблица с товарами в заказе
-- product_id - айди товара, передается из товара в корзине запросрв, чтобы не генерирвоать
-- лишний раз
-- seller_order_id - айди запроса продавца, к которому относится товар
-- seller_id - айди продавца
-- product_name - название товара
-- quantity - количество
-- price - стоимость за единицу
-- message - пожелания по количеству, срокам, иные детали
CREATE TABLE IF NOT EXISTS public.request_item
(
    product_id      uuid PRIMARY KEY,
    seller_order_id uuid           NOT NULL REFERENCES public.seller_order (seller_order_id),
    seller_id       BIGINT         NOT NULL REFERENCES public."user" (user_id),
    product_name    VARCHAR(128)   NOT NULL,
    quantity        INTEGER        NOT NULL,
    price           NUMERIC(10, 2) NOT NULL,
    message         VARCHAR(256)
);

-- sub_seller - таблица для помошников продавца - подпользователь
-- login - логин подпользователя
-- password - пароль
-- user_description - название - описание поользователч
-- user_role - роль всегда 'SUB_SELLER'
-- user_id - айди пользователя, к которому принадлежит сотрудник
CREATE TABLE IF NOT EXISTS public.sub_seller
(
    sub_seller_id    BIGSERIAL PRIMARY KEY,
    login            VARCHAR(32)  NOT NULL UNIQUE,
    password         VARCHAR(256) NOT NULL,
    user_description VARCHAR(64)  NOT NULL,
    user_role        VARCHAR(32)  NOT NULL REFERENCES user_role (user_role) DEFAULT ('SUB_SELLER'),
    user_id          BIGINT       NOT NULL REFERENCES public."user" (user_id)
);

-- order_return_status - таблица со статусами возврата
-- order_return_status_id - айди статуса возврата
-- order_return_status - краткая форма статуса
-- description - подробное описание статуса
CREATE TABLE IF NOT EXISTS public.order_return_status
(
    order_return_status_id SERIAL PRIMARY KEY,
    order_return_status    VARCHAR(128) NOT NULL,
    description            VARCHAR(512) NOT NULL
);

-- order_return - таблица с основной информации о возврате
-- order_return_id - айди возврата
-- consumer_id - айдм покупателя, оформившего возврат
-- client_order_id - айди заказа покупателя, к которому относится возврат
-- cause - причина возврата
-- creation_date - дата создания возврата
-- return_sum - сумма возврата
-- order_return_status - статус возврата
CREATE TABLE IF NOT EXISTS public.order_return
(
    order_return_id     uuid PRIMARY KEY,
    consumer_id         BIGINT         NOT NULL REFERENCES public."user" (user_id),
    client_order_id     uuid           NOT NULL REFERENCES public.client_order (client_order_id),
    cause               VARCHAR(512)   NOT NULL,
    creation_date       TIMESTAMP      NOT NULL DEFAULT now(),
    return_sum          NUMERIC(10, 2) NOT NULL,
    order_return_status INTEGER        NOT NULL REFERENCES public.order_return_status (order_return_status_id)
);

-- order_return_item - товар, содержащийся в возврате
-- order_return_item_id айди товарара в возврате
-- order_return_id - айди возврата
-- seller_order_id - айди заказа продавца, по товару из которого осуществляется возврат
-- product_name - наименование товара
-- quantity - количество
-- price - цена
CREATE TABLE IF NOT EXISTS public.order_return_item
(
    order_return_item_id uuid PRIMARY KEY,
    order_return_id      uuid           NOT NULL REFERENCES public.order_return (order_return_id),
    seller_order_id      uuid           NOT NULL REFERENCES public.seller_order (seller_order_id),
    seller_id            BIGINT         NOT NULL REFERENCES public."user" (user_id),
    product_name         VARCHAR(128)   NOT NULL,
    quantity             INTEGER        NOT NULL,
    price                NUMERIC(10, 2) NOT NULL
);

-- dispute_status - таблица со статусами спора
-- dispute_status_id - айди статуса
-- dispute_status - краткое имя статуса
-- description - описание статуса
CREATE TABLE IF NOT EXISTS public.dispute_status
(
    dispute_status_id SERIAL PRIMARY KEY,
    dispute_status    VARCHAR(128) NOT NULL,
    description       VARCHAR(512) NOT NULL
);

-- dispute - спор
-- dispute_id - айди спора
-- seller_order_id - заказ продавца, по которому ведется спор
-- client_order_id - заказ покупателя
-- creation_date - дата создания
-- dispute_status - статус спора
-- cause - причина спора
CREATE TABLE IF NOT EXISTS public.dispute
(
    dispute_id      uuid PRIMARY KEY,
    seller_order_id uuid         NOT NULL REFERENCES public.seller_order (seller_order_id),
    client_order_id uuid         NOT NULL REFERENCES public.client_order (client_order_id),
    creation_date   TIMESTAMP    NOT NULL DEFAULT now(),
    dispute_status  INTEGER      NOT NULL REFERENCES public.dispute_status (dispute_status_id),
    cause           VARCHAR(512) NOT NULL
);

-- chat - чат (по спору и без)
-- chat_id - айди чата
-- dispute_id - айди спора, к которому относится чат
-- consumer_id - покупатель
-- seller_id - продавец
-- creation_date - дата создания
CREATE TABLE IF NOT EXISTS public.chat
(
    chat_id       uuid PRIMARY KEY,
    dispute_id    uuid REFERENCES public.dispute (dispute_id),
    consumer_id   BIGINT    NOT NULL REFERENCES public."user" (user_id),
    seller_id     BIGINT    NOT NULL REFERENCES public."user" (user_id),
    creation_date TIMESTAMP NOT NULL DEFAULT now()
);

-- chat_message - сообщение в чате
-- chat_message_id - айди сообщения
-- chat_messages_text - сообщение в чате
-- chat_id - чат, к которому относится сообщение
-- user_id - отправитель сообщения
-- to_user_id - получатель сообщения
-- creation_date - время отправки
CREATE TABLE IF NOT EXISTS public.chat_message
(
    chat_message_id    uuid PRIMARY KEY,
    chat_messages_text VARCHAR(512) NOT NULL,
    chat_id            uuid         NOT NULL REFERENCES public.chat (chat_id),
    user_id            BIGINT       NOT NULL REFERENCES public."user" (user_id),
    to_user_id         BIGINT       NOT NULL REFERENCES public."user" (user_id),
    creation_date      TIMESTAMP    NOT NULL DEFAULT now()
);

-- resolution - решение спора
-- resolution_id - айди решения
-- resolution_offer - предложение по решению спора
-- return_sum - сумма возврата
-- resolution_status - статус решения
CREATE TABLE IF NOT EXISTS public.resolution
(
    resolution_id     uuid PRIMARY KEY REFERENCES public.dispute (dispute_id),
    resolution_offer  VARCHAR(512)   NOT NULL,
    return_sum        NUMERIC(10, 2) NOT NULL,
    resolution_status VARCHAR(128)   NOT NULL
);

-- dispute_item - товар в споре
-- dispute_item_id - айди товара
-- dispute_id - айди спора, в котором фигурирует заказ
-- item_name - наимеование товара
CREATE TABLE IF NOT EXISTS public.dispute_item
(
    dispute_item_id uuid PRIMARY KEY,
    dispute_id      uuid         NOT NULL REFERENCES public.dispute (dispute_id),
    item_name       VARCHAR(128) NOT NULL
);

-- excel_item - таблица для выставления товара для производителя
-- excel_item_id - айди товара
-- seller_id - айди продавца
-- item_name - наименование товара
-- in_stock - флаг наличия товара (да/нет)
-- production_time - время производства, если не в наличии
-- quantity - кол-во товара в наличии
-- first_price - цена, которая дейтсвует от 0 единиц товара
-- second_price_quantity - количество, от которого действует вторая цена товара
-- second_price - вторая цена товара
-- third_price_quantity - количество, от которого действует третья цена товара
-- third_price - третья цена
CREATE TABLE IF NOT EXISTS public.excel_item
(
    excel_item_id         uuid PRIMARY KEY,
    seller_id             BIGINT REFERENCES public."user" (user_id),
    item_name             VARCHAR(128) NOT NULL,
    in_stock              BOOLEAN      NOT NULL,
    production_time       varchar(16),
    quantity              INTEGER,
    first_price           NUMERIC(10, 2),
    second_price_quantity INTEGER,
    second_price          NUMERIC(10, 2),
    third_price_quantity  INTEGER,
    third_price           NUMERIC(10, 2)
);











