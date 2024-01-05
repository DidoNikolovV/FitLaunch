package com.softuni.fitlaunch.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "clients")
public class ClientEntity extends BaseEntity {

    @Column(unique = true)
    private String username;

    @Email
    private String email;

    @Column(name = "img_url")
    private String imgUrl;


    private Double weight;
    private Double height;
    private String targetGoals;

    private String dietaryPreferences;

    @ManyToOne
    private CoachEntity coach;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<ScheduledWorkoutEntity> scheduledWorkouts;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<ProgramEntity> completedPrograms;

}
