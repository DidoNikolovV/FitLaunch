package com.softuni.fitlaunch.model.dto.program;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

public class ProgramDTO {

    @NotNull
    private Long id;

    @NotNull
    private String imgUrl;

    @NotNull
    private String name;

    public ProgramDTO() {
    }

    public ProgramDTO(Long id) {
        this.id = id;
    }

    public ProgramDTO(Long id, String imgUrl, String name) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public ProgramDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public ProgramDTO setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProgramDTO setName(String name) {
        this.name = name;
        return this;
    }
}
