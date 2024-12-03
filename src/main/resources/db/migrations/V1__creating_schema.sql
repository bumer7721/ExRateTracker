CREATE TABLE currencies
(
    id         BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    code       VARCHAR(3)       NOT NULL,
    name       VARCHAR(50)
);