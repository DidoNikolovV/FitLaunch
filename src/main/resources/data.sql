INSERT INTO users (id, email, username, password)
VALUES
    (1, 'admin@example.com', 'Admin', '95c1933b8ffe84f085f2839899d1673260be58dbd9c2c787ac35515805502c996417596dae9a92880aaa50a046fc7151'),
    (2, 'user@example.com', 'User', '95c1933b8ffe84f085f2839899d1673260be58dbd9c2c787ac35515805502c996417596dae9a92880aaa50a046fc7151');


INSERT INTO roles (`id`, `role`)
VALUES
    (1, 'ADMIN'),
    (2, 'USER');

INSERT INTO users_roles(`user_id`,  `role_id`)
VALUES
    (1, 1),
    (1, 2),
    (2, 2);

INSERT INTO workouts(`id`, `description`, `img_url`, `level`, `name`)
VALUES
    (1, 'Start your fitness journey with our beginner workouts.', '/images/beginner.jpg', 'BEGINNER', 'Full Body'),
    (2, 'Take your fitness to the next level with our intermediate workouts.', '/images/intermediate.jpg', 'INTERMEDIATE', 'Push'),
    (3, 'Challenge yourself with our advanced workout programs.', '/images/advanced.jpg', 'ADVANCED', 'Pull'),
    (4, 'Start your fitness journey with our beginner workouts.', '/images/beginner.jpg', 'BEGINNER', 'LEGS');

INSERT INTO exercises(`id`, `name`)
VALUES
    (1, 'Push-ups'),
    (2, 'Squats'),
    (3, 'Planks'),
    (4, 'Jumping Jacks'),
    (5, 'Burpees'),
    (6, 'Lunges'),
    (7, 'Bicycle Crunches'),
    (8, 'Mountain Climbers'),
    (9, 'Leg Raises'),
    (10, 'Pull-ups'),
    (11, 'Dumbbell Curls'),
    (12, 'Deadlifts');

INSERT INTO workouts_exercises(`workout_id`, `exercise_id`)
VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (2, 1),
    (2, 2),
    (2, 5),
    (3, 10),
    (3, 11),
    (3, 12),
    (4, 2),
    (4, 6),
    (4, 12);

-- Insert data into genders table
INSERT INTO genders (`id`, `gender`)
VALUES
       (1, 'MEN'),
       (2, 'WOMAN');

-- Insert data into shop table
INSERT INTO shop (`id`, `gender_id`) VALUES
                                      (1, 1),
                                      (2, 2);

-- Insert data into cloths table
INSERT INTO cloths (id, img_url, name, price, shop_id) VALUES
                                                                      (1, '/images/mens-cloth', 'Men Shirt 1',  29.99, 1),
                                                                      (2, '/images/mens-cloth', 'Men Jeans 1',  39.99, 1),
                                                                      (3, '/images/mens-cloth', 'Women Dress 1',  49.99, 2);







