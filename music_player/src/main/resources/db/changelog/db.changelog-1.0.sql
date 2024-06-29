--liquibase formatted sql

--changeset ilya_shulenin:1
CREATE TABLE music_user (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(128),
    password VARCHAR(128),
    role VARCHAR(16) DEFAULT 'LISTENER'
);

--changeset ilya_shulenin:2
CREATE TABLE playlist (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES music_user(id),
    name VARCHAR(255)
);

--changeset ilya_shulenin:3
CREATE TABLE music (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR(256),
                       creator_id BIGINT REFERENCES music_user(id)
);

--changeset ilya_shulenin:4
CREATE TABLE playlist_music (
    id BIGSERIAL PRIMARY KEY,
    playlist_id BIGINT REFERENCES playlist(id),
    music_id BIGINT REFERENCES music(id)
);