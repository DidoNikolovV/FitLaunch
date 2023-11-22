package com.softuni.fitlaunch.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comments")
public class CommentEntity extends BaseEntity{

    @ManyToOne
    private UserEntity author;

    @ManyToOne
    private WorkoutEntity workout;

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

    public WorkoutEntity getWorkout() {
        return workout;
    }

    public CommentEntity setWorkout(WorkoutEntity workout) {
        this.workout = workout;
        return this;
    }
}
