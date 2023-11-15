package com.softuni.fitlaunch.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "cart")
public class CartEntity extends BaseEntity {

    private Long id;

    @OneToOne
    @JoinColumn(name= "user_id", referencedColumnName = "id")
    private UserEntity user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name= "cart_clothes",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "cloth_id")
    )
    private List<ClothEntity> clothes;


    public Long getId() {
        return id;
    }

    public CartEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public CartEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public List<ClothEntity> getClothes() {
        return clothes;
    }

    public CartEntity setClothes(List<ClothEntity> clothes) {
        this.clothes = clothes;
        return this;
    }
}
