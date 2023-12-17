INSERT INTO users (id, email, username, password, membership, activated)
VALUES
    (1, 'admin@example.com', 'Admin', '95c1933b8ffe84f085f2839899d1673260be58dbd9c2c787ac35515805502c996417596dae9a92880aaa50a046fc7151', 'Yearly', 1),
    (2, 'user@example.com', 'User', '95c1933b8ffe84f085f2839899d1673260be58dbd9c2c787ac35515805502c996417596dae9a92880aaa50a046fc7151', 'Monthly', 1);

INSERT INTO roles (`id`, `role`)
VALUES
    (1, 'ADMIN'),
    (2, 'USER');

INSERT INTO users_roles(`user_id`,  `role_id`)
VALUES
    (1, 1),
    (1, 2),
    (2, 2);

INSERT INTO workouts(`id`, `description`, `img_url`, `level`, `name`, `likes`, `author_id`, `has_started`, `is_completed`)
VALUES
    (1, 'Start your fitness journey with our beginner workouts.', '/images/beginner.jpg', 'BEGINNER', 'Full Body', 0, 2, false, false),
    (2, 'Take your fitness to the next level with our intermediate workouts.', '/images/intermediate.jpg', 'INTERMEDIATE', 'Push', 0, 1, false, false),
    (3, 'Challenge yourself with our advanced workout programs.', '/images/advanced.jpg', 'ADVANCED', 'Pull', 0, 1, false, false),
    (4, 'Start your fitness journey with our beginner workouts.', '/images/beginner.jpg', 'BEGINNER', 'LEGS', 0, 1, false, false);



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

-- INSERT INTO workout_exercises (reps, sets, exercise_id, workout_id, is_completed)
-- VALUES
--     (12, 3, 1, 1, 0),
--     (10, 4, 2, 1, 0),
--     (15, 3, 3, 2, 0),
--     (8, 5, 1, 2, 0),
--     (10, 3, 2, 3, 0),
--     (12, 4, 3, 3, 0),
--     (12, 4, 3, 3, 0);


INSERT INTO programs (img_url, name)
VALUES
    ('/images/beginner-program.jpg', 'Beginner'),
    ('/images/intermediate-program.jpg', 'Intermediate'),
    ('/images/advanced-program.jpg', 'Advanced');

-- Insert data into ProgramWeekEntity
INSERT INTO programs_weeks (id, program_id)
VALUES
        (1, 1),
        (2, 1),
        (3, 1),
        (4, 1);

-- Insert data into ProgramWeekWorkoutEntity
INSERT INTO programs_weeks_workouts (id, name, program_week_id, has_started, is_completed, level, description)
VALUES
        (1, 'Push', 1, 0, 0, 'Beginner', 'This workout is designed to hit Chest, Triceps and Shoulder effectively'),
        (2, 'Pull', 1, 0, 0, 'Beginner', 'This workout is designed to hit Back, Biceps and Rear Delts effectively'),
        (3, 'Legs', 1, 0, 0, 'Beginner', 'This workout is designed to hit Legs effectively'),
        (4, 'Upper Body', 1, 0, 0, 'Beginner', 'This workout is designed to hit Upper Body effectively'),
        (5, 'Lower Body', 2, 0, 0, 'Beginner', 'This workout is designed to hit Lower Body effectively'),
        (6, 'Back & Back', 2, 0, 0, 'Beginner', 'This workout is designed to hit Back and Biceps effectively'),
        (7, 'Chest & Triceps', 2, 0, 0, 'Beginner', 'This workout is designed to hit Chest and Triceps effectively'),
        (8, 'ABS/Calves', 2, 0, 0, 'Beginner', 'This workout is designed to hit ABS and Calves effectively'),
        (9, 'Full Body', 3, 0, 0, 'Beginner', 'This workout is designed to hit your whole body effectively'),
        (10, 'HIIT ', 3, 0, 0, 'Beginner', 'This workout is designed to make your heart rate go up effectively'),
        (11, 'Core', 3, 0, 0, 'Beginner', 'This workout is designed to hit your Core effectively'),
        (12, 'Push', 3, 0, 0, 'Beginner', 'This workout is designed to hit Chest, Triceps and Shoulder  effectively'),
        (13, 'Pull', 4, 0, 0, 'Beginner', 'This workout is designed to hit Back, Biceps and Rear Delts effectively'),
        (14, 'Legs',4, 0, 0, 'Beginner', 'This workout is designed to hit Legs effectively effectively'),
        (15, 'Arms', 4, 0, 0, 'Beginner', 'This workout is designed to hit your Arms effectively'),
        (16, 'Shoulders', 4, 0, 0, 'Beginner', 'This workout is designed to hit your Shoulders effectively');

INSERT INTO exercises(`id`, `name`, `video_url`, `program_workout_id`)
VALUES
    (1, 'Shoulder Press','OLePvpxQEGk', 1),
    (2, 'Squats', 'MLoZuAkIyZI', 1),
    (3, 'Dumbbell Lateral Raise', 'JIhbYYA1Q90', 2),
    (4, 'Chest Fly',  'g3T7LsEeDWQ', 2),
    (5, 'Cable Row', 'G18ysBYu5Mw', 3),
    (6, 'Pull-ups', 'dvG8B2OjfWk', 3),
    (7, 'Dumbbell Curls', '', 4),
    (8, 'RDL', '5rIqP63yWFg', 4),
    (9, 'Deadlift', 'McCDaAsSeRc', 5),
    (10, 'Bench Press','EdDqD4aKwxM', 5);

INSERT INTO program_workouts_exercises(id, workout_id, exercise_id, sets, reps, is_completed)
VALUES
    (1, 1, 1, 3, 12, 0),
    (2, 1, 2, 4, 10, 0),
    (3, 2, 3, 5, 5, 0),
    (4, 2, 4, 2, 20, 0),
    (5, 3, 2, 3, 12, 0),
    (6, 3, 1, 4, 6, 0);






