package com.softuni.fitlaunch.model.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "programs")
public class ProgramEntity extends BaseEntity {

    @Column(nullable = false)
    private String imgUrl;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL)
    private List<WeekEntity> weeks;

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

    public List<WeekEntity> getWeeks() {
        return weeks;
    }

    public ProgramEntity setWeeks(List<WeekEntity> weeks) {
        this.weeks = weeks;
        return this;
    }
}
