CREATE TABLE clients_stocks
(
    id             BIGSERIAL PRIMARY KEY,
    ticker         VARCHAR(32)  NOT NULL,
    name           VARCHAR(128) NOT NULL,
    count         BIGINT,
    cost_one_stock DECIMAL      NOT NULL,
    operation VARCHAR(10),
    date date,
    country        VARCHAR(128) NOT NULL,
    dividend       DECIMAL,
    currency       VARCHAR(20) NOT NULL
);

CREATE TABLE currencies
(
    id     BIGSERIAL PRIMARY KEY,
    ticker VARCHAR(32) NOT NULL UNIQUE,
    name   VARCHAR(32) NOT NULL UNIQUE
);

CREATE TABLE clients
(
    id       BIGSERIAL PRIMARY KEY,
    email    VARCHAR(128) NOT NULL UNIQUE,
    password VARCHAR(128) NOT NULL,
    role     VARCHAR(128),
    detail_id BIGINT,
    FOREIGN KEY (detail_id) REFERENCES details(id) ON DELETE CASCADE
);

CREATE TABLE details
(
    id            BIGSERIAL PRIMARY KEY,
    first_name    VARCHAR(128) NOT NULL,
    last_name     VARCHAR(128) NOT NULL,
    father_name   VARCHAR(128),
    citizenship   VARCHAR(128) NOT NULL,
    birthday      DATE         NOT NULL,
    passport_code VARCHAR(128),
    phone_number varchar(40)
);

CREATE TABLE client_clients_stocks
(
    client_id BIGINT REFERENCES clients (id) NOT NULL,
    stock_id  BIGINT REFERENCES clients_stocks (id)  NOT NULL
);

CREATE TABLE clients_currencies
(
    client_id   BIGINT REFERENCES clients (id)    NOT NULL,
    currency_id BIGINT REFERENCES currencies (id) NOT NULL
);

CREATE TABLE money
(
    id          SERIAL NOT NULL,
    count      DECIMAL,
    currency_id BIGINT,
    client_id   BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (currency_id) REFERENCES currencies (id),
    FOREIGN KEY (client_id) REFERENCES clients (id)
);

CREATE TABLE clients_money
(
    client_id BIGINT REFERENCES clients (id) NOT NULL,
    money_id  INT REFERENCES money (id)      NOT NULL
);

DROP TABLE details CASCADE;
DROP TABLE clients CASCADE;
DROP TABLE clients CASCADE ;
DROP TABLE clients_money;
DROP TABLE clients_stocks;

DROP TABLE money;
DROP TABLE money;

-- DROP TABLE stock_currencies;
--
-- DROP TABLE stocks CASCADE;

CREATE TABLE storage_stocks
(
    id             BIGSERIAL PRIMARY KEY,
    ticker         VARCHAR(32)  NOT NULL,
    name           VARCHAR(128) NOT NULL,
    count        BIGINT,
    cost_one_stock DECIMAL      NOT NULL,
    country        VARCHAR(128) NOT NULL,
    dividend       DECIMAL,
    currency_id    INT NOT NULL,
    FOREIGN KEY (currency_id) REFERENCES currencies
);

CREATE TABLE news(
    id BIGSERIAL PRIMARY KEY ,
    text TEXT NOT NULL ,
    date DATE,
    storage_stock_id INT REFERENCES storage_stocks(id)
    );



SELECT * FROM clients_stocks
                  JOIN client_clients_stocks ccs ON clients_stocks.id = ccs.stock_id
WHERE client_id=1


