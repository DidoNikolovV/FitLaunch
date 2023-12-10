package com.softuni.fitlaunch.model.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "programs_weeks")
public class ProgramWeekEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "program_id")
    private ProgramEntity program;


    @OneToMany(mappedBy = "programWeek", cascade = CascadeType.ALL)
    private List<ProgramWeekWorkoutEntity> weekWorkouts;


    public ProgramEntity getProgram() {
        return program;
    }

    public ProgramWeekEntity setProgram(ProgramEntity program) {
        this.program = program;
        return this;
    }

    public List<ProgramWeekWorkoutEntity> getWeekWorkouts() {
        return weekWorkouts;
    }

    public ProgramWeekEntity setWeekWorkouts(List<ProgramWeekWorkoutEntity> weekWorkouts) {
        this.weekWorkouts = weekWorkouts;
        return this;
    }
}
