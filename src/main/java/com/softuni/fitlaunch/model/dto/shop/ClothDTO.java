package com.softuni.fitlaunch.model.dto.shop;

import com.softuni.fitlaunch.model.entity.ShopEntity;
import com.softuni.fitlaunch.model.enums.GenderEnum;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

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

    @NotNull
    private GenderEnum gender;


    private List<ShopDTO> shops;


    public ClothDTO() {
    }

    public ClothDTO(Long id, String name, String imgUrl, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public List<ShopDTO> getShops() {
        return shops;
    }

    public void setShops(List<ShopDTO> shops) {
        this.shops = shops;
    }
}
