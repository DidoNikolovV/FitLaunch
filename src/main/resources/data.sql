INSERT INTO users (id, email, username, password, membership)
VALUES
    (1, 'admin@example.com', 'Admin', '95c1933b8ffe84f085f2839899d1673260be58dbd9c2c787ac35515805502c996417596dae9a92880aaa50a046fc7151', 'Yearly'),
    (2, 'user@example.com', 'User', '95c1933b8ffe84f085f2839899d1673260be58dbd9c2c787ac35515805502c996417596dae9a92880aaa50a046fc7151', 'Monthly');

INSERT INTO roles (`id`, `role`)
VALUES
    (1, 'ADMIN'),
    (2, 'USER');

INSERT INTO users_roles(`user_id`,  `role_id`)
VALUES
    (1, 1),
    (1, 2),
    (2, 2);

INSERT INTO workouts(`id`, `description`, `img_url`, `level`, `name`, `likes`, `author_id`)
VALUES
    (1, 'Start your fitness journey with our beginner workouts.', '/images/beginner.jpg', 'BEGINNER', 'Full Body', 0, 2),
    (2, 'Take your fitness to the next level with our intermediate workouts.', '/images/intermediate.jpg', 'INTERMEDIATE', 'Push', 0, 1),
    (3, 'Challenge yourself with our advanced workout programs.', '/images/advanced.jpg', 'ADVANCED', 'Pull', 0, 1),
    (4, 'Start your fitness journey with our beginner workouts.', '/images/beginner.jpg', 'BEGINNER', 'LEGS', 0, 1);

INSERT INTO exercises(`id`, `name`, reps, sets, video_url)
VALUES
    (1, 'Shoulder Press', 12, 3, 'OLePvpxQEGk'),
    (2, 'Squats', 8, 3, 'MLoZuAkIyZI'),
    (3, 'Dumbbell Lateral Raise', 4, 20, 'JIhbYYA1Q90'),
    (4, 'Chest Fly', 10, 4, 'g3T7LsEeDWQ'),
    (5, 'Cable Row', 10, 3, 'G18ysBYu5Mw'),
    (6, 'Pull-ups', 6, 4, 'dvG8B2OjfWk'),
    (7, 'Dumbbell Curls', 12, 3, ''),
    (8, 'RDL', 12, 3, '5rIqP63yWFg'),
    (9, 'Deadlift', 5, 5, 'McCDaAsSeRc'),
    (10, 'Bench Press', 5, 5, 'EdDqD4aKwxM');

-- INSERT INTO workouts_exercises(`workout_id`, `exercise_id`)
-- VALUES
--     (1, 1),
--     (1, 2),
--     (1, 3),
--     (2, 1),
--     (2, 2),
--     (2, 5),
--     (3, 10),
--     (3, 9),
--     (3, 5),
--     (3, 10),
--     (3, 2),
--     (4, 3),
--     (4, 1),
--     (4, 4);

INSERT INTO workout_exercises (reps, sets, exercise_id, workout_id)
VALUES
    (12, 3, 1, 1),
    (10, 4, 2, 1),
    (15, 3, 3, 2),
    (8, 5, 1, 2),
    (10, 3, 2, 3),
    (12, 4, 3, 3);


INSERT INTO programs (img_url, name)
VALUES
    ('/images/beginner-program.jpg', 'Beginner'),
    ('/images/intermediate-program.jpg', 'Intermediate'),
    ('/images/advanced-program.jpg', 'Advanced');

INSERT INTO weeks (program_id)
VALUES
    (1), (1), (1), (1), (1), -- Program 1
    (2), (2), (2), (2), (2), -- Program 2
    (3), (3), (3), (3), (3); -- Program 2

INSERT INTO days (week_id)
VALUES
    (1), (1), (1), (1), (1), (1), (1),
    (2), (2), (2), (2), (2), (2), (2),
    (3), (3), (3), (3), (3), (3), (3);

INSERT INTO workout_exercises (day_id, exercise_id, sets, reps, workout_id) VALUES
(1, 1, 3, 12, 1),
(1, 2, 4, 10, 2),
(2, 3, 3, 15, 4),
(3, 4, 5, 5, 3);









