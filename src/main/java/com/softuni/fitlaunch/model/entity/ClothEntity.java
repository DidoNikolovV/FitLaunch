package com.softuni.fitlaunch.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "cloths")
public class ClothEntity extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String imgUrl;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private ShopEntity shop;

    public String getName() {
        return name;
    }

    public ClothEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public ClothEntity setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public ClothEntity setCategory(String category) {
        this.category = category;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ClothEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public ShopEntity getShop() {
        return shop;
    }

    public ClothEntity setShop(ShopEntity shop) {
        this.shop = shop;
        return this;
    }
}
