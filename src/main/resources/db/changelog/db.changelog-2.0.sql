--liquibase formatted sql

--changeset ilya_shulenin:1
INSERT INTO users (email, password, login, name, surname) VALUES ('123', '123', '123', 'ivan', 'ivanovich');
INSERT INTO users (email, password, login, name, surname) VALUES ('456', '456', '456', 'petr', 'petrovich');
INSERT INTO users (email, password, login, name, surname) VALUES ('798', '798', '789', 'vasya', 'vasilyevich');

--changeset ilya_shulenin:2
INSERT INTO message (content) VALUES ('Hello');
INSERT INTO message (content) VALUES ('Bye');
INSERT INTO message (content) VALUES ('Hi!');

--changeset ilya_shulenin:3
INSERT INTO chat (user_1_id, user_2_id, message_id) VALUES (1, 2, 1);
INSERT INTO chat (user_1_id, user_2_id, message_id) VALUES (1, 2, 2);
INSERT INTO chat (user_1_id, user_2_id, message_id) VALUES (1, 2, 3);
