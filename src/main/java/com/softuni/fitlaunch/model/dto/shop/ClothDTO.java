package com.softuni.fitlaunch.model.dto.shop;

import com.softuni.fitlaunch.model.entity.ShopEntity;
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

    private String category;

    @NotNull

    private BigDecimal price;

    public ClothDTO() {
    }

    public ClothDTO(String name, String imgUrl, String category, BigDecimal price, ShopDTO shop) {
        this.name = name;
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
