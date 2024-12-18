CREATE TABLE clazz
(
    id   INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255)       NULL,
    CONSTRAINT pk_clazz PRIMARY KEY (id)
);

CREATE TABLE student
(
    id       INT AUTO_INCREMENT NOT NULL,
    name     VARCHAR(255)       NULL,
    score    DOUBLE             NOT NULL,
    clazz_id INT                NULL,
    CONSTRAINT pk_student PRIMARY KEY (id)
);

ALTER TABLE student
    ADD CONSTRAINT FK_STUDENT_ON_CLAZZ FOREIGN KEY (clazz_id) REFERENCES clazz (id);