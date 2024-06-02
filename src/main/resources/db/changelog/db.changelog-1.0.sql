--liquibase formatted sql

--changeset ilya_shulenin:1
CREATE TABLE users(
    id BIGSERIAL,
    email VARCHAR(128) UNIQUE,
    password VARCHAR(128) default '{noop}123',
    login VARCHAR(64) UNIQUE,
    name VARCHAR(64),
    surname VARCHAR(64),
    is_active VARCHAR(16) DEFAULT 'ACTIVE',

    PRIMARY KEY (id)
);

--changeset ilya_shulenin:2
CREATE TABLE chat(
    id BIGSERIAL,
    name VARCHAR(128),

    PRIMARY KEY (id)
);

--changeset ilya_shulenin:3
CREATE TABLE message(
    id BIGSERIAL,
    sender_id BIGINT REFERENCES users(id),
    chat_id BIGINT REFERENCES chat(id),
    send_time TIMESTAMP,
    content TEXT,

    PRIMARY KEY (id)
);


--changeset ilya_shulenin:4
CREATE TABLE user_chat(
    id BIGSERIAL,
    user_id BIGINT REFERENCES users(id),
    chat_id BIGINT REFERENCES chat(id),

    PRIMARY KEY (id),
    CONSTRAINT uk_user_chat UNIQUE (user_id, chat_id)
);

--changeset ilya_shulenin:5
CREATE TABLE friend(
    id BIGSERIAL,
    user_1_id BIGINT NOT NULL REFERENCES users(id),
    user_2_id BIGINT NOT NULL REFERENCES users(id),

    PRIMARY KEY (id)
);
