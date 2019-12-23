CREATE TABLE product
(
    id      INTEGER PRIMARY KEY,
    title   TEXT             NOT NULL,
    cost    DOUBLE PRECISION NOT NULL,
    count   INTEGER          NOT NULL,
    shop_id INTEGER REFERENCES shop (id) ON DELETE CASCADE
);