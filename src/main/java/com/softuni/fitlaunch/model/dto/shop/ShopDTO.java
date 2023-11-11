package com.softuni.fitlaunch.model.dto.shop;

import com.softuni.fitlaunch.model.entity.ClothEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class ShopDTO {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    private List<ClothDTO> clothes;

    public ShopDTO() {
    }

    public ShopDTO(String name, List<ClothDTO> clothes) {
        this.name = name;
        this.clothes = clothes;
    }



    public ShopDTO(Long id, String name, List<ClothDTO> clothes) {
        this.id = id;
        this.name = name;
        this.clothes = clothes;
    }

    public Long getId() {
        return id;
    }

    public ShopDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ShopDTO setName(String name) {
        this.name = name;
        return this;
    }

    public List<ClothDTO> getClothes() {
        return clothes;
    }

    public ShopDTO setClothes(List<ClothDTO> clothes) {
        this.clothes = clothes;
        return this;
    }


}
