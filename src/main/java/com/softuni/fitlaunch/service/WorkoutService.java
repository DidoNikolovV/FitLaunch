package com.softuni.fitlaunch.service;


import com.softuni.fitlaunch.model.dto.workout.CreateWorkoutDTO;
import com.softuni.fitlaunch.model.dto.ExerciseDTO;
import com.softuni.fitlaunch.model.dto.workout.WorkoutDTO;
import com.softuni.fitlaunch.model.dto.workout.WorkoutDetailsDTO;
import com.softuni.fitlaunch.model.entity.ExerciseEntity;
import com.softuni.fitlaunch.model.entity.WorkoutEntity;
import com.softuni.fitlaunch.model.entity.WorkoutExerciseEntity;
import com.softuni.fitlaunch.model.enums.LevelEnum;
import com.softuni.fitlaunch.repository.CommentRepository;
import com.softuni.fitlaunch.repository.ExerciseRepository;
import com.softuni.fitlaunch.repository.WorkoutExerciseRepository;
import com.softuni.fitlaunch.repository.WorkoutRepository;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkoutService {
    private static final String BASE_IMAGES_PATH = "/images/";
    private final WorkoutRepository workoutRepository;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutExerciseRepository workoutExerciseRepository;
    private final ModelMapper modelMapper;


    public WorkoutService(WorkoutRepository workoutRepository, ExerciseRepository exerciseRepository, WorkoutExerciseRepository workoutExerciseRepository, ModelMapper modelMapper) {
        this.workoutRepository = workoutRepository;
        this.exerciseRepository = exerciseRepository;
        this.workoutExerciseRepository = workoutExerciseRepository;
        this.modelMapper = modelMapper;
    }

    public Long createWorkout(WorkoutEntity workout, CreateWorkoutDTO createWorkoutDTO) {


//        MultipartFile pictureFile = createWorkoutDTO.getImgUrl();
//
//        String picturePath = getPicturePath(pictureFile);
//
//
//        try {
//            File file = new File(BASE_IMAGES_PATH + picturePath);
//            file.getParentFile().mkdirs();
//            file.createNewFile();
//
//            OutputStream outputStream = new FileOutputStream(file);
//            outputStream.write(pictureFile.getBytes());
//
//        } catch(IOException ex) {
//            System.out.println(ex.getMessage());
//        }

//        workout.setImgUrl(picturePath);
        WorkoutEntity newWorkout = workoutRepository.save(workout);
        return newWorkout.getId();

    }

    public Page<WorkoutDTO> getAllWorkouts(Pageable pageable) {
        return workoutRepository
                .findAll(pageable)
                .map(entity -> modelMapper.map(entity, WorkoutDTO.class));
    }

//    public List<WorkoutDTO> getAllWorkouts() {
//        return workoutRepository.findAll().stream().map(workoutEntity -> modelMapper.map(workoutEntity, WorkoutDTO.class)).toList();
//    }

    public List<ExerciseDTO> getAllExercises() {
        return exerciseRepository.findAll()
                .stream()
                .map(entity -> modelMapper.map(entity, ExerciseDTO.class))
                .toList();
    }

    public List<LevelEnum> getAllLevels() {
        return Arrays.stream(LevelEnum.values()).collect(Collectors.toList());
    }

    public Optional<WorkoutDetailsDTO> getWorkoutDetails(Long workoutId) {
        Optional<WorkoutDetailsDTO> workoutDetailsDTO = workoutRepository.findById(workoutId)
                .map(this::mapAsDetails);

        return workoutDetailsDTO;
    }




    private WorkoutDetailsDTO mapAsDetails(WorkoutEntity workoutEntity) {

//        List<ExerciseDTO> exercises = workoutEntity.getExercises().stream().map(WorkoutService::mapAsExerciseDTO).toList();
        List<WorkoutExerciseEntity> exercises = workoutExerciseRepository.findByWorkoutId(workoutEntity.getId()).stream().toList();
//        List<CommentDTO> comments = commentRepository.findByWorkoutId(workoutEntity.getId()).stream().map(commentEntity -> modelMapper.map(commentEntity, CommentDTO.class)).toList();


        return new WorkoutDetailsDTO(
                workoutEntity.getId(),
                workoutEntity.getName(),
                workoutEntity.getLevel(),
                workoutEntity.getDescription(),
                workoutEntity.getImgUrl(),
                exercises
        );
    }

//    private WorkoutEntity map(CreateWorkoutDTO workoutDTO) {
//
//        List<ExerciseEntity> exercises = workoutDTO.getExercises().stream().map(WorkoutService::mapAsExerciseEntity).toList();
//
//        MultipartFile pictureFile = workoutDTO.getImgUrl();
//
//        String picturePath = getPicturePath(pictureFile);
//
//
//        try {
//            File file = new File(BASE_IMAGES_PATH + picturePath);
//            file.getParentFile().mkdirs();
//            file.createNewFile();
//
//            OutputStream outputStream = new FileOutputStream(file);
//            outputStream.write(pictureFile.getBytes());
//
//        } catch(IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//
//
//        return new WorkoutEntity()
//                .setName(workoutDTO.getName())
//                .setLevel(workoutDTO.getLevel())
//                .setDescription(workoutDTO.getDescription())
//                .setImgUrl(picturePath)
//                .setExercises(exercises);
//
//    }

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
                exerciseEntity.getName(),
                exerciseEntity.getSets(),
                exerciseEntity.getSets(),
                exerciseEntity.getVideoUrl()
        );
    }

    private static ExerciseEntity mapAsExerciseEntity(ExerciseDTO exerciseDTO) {
        return new ExerciseEntity()
                .setId(exerciseDTO.getId())
                .setName(exerciseDTO.getName());
    }

}
