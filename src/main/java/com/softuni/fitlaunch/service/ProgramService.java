package com.softuni.fitlaunch.service;


import com.softuni.fitlaunch.model.entity.ProgramEntity;
import com.softuni.fitlaunch.repository.ProgramRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramService {

    private final ProgramRepository programRepository;

    public ProgramService(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    public List<ProgramEntity> loadAllPrograms() {
        return programRepository.findAll();
    }
}
