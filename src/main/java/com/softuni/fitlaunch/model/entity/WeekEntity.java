package com.softuni.fitlaunch.model.entity;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "weeks")
public class WeekEntity extends BaseEntity {

    @OneToMany(mappedBy = "week", cascade = CascadeType.ALL)
    private List<DayEntity> days;

    @ManyToOne
    private ProgramEntity program;

    public List<DayEntity> getDays() {
        return days;
    }

    public WeekEntity setDays(List<DayEntity> days) {
        this.days = days;
        return this;
    }

    public ProgramEntity getProgram() {
        return program;
    }

    public WeekEntity setProgram(ProgramEntity program) {
        this.program = program;
        return this;
    }
}
