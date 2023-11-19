package com.softuni.fitlaunch.service;


import com.softuni.fitlaunch.model.dto.ExerciseDTO;
import com.softuni.fitlaunch.model.entity.ExerciseEntity;
import com.softuni.fitlaunch.model.entity.WorkoutExerciseEntity;
import com.softuni.fitlaunch.repository.ExerciseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;

    private final ModelMapper modelMapper;

    public ExerciseService(ExerciseRepository exerciseRepository, ModelMapper modelMapper) {
        this.exerciseRepository = exerciseRepository;
        this.modelMapper = modelMapper;
    }

    public List<ExerciseEntity> getExercisesByIds(List<Long> ids) {
        return exerciseRepository.findAllById(ids);

    }

    public ExerciseEntity getExerciseById(Long id) {
       return exerciseRepository.findById(id).orElse(null);
    }

    public void saveExercise(ExerciseEntity exercise) {
        exerciseRepository.save(exercise);
    }


}
