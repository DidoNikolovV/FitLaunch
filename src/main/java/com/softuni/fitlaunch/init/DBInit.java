package com.softuni.fitlaunch.init;


import com.softuni.fitlaunch.model.entity.CommentEntity;
import com.softuni.fitlaunch.model.entity.UserEntity;
import com.softuni.fitlaunch.model.entity.WorkoutEntity;
import com.softuni.fitlaunch.repository.CommentRepository;
import com.softuni.fitlaunch.repository.UserRepository;
import com.softuni.fitlaunch.repository.WorkoutRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DBInit implements CommandLineRunner {

    private final WorkoutRepository workoutRepository;
    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    public DBInit(WorkoutRepository workoutRepository, CommentRepository commentRepository, UserRepository userRepository) {
        this.workoutRepository = workoutRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(commentRepository.count() == 0) {
            initComment("Admin", 1L, "Cool Workout", "I like it");
        }
    }

    private void initComment(String authorName, Long workoutId, String... comments) {
        UserEntity user = userRepository.findByUsername(authorName)
                .orElseThrow(() -> new RuntimeException("User not found"));

        WorkoutEntity workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new RuntimeException(("Workout not found")));


        List<CommentEntity> allComments = new ArrayList<>();

        for (String comment : comments) {
            CommentEntity aComment = new CommentEntity();
            aComment.setAuthor(user);
            aComment.setWorkout(workout);
            aComment.setContent(comment);
            allComments.add(aComment);
        }

//        user.setComments(allComments);

        commentRepository.saveAll(allComments);

    }
}
