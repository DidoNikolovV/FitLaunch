package com.softuni.fitlaunch.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;


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

    @ManyToOne
    private CoachEntity coach;

}
