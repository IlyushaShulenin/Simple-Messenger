--liquibase formatted sql

--changeset ilya_shulenin:1
INSERT INTO music_user (email, password, role) VALUES ('123', '123', 'CREATOR');
INSERT INTO music_user (email, password) VALUES ('456', '123');
INSERT INTO music_user (email, password) VALUES ('798', '123');

--changeset ilya_shulenin:2
INSERT INTO playlist (user_id, name) VALUES (1, 'Training');
INSERT INTO playlist (user_id, name) VALUES (1, 'Party');
INSERT INTO playlist (user_id, name) VALUES (1, 'MyMusic');

INSERT INTO playlist (user_id, name) VALUES (2, 'Classic');
INSERT INTO playlist (user_id, name) VALUES (2, 'Rock');
INSERT INTO playlist (user_id, name) VALUES (2, 'To sleep');

INSERT INTO playlist (user_id, name) VALUES (3, 'Pop');
INSERT INTO playlist (user_id, name) VALUES (3, '80s');
INSERT INTO playlist (user_id, name) VALUES (3, 'Meditation');

--changeset ilya_shulenin:3
INSERT INTO music (name, creator_id) VALUES ('song1', 1);
INSERT INTO music (name, creator_id) VALUES ('song2', 1);
INSERT INTO music (name, creator_id) VALUES ('song3', 1);
INSERT INTO music (name, creator_id) VALUES ('song4', 1);
INSERT INTO music (name, creator_id) VALUES ('song5', 1);
INSERT INTO music (name, creator_id) VALUES ('song6', 1);
INSERT INTO music (name, creator_id) VALUES ('song7', 1);

--changeset ilya_shulenin:4
INSERT INTO playlist_music (playlist_id, music_id) VALUES (1, 1);
INSERT INTO playlist_music (playlist_id, music_id) VALUES (1, 2);
INSERT INTO playlist_music (playlist_id, music_id) VALUES (1, 3);

INSERT INTO playlist_music (playlist_id, music_id) VALUES (2, 4);
INSERT INTO playlist_music (playlist_id, music_id) VALUES (2, 5);
INSERT INTO playlist_music (playlist_id, music_id) VALUES (2, 6);

INSERT INTO playlist_music (playlist_id, music_id) VALUES (4, 7);
INSERT INTO playlist_music (playlist_id, music_id) VALUES (4, 1);
INSERT INTO playlist_music (playlist_id, music_id) VALUES (5, 2);