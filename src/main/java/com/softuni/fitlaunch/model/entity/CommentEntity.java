package com.softuni.fitlaunch.model.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class CommentEntity extends BaseEntity{

    @ManyToOne
    private UserEntity author;

    @ManyToOne
    private ProgramWeekWorkoutEntity workout;

    @ManyToOne
    private ProgramEntity program;

    @ManyToOne
    private ProgramWeekEntity week;

    @Column(nullable = false)
    private String content;

    public UserEntity getAuthor() {
        return author;
    }

    public CommentEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public String getContent() {
        return content;
    }

    public CommentEntity setContent(String content) {
        this.content = content;
        return this;
    }

    public ProgramWeekWorkoutEntity getWorkout() {
        return workout;
    }

    public CommentEntity setWorkout(ProgramWeekWorkoutEntity workout) {
        this.workout = workout;
        return this;
    }

    public ProgramEntity getProgram() {
        return program;
    }

    public CommentEntity setProgram(ProgramEntity program) {
        this.program = program;
        return this;
    }

    public ProgramWeekEntity getWeek() {
        return week;
    }

    public CommentEntity setWeek(ProgramWeekEntity week) {
        this.week = week;
        return this;
    }
}
