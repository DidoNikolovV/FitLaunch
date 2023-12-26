package com.softuni.fitlaunch.model.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "programs")
public class ProgramEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String imgUrl;

    @Column(nullable = false)
    private String name;

    public Long getId() {
        return id;
    }

    public ProgramEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public ProgramEntity setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProgramEntity setName(String name) {
        this.name = name;
        return this;
    }

}
