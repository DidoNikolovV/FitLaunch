CREATE TABLE beginner_program (
                                  id INT AUTO_INCREMENT PRIMARY KEY,
                                  title VARCHAR(255),
                                  description TEXT,
                                  target_muscles VARCHAR(255)
);


INSERT INTO beginner_program (title, description, target_muscles)
VALUES ('Beginner Workout', 'Start your fitness journey with our beginner workouts.', 'Chest, Triceps, Back, Biceps, Legs');


CREATE TABLE beginner_program_exercises (
                                            id INT AUTO_INCREMENT PRIMARY KEY,
                                            exercise_name VARCHAR(255),
                                            description TEXT,
                                            program_id INT,
                                            FOREIGN KEY (program_id) REFERENCES beginner_program(id)
);


INSERT INTO beginner_program_exercises (exercise_name, description, program_id)
VALUES
    ('Push-ups', 'Description of Push-ups', 1),
    ('Bodyweight Squats', 'Description of Bodyweight Squats', 1),
    ('Planks', 'Description of Planks', 1),
    ('Lunges', 'Description of Lunges', 1),
    ('Jumping Jacks', 'Description of Jumping Jacks', 1);
