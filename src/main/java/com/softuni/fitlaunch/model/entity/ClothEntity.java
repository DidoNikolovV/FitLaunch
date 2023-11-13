package com.softuni.fitlaunch.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "clothes")
public class ClothEntity extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String imgUrl;


    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String description;

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

    public String getDescription() {
        return description;
    }

    public ClothEntity setDescription(String description) {
        this.description = description;
        return this;
    }


}
