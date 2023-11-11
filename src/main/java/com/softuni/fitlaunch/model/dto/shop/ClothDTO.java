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
    private GenderEnum gender;

    @NotNull

    private String imgUrl;

    @NotNull

    private String category;

    @NotNull

    private BigDecimal price;

    public ClothDTO() {
    }

    public ClothDTO(Long id, GenderEnum gender, String imgUrl, String category, BigDecimal price, ShopDTO shop) {
        this.id = id;
        this.gender = gender;
        this.imgUrl = imgUrl;
        this.category = category;
        this.price = price;
        this.shop = shop;
    }

    public Long getId() {
        return id;
    }

    public ClothDTO setId(Long id) {
        this.id = id;
        return this;
    }

    private ShopDTO shop;

    public GenderEnum getGender() {
        return gender;
    }

    public ClothDTO setName(GenderEnum gender) {
        this.gender = gender;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public ClothDTO setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public ClothDTO setCategory(String category) {
        this.category = category;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ClothDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public ShopDTO getShop() {
        return shop;
    }

    public ClothDTO setShop(ShopDTO shop) {
        this.shop = shop;
        return this;
    }
}
