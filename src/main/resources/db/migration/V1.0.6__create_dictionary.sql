CREATE TABLE dictionary
(
    id       INT AUTO_INCREMENT NOT NULL,
    viet_nam VARCHAR(255)       NULL,
    english  VARCHAR(255)       NULL,
    CONSTRAINT pk_dictionary PRIMARY KEY (id)
);