package com.softuni.fitlaunch.service;


import com.softuni.fitlaunch.model.dto.ExerciseDTO;
import com.softuni.fitlaunch.model.entity.ExerciseEntity;
import com.softuni.fitlaunch.repository.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public List<ExerciseDTO> getExercisesByIds(List<Long> ids) {
        List<ExerciseEntity> exercisesById = exerciseRepository.findAllById(ids);

        return exercisesById.stream().map(ExerciseService::mapAsExerciseDTO).collect(Collectors.toList());
    }

    private static ExerciseDTO mapAsExerciseDTO(ExerciseEntity exerciseEntity) {
        return new ExerciseDTO(
                exerciseEntity.getId(),
                exerciseEntity.getName()
        );
    }
}
