--liquibase formatted sql

--changeset ilya_shulenin:1
CREATE TABLE users(
    id BIGSERIAL,
    email VARCHAR(128) UNIQUE,
    password VARCHAR(128) default '123',
    login VARCHAR(64) UNIQUE,
    name VARCHAR(64),
    surname VARCHAR(64),
    is_active BOOLEAN DEFAULT true,

    PRIMARY KEY (id)
);

--changeset ilya_shulenin:2
CREATE TABLE message(
    id BIGSERIAL,
    send_time TIMESTAMP,
    content TEXT,

    PRIMARY KEY (id)
);

--changeset ilya_shulenin:3
CREATE TABLE chat(
    id BIGSERIAL,
    user_1_id BIGINT NOT NULL REFERENCES users(id),
    user_2_id BIGINT NOT NULL REFERENCES users(id),
    message_id BIGINT NOT NULL REFERENCES message(id),

    PRIMARY KEY (id)
);

----changeset ilya_shulenin:4
CREATE TABLE friend(
    id BIGSERIAL,
    user_1_id BIGINT NOT NULL REFERENCES users(id),
    user_2_id BIGINT NOT NULL REFERENCES users(id),

    PRIMARY KEY (id)
);
