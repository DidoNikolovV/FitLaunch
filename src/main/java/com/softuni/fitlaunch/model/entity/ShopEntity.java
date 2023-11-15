package com.softuni.fitlaunch.model.entity;


import com.softuni.fitlaunch.model.enums.GenderEnum;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shop")
public class ShopEntity extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private ShopGenderEntity gender;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name= "shop_clothes",
            joinColumns = @JoinColumn(name = "shop_id"),
            inverseJoinColumns = @JoinColumn(name = "cloth_id")
    )
    private List<ClothEntity> clothes = new ArrayList<>();

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public ShopEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public ShopGenderEntity getGender() {
        return gender;
    }

    public ShopEntity setGender(ShopGenderEntity gender) {
        this.gender = gender;
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
