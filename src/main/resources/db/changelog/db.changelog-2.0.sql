--liquibase formatted sql

--changeset ilya_shulenin:1
INSERT INTO users (email, login, name, surname) VALUES ('123', '123', 'ivan', 'ivanovich');
INSERT INTO users (email, login, name, surname) VALUES ('456', '456', 'petr', 'petrovich');
INSERT INTO users (email, login, name, surname) VALUES ('798', '789', 'vasya', 'vasilyevich');

--changeset ilya_shulenin:2
INSERT INTO chat (name) VALUES ('work');
INSERT INTO chat (name) VALUES ('friends');
INSERT INTO chat (name) VALUES ('family');

--changeset ilya_shulenin:3
INSERT INTO user_chat (user_id, chat_id) VALUES (1, 1);
INSERT INTO user_chat (user_id, chat_id) VALUES (2, 1);
INSERT INTO user_chat (user_id, chat_id) VALUES (3, 1);
INSERT INTO user_chat (user_id, chat_id) VALUES (2, 2);
INSERT INTO user_chat (user_id, chat_id) VALUES (3, 2);
INSERT INTO user_chat (user_id, chat_id) VALUES (2, 3);
INSERT INTO user_chat (user_id, chat_id) VALUES (1, 3);

--changeset ilya_shulenin:4
INSERT INTO message (sender_id, chat_id, content) VALUES (1, 1, 'Hello');
INSERT INTO message (sender_id, chat_id, content) VALUES (2, 1, 'Bye');
INSERT INTO message (sender_id, chat_id, content) VALUES (3, 1, 'Hi!');
INSERT INTO message (sender_id, chat_id, content) VALUES (1, 2, 'Hi!');
INSERT INTO message (sender_id, chat_id, content) VALUES (2, 3, 'Hi!');
