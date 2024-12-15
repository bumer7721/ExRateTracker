CREATE TABLE currencies
(
    id         BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    code       VARCHAR(3)       NOT NULL,
    name       VARCHAR(50)
);

CREATE TABLE exchange_rates
(
    id                BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    base              VARCHAR(3)       NOT NULL,
    quote_currency_id INTEGER          NOT NULL,
    rate              DOUBLE PRECISION NOT NULL,
    CONSTRAINT fk_quote_currency FOREIGN KEY (quote_currency_id) REFERENCES currencies (id) ON DELETE CASCADE
);