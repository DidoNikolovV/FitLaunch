package com.softuni.fitlaunch.service;



import com.softuni.fitlaunch.model.dto.program.ProgramWeekDTO;
import com.softuni.fitlaunch.model.dto.program.ProgramWeekWorkoutDTO;
import com.softuni.fitlaunch.model.dto.user.UserDTO;
import com.softuni.fitlaunch.model.entity.ProgramEntity;
import com.softuni.fitlaunch.model.entity.ProgramWeekEntity;
import com.softuni.fitlaunch.model.entity.ProgramWeekWorkoutEntity;
import com.softuni.fitlaunch.model.entity.UserEntity;
import com.softuni.fitlaunch.repository.ProgramRepository;
import com.softuni.fitlaunch.repository.ProgramWeekRepository;
import com.softuni.fitlaunch.repository.ProgramWeekWorkoutRepository;
import com.softuni.fitlaunch.repository.UserRepository;
import com.softuni.fitlaunch.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProgramService {

    private final ProgramRepository programRepository;

    private final ProgramWeekRepository programWeekRepository;

    private final ProgramWeekWorkoutRepository programWeekWorkoutRepository;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;


    public ProgramService(ProgramRepository programRepository, ProgramWeekRepository programWeekRepository, ProgramWeekWorkoutRepository programWeekWorkoutRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.programRepository = programRepository;
        this.programWeekRepository = programWeekRepository;
        this.programWeekWorkoutRepository = programWeekWorkoutRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public List<ProgramEntity> loadAllPrograms() {
        return programRepository.findAll();
    }

    public List<ProgramWeekDTO> getAllWeeksByProgramId(Long programId) {
        List<ProgramWeekEntity> programWeeks = programWeekRepository.findAllByProgramId(programId).orElseThrow(() -> new ObjectNotFoundException("Program with id " + programId + " was not found"));

        List<ProgramWeekDTO> programWeeksDTO = programWeeks.stream().map(programWeekEntity -> modelMapper.map(programWeekEntity, ProgramWeekDTO.class)).toList();

        return programWeeksDTO;
    }

    public ProgramWeekDTO getProgramWeekById(Long weekId) {
        ProgramWeekEntity programWeekEntity = programWeekRepository.findById(weekId).orElseThrow(() -> new ObjectNotFoundException("Week with id " + weekId + " was not found"));

        ProgramWeekDTO programWeekWorkoutDTO = modelMapper.map(programWeekEntity, ProgramWeekDTO.class);

        return programWeekWorkoutDTO;

    }

    public ProgramWeekWorkoutDTO getProgramWeekWorkoutById(Long id) {
        ProgramWeekWorkoutEntity programWeekWorkoutEntity = programWeekWorkoutRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Workout not found"));
        ProgramWeekWorkoutDTO programWeekWorkoutDTO = modelMapper.map(programWeekWorkoutEntity, ProgramWeekWorkoutDTO.class);

        return programWeekWorkoutDTO;
    }

    public ProgramEntity getById(Long programId) {
        return programRepository.findById(programId).orElseThrow(() -> new ObjectNotFoundException("Program with id " + programId + " not found"));
    }

    public ProgramWeekWorkoutDTO getProgramWorkout(Long programId, Long weekId, Long workoutId, UserDTO userDTO) {
        ProgramWeekWorkoutEntity programWeekWorkout = programWeekWorkoutRepository.findByProgramWeekIdAndId(weekId, workoutId).orElseThrow(() -> new ObjectNotFoundException("Workout was not found"));
        ProgramWeekWorkoutDTO programWeekWorkoutDTO = modelMapper.map(programWeekWorkout, ProgramWeekWorkoutDTO.class);

        programWeekWorkout.setHasStarted(true);
        UserEntity user = userRepository.findByUsername(userDTO.getUsername()).orElseThrow(() -> new ObjectNotFoundException("User with " + userDTO.getUsername() + " doesn't exist"));
        user.getWorkoutsStarted().add(programWeekWorkout);

        programWeekWorkoutRepository.save(programWeekWorkout);
        userRepository.save(user);

        return programWeekWorkoutDTO;
    }
}
