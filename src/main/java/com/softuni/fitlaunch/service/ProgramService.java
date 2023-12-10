package com.softuni.fitlaunch.service;



import com.softuni.fitlaunch.model.dto.program.ProgramWeekDTO;
import com.softuni.fitlaunch.model.dto.program.ProgramWeekWorkoutDTO;
import com.softuni.fitlaunch.model.entity.ProgramEntity;
import com.softuni.fitlaunch.model.entity.ProgramWeekEntity;
import com.softuni.fitlaunch.model.entity.ProgramWeekWorkoutEntity;
import com.softuni.fitlaunch.repository.ProgramRepository;
import com.softuni.fitlaunch.repository.ProgramWeekRepository;
import com.softuni.fitlaunch.repository.ProgramWeekWorkoutRepository;
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

    private final ModelMapper modelMapper;


    public ProgramService(ProgramRepository programRepository, ProgramWeekRepository programWeekRepository, ProgramWeekWorkoutRepository programWeekWorkoutRepository, ModelMapper modelMapper) {
        this.programRepository = programRepository;
        this.programWeekRepository = programWeekRepository;
        this.programWeekWorkoutRepository = programWeekWorkoutRepository;
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
}
