package com.softuni.fitlaunch.model.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "shop")
public class ShopEntity extends BaseEntity {


    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private List<ClothEntity> clothes;

    public String getName() {
        return name;
    }

    public ShopEntity setName(String name) {
        this.name = name;
        return this;
    }

    public List<ClothEntity> getClothes() {
        return clothes;
    }

    public ShopEntity setClothes(List<ClothEntity> clothes) {
        this.clothes = clothes;
        return this;
    }
}
