--liquibase formatted sql

--changeset ilya_shulenin:1
ALTER TABLE refresh_token ADD COLUMN user_id BIGINT REFERENCES users(id);