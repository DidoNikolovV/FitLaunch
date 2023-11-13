package com.softuni.fitlaunch.model.dto.shop;

import com.softuni.fitlaunch.model.entity.ShopEntity;
import com.softuni.fitlaunch.model.enums.GenderEnum;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ClothDTO {

    @NotNull
    private Long id;

    @NotNull
    private String name;


    @NotNull
    private String imgUrl;

    @NotNull
    private String description;

    @NotNull
    private BigDecimal price;


    public ClothDTO() {
    }

    public ClothDTO(String name, String imgUrl, String description, BigDecimal price) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.description = description;
        this.price = price;
    }


    public Long getId() {
        return id;
    }

    public ClothDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ClothDTO setName(String name) {
        this.name = name;
        return this;
    }



    public String getImgUrl() {
        return imgUrl;
    }

    public ClothDTO setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ClothDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ClothDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }


}
