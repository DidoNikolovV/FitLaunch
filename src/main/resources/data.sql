INSERT INTO users (id, email, password, username)
VALUES
    (1, 'dido@abv.bg', '827ccb0eea8a706c4c34a16891f84e7b', 'didaka'),
    (2, 'gosho@abv.bg', '827ccb0eea8a706c4c34a16891f84e7b', 'gosho');


INSERT INTO user_roles (`id`, `role`)
VALUES
    (1, 'ADMIN'),
    (2, 'USER');

INSERT INTO users_roles(`user_id`,  `role_id`)
VALUES
    (1, 1),
    (1, 2),
    (2, 2);



