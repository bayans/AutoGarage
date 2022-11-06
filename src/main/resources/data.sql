-- password is password
INSERT INTO users(username, password, enabled, email)
VALUES ('baian', '$2a$10$K6mQgFMi4.kUfo8wfiFLcu6d/rb3C.pIRlPrbt8x8ep5AUxeBSvli', true, 'theboss@gmail.com');
-- password is yannick
INSERT INTO users(username, password, enabled, email)
VALUES ('yannick', '$2a$10$c9PEU.QWzE15R4nD84/sMe7xz9Jrqskw1VZqfPp4N2v7m2bQ1VF9O', true, 'themechanic@gmail.com');


INSERT INTO authorities(username, authority, user_username)
VALUES ('baian', 'ROLE_ADMIN', 'baian');
INSERT INTO authorities(username, authority, user_username)
VALUES('yannick','ROLE_MECHANIC', 'yannick');


