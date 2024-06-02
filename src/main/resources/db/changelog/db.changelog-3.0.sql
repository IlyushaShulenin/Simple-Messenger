--liquibase formatted sql

--changeset ilya_shulenin:1
CREATE TABLE refresh_token(
    id BIGSERIAL,
    token VARCHAR(256),
    expry_date TIMESTAMP,

    PRIMARY KEY (id)
);