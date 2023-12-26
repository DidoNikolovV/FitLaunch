package com.softuni.fitlaunch.model.dto.program;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class ProgramWeekDTO {

    @NotNull
    private Long id;

    @NotNull
    private ProgramDTO program;


    private List<ProgramWeekWorkoutDTO> weekWorkouts;

    public Long getId() {
        return id;
    }

    public ProgramWeekDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public ProgramDTO getProgram() {
        return program;
    }

    public ProgramWeekDTO setProgram(ProgramDTO program) {
        this.program = program;
        return this;
    }

    public List<ProgramWeekWorkoutDTO> getWeekWorkouts() {
        return weekWorkouts;
    }

    public ProgramWeekDTO setWeekWorkouts(List<ProgramWeekWorkoutDTO> weekWorkouts) {
        this.weekWorkouts = weekWorkouts;
        return this;
    }
}
