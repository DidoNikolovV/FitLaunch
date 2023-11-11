package com.softuni.fitlaunch.model.entity;

import com.softuni.fitlaunch.model.enums.GenderEnum;
import com.softuni.fitlaunch.model.enums.UserRoleEnum;
import jakarta.persistence.*;


@Entity
@Table(name = "genders")
public class ShopGenderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    public Long getId() {
        return id;
    }

    public ShopGenderEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public ShopGenderEntity setGender(GenderEnum gender) {
        this.gender = gender;
        return this;
    }
}
