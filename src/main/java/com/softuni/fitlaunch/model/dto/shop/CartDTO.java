package com.softuni.fitlaunch.model.dto.shop;

import com.softuni.fitlaunch.model.dto.user.UserDTO;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CartDTO {

    private Long id;

    private UserDTO user;

    private List<ClothDTO> clothes;

    public CartDTO() {
        this.clothes = new ArrayList<>();
    }

    public CartDTO(Long id, List<ClothDTO> clothes, UserDTO user) {
        this.id = id;
        this.clothes = clothes;
        this.user = user;
    }

    public List<ClothDTO> getClothes() {
        return new ArrayList<>(clothes); // Return a copy of the list
    }

    public void setClothes(List<ClothDTO> clothes) {
        this.clothes = new ArrayList<>(clothes);
    }

    public Long getId() {
        return id;
    }

    public CartDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public UserDTO getUser() {
        return user;
    }

    public CartDTO setUser(UserDTO user) {
        this.user = user;
        return this;
    }
}
