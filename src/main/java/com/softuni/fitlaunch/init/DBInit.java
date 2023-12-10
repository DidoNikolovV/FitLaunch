package com.softuni.fitlaunch.init;


import com.softuni.fitlaunch.model.entity.*;
import com.softuni.fitlaunch.repository.*;
import com.softuni.fitlaunch.service.exception.ObjectNotFoundException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DBInit implements CommandLineRunner {

    private final WorkoutRepository workoutRepository;
    private final CommentRepository commentRepository;

    private final UserRepository userRepository;
    private final ProgramRepository programRepository;
    private final ProgramWeekRepository programWeekRepository;
    private final ProgramWeekWorkoutRepository programWeekWorkoutRepository;


    public DBInit(WorkoutRepository workoutRepository, CommentRepository commentRepository, UserRepository userRepository, ProgramRepository programRepository, ProgramWeekRepository programWeekRepository, ProgramWeekWorkoutRepository programWeekWorkoutRepository) {
        this.workoutRepository = workoutRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.programRepository = programRepository;
        this.programWeekRepository = programWeekRepository;
        this.programWeekWorkoutRepository = programWeekWorkoutRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(commentRepository.count() == 0) {
            initComment("Admin", 1L, 1L, 1L, "Cool Workout", "I like it");
        }

//        initData();

    }

    private void initComment(String authorName, Long programId, Long weekId, Long workoutId, String... comments) {
        UserEntity user = userRepository.findByUsername(authorName)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ProgramEntity program = programRepository.findById(programId).orElseThrow(() -> new ObjectNotFoundException("Program not found"));
        ProgramWeekEntity programWeek = programWeekRepository.findById(weekId).orElseThrow(() -> new ObjectNotFoundException("Week not found"));

        ProgramWeekWorkoutEntity programWorkout = programWeekWorkoutRepository.findById(workoutId)
                .orElseThrow(() -> new ObjectNotFoundException("Workout not found"));


        List<CommentEntity> allComments = new ArrayList<>();

        for (String comment : comments) {
            CommentEntity aComment = new CommentEntity();
            aComment.setAuthor(user);
            aComment.setProgram(program);
            aComment.setWeek(programWeek);
            aComment.setWorkout(programWorkout);
            aComment.setContent(comment);
            allComments.add(aComment);
        }

//        user.setComments(allComments);

        commentRepository.saveAll(allComments);

    }

}

