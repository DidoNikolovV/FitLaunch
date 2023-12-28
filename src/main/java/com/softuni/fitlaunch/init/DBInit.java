package com.softuni.fitlaunch.init;


import com.softuni.fitlaunch.model.entity.*;
import com.softuni.fitlaunch.model.enums.UserRoleEnum;
import com.softuni.fitlaunch.repository.*;
import com.softuni.fitlaunch.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DBInit implements CommandLineRunner {

    private final WorkoutRepository workoutRepository;
    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    private final ClientRepository clientRepository;
    private final CoachRepository coachRepository;

    private final ProgramRepository programRepository;
    private final ProgramWeekRepository programWeekRepository;
    private final ProgramWeekWorkoutRepository programWeekWorkoutRepository;

    private final ExerciseRepository exerciseRepository;

    private final ModelMapper modelMapper;




    public DBInit(WorkoutRepository workoutRepository, CommentRepository commentRepository, UserRepository userRepository, ClientRepository clientRepository, CoachRepository coachRepository, ProgramRepository programRepository, ProgramWeekRepository programWeekRepository, ProgramWeekWorkoutRepository programWeekWorkoutRepository, ExerciseRepository exerciseRepository, ModelMapper modelMapper) {
        this.workoutRepository = workoutRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.coachRepository = coachRepository;
        this.programRepository = programRepository;
        this.programWeekRepository = programWeekRepository;
        this.programWeekWorkoutRepository = programWeekWorkoutRepository;
        this.exerciseRepository = exerciseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if(commentRepository.count() == 0) {
            initComment("Admin", 1L, 1L, 1L, "Cool Workout", "I like it");
        }

        if(clientRepository.count() == 0) {
            List<ClientEntity> dbClients = userRepository.findAll()
                    .stream()
                    .filter(user -> user.getRoles().get(0).getRole().equals(UserRoleEnum.CLIENT))
                            .map(user -> modelMapper.map(user, ClientEntity.class))
                                    .toList();
            clientRepository.saveAll(dbClients);
        }



        List<ExerciseEntity> allExercises = exerciseRepository.findAll();


//        if(programWeekWorkoutRepository.count() == 0) {
//            initProgram(1L, 1L, allExercises);
//            initProgram(2L, 1L, allExercises);
//        }

//        initData();

    }

    private void initComment(String authorUsername, Long programId, Long weekId, Long workoutId, String... comments) {
        UserEntity user = userRepository.findByUsername(authorUsername)
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
            aComment.setMessage(comment);
            allComments.add(aComment);
        }


        commentRepository.saveAll(allComments);
        user.setComments(allComments);


    }

//    private void initProgram(Long programId, Long weekId, List<ExerciseEntity> exercises) {
//        ProgramEntity program = programRepository.findById(programId)
//                .orElseThrow(() -> new ObjectNotFoundException("Program not found"));
//
//        ProgramWeekEntity programWeekEntity = programWeekRepository.findByIdAndProgramId(weekId, programId).orElseThrow(() -> new ObjectNotFoundException("Week not found"));
//        List<ProgramWeekWorkoutEntity> programWeekWorkouts = new ArrayList<>();
//        while(programWeekWorkouts.size() < 4) {
//            ProgramWeekWorkoutEntity programWeekWorkout = new ProgramWeekWorkoutEntity();
//            programWeekWorkout.setHasStarted(false);
//            programWeekWorkout.setLikes(0L);
//            programWeekWorkout.setDescription("Cool Workout");
//            programWeekWorkout.setLevel(LevelEnum.BEGINNER);
//            programWeekWorkout.setLevel(LevelEnum.BEGINNER);
//            programWeekWorkout.setCompleted(false);
//            programWeekWorkout.setProgramWeek(programWeekEntity);
//            for (ExerciseEntity exercise : exercises) {
//                ProgramWorkoutExerciseEntity programWorkoutExerciseEntity = new ProgramWorkoutExerciseEntity();
//                programWorkoutExerciseEntity.setExercise(exercise);
//                programWorkoutExerciseEntity.setWorkout(programWeekWorkout);
//                programWorkoutExerciseEntity.setSets(4);
//                programWorkoutExerciseEntity.setReps(12);
//            }
//
//            programWeekWorkouts.add(programWeekWorkout);
//        }
//
//        programWeekWorkoutRepository.saveAll(programWeekWorkouts);
//
//        programWeekEntity.setProgram(program);
//        programWeekEntity.setWeekWorkouts(programWeekWorkouts);
//
//        programRepository.save(program);
//        programWeekRepository.save(programWeekEntity);
//    }

}

