CREATE TABLE premises
(
    id            VARCHAR(255) NOT NULL,
    name          VARCHAR(255) NULL,
    address       VARCHAR(255) NULL,
    type          VARCHAR(255) NULL,
    area          DOUBLE       NULL,
    rent_price    DOUBLE       NULL,
    start_date    date         NULL,
    `description` VARCHAR(255) NULL,
    CONSTRAINT pk_premises PRIMARY KEY (id)
);