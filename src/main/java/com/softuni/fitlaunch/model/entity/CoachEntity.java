package com.softuni.fitlaunch.model.entity;

import com.softuni.fitlaunch.model.enums.UserRoleEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "coaches")
public class CoachEntity extends BaseEntity{


    @Column(unique = true)
    private String username;

    @Email
    private String email;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(columnDefinition = "FLOAT DEFAULT 1.0")
    private Double rating;

    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    @Length(min = 20, max = 200)
    private String description;


    @OneToMany(cascade = CascadeType.ALL)
    private List<CertificateEntity> certificates;

    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL)
    private List<ProgramEntity> programs;

    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL)
    private List<ClientEntity> clients;

    @OneToMany(mappedBy = "coach", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<ScheduledWorkoutEntity> scheduledWorkouts;


}
