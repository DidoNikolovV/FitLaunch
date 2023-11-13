package com.softuni.fitlaunch.service;


import com.softuni.fitlaunch.model.dto.UploadPictureWorkoutDTO;
import com.softuni.fitlaunch.model.dto.workout.CreateWorkoutDTO;
import com.softuni.fitlaunch.model.dto.ExerciseDTO;
import com.softuni.fitlaunch.model.dto.workout.WorkoutDTO;
import com.softuni.fitlaunch.model.dto.workout.WorkoutDetailsDTO;
import com.softuni.fitlaunch.model.entity.ExerciseEntity;
import com.softuni.fitlaunch.model.entity.WorkoutEntity;
import com.softuni.fitlaunch.model.enums.LevelEnum;
import com.softuni.fitlaunch.repository.ExerciseRepository;
import com.softuni.fitlaunch.repository.WorkoutRepository;
import org.hibernate.result.Outputs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WorkoutService {
    private static final String BASE_IMAGES_PATH = "/images/";
    private final WorkoutRepository workoutRepository;
    private final ExerciseRepository exerciseRepository;

    public WorkoutService(WorkoutRepository workoutRepository, ExerciseRepository exerciseRepository) {
        this.workoutRepository = workoutRepository;
        this.exerciseRepository = exerciseRepository;
    }

    public Page<WorkoutDTO> getAllWorkouts(Pageable pageable) {
        return workoutRepository
                .findAll(pageable)
                .map(WorkoutService::mapAsWorkoutDTO);
    }

    public List<ExerciseDTO> getAllExercises() {
        return exerciseRepository.findAll()
                .stream()
                .map(WorkoutService::mapAsExerciseDTO)
                .toList();
    }

    public List<LevelEnum> getAllLevels() {
        return Arrays.stream(LevelEnum.values()).collect(Collectors.toList());
    }

    public Optional<WorkoutDetailsDTO> getWorkoutDetails(Long workoutId) {
        return workoutRepository.findById(workoutId)
                .map(WorkoutService::mapAsDetails);
    }

    public Long createWorkout(CreateWorkoutDTO createWorkoutDTO) {
        WorkoutEntity newWorkout = map(createWorkoutDTO);
        newWorkout.setExercises(createWorkoutDTO.getExercises().stream().map(WorkoutService::mapAsExerciseEntity).collect(Collectors.toList()));
        newWorkout = workoutRepository.save(newWorkout);

        return newWorkout.getId();
    }


    private static WorkoutDTO mapAsWorkoutDTO(WorkoutEntity workoutEntity) {
        return new WorkoutDTO(
                workoutEntity.getId(),
                workoutEntity.getName(),
                workoutEntity.getImgUrl(),
                workoutEntity.getLevel(),
                workoutEntity.getDescription()
        );
    }

    private static WorkoutDetailsDTO mapAsDetails(WorkoutEntity workoutEntity) {

        List<ExerciseDTO> exercises = workoutEntity.getExercises().stream().map(WorkoutService::mapAsExerciseDTO).toList();

        return new WorkoutDetailsDTO(
                workoutEntity.getId(),
                workoutEntity.getName(),
                workoutEntity.getLevel(),
                workoutEntity.getDescription(),
                workoutEntity.getImgUrl(),
                exercises
        );
    }

    private WorkoutEntity map(CreateWorkoutDTO workoutDTO) {

        List<ExerciseEntity> exercises = workoutDTO.getExercises().stream().map(WorkoutService::mapAsExerciseEntity).toList();

        MultipartFile pictureFile = workoutDTO.getImgUrl();

        String picturePath = getPicturePath(pictureFile);


        try {
            File file = new File(BASE_IMAGES_PATH + picturePath);
            file.getParentFile().mkdirs();
            file.createNewFile();

            OutputStream outputStream = new FileOutputStream(file);
            outputStream.write(pictureFile.getBytes());

        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }

        return new WorkoutEntity()
                .setName(workoutDTO.getName())
                .setLevel(workoutDTO.getLevel())
                .setDescription(workoutDTO.getDescription())
                .setImgUrl(picturePath)
                .setExercises(exercises);


    }

    private String getPicturePath(MultipartFile pictureFile) {


        String[] splitPictureName = pictureFile.getOriginalFilename().split("\\.");
        String ext = splitPictureName[splitPictureName.length - 1];
        String imgPath = splitPictureName[0];
        String pathPattern = "%s." + ext;


        return String.format(pathPattern,
                BASE_IMAGES_PATH + imgPath);
    }


    private static ExerciseDTO mapAsExerciseDTO(ExerciseEntity exerciseEntity) {
        return new ExerciseDTO(
                exerciseEntity.getId(),
                exerciseEntity.getName()
        );
    }

    private static ExerciseEntity mapAsExerciseEntity(ExerciseDTO exerciseDTO) {
        return new ExerciseEntity()
                .setId(exerciseDTO.getId())
                .setName(exerciseDTO.getName());
    }

    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        }

        return null;
    }

}
