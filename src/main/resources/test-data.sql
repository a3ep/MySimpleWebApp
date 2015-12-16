INSERT INTO HOBBY (title, description) VALUES ('GAMES', 'Play games');
INSERT INTO HOBBY (title, description) VALUES ('SPORT', 'Sport');

INSERT INTO PLACE (title, description, latitude, longitude) VALUES ('HOME', 'My home', 12.0,  12.0);
INSERT INTO PLACE (title, description, latitude, longitude) VALUES ('WORK', 'My work', 21.0,  21.0);

INSERT INTO Contact (firstName, lastName, birthDate, userName, password, confirmPassword, photo) VALUES ('Всеволод', 'Бондарь', '1990-09-18', 'azeral', '258456', '258456', '../../resources/img/my.png');
INSERT INTO Contact (firstName, lastName, birthDate, userName, password, confirmPassword, photo) VALUES ('Святослав', 'Бондарь', '1992-07-24', 'ctumyji', '258456', '258456', '../../resources/img/no-photo.png');
INSERT INTO Contact (firstName, lastName, birthDate, userName, password, confirmPassword, photo) VALUES ('Илья', 'Коверя', '1992-08-14', 'gold', '258456', '258456', '../../resources/img/no-photo.png')

-- INSERT INTO CONTACT_HOBBY (CONTACT_id, HOBBY_id) VALUES (1, 1);
-- INSERT INTO CONTACT_HOBBY (CONTACT_id, HOBBY_id) VALUES (1, 2);
-- INSERT INTO CONTACT_PLACE (CONTACT_id, PLACE_id) VALUES (1, 1);
-- INSERT INTO CONTACT_PLACE (CONTACT_id, PLACE_id) VALUES (1, 2);
-- INSERT INTO CONTACT_CONTACT (CONTACT_id, friendList_id) VALUES (1, 2);
