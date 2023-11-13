package com.softuni.fitlaunch.service;


import com.softuni.fitlaunch.model.dto.shop.ClothDTO;
import com.softuni.fitlaunch.model.entity.ClothEntity;
import com.softuni.fitlaunch.repository.ClothRepository;
import com.softuni.fitlaunch.repository.ShopRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ShopService {
    private final ShopRepository shopRepository;
    private final ClothRepository clothRepository;


    public ShopService(ShopRepository shopRepository, ClothRepository clothRepository) {
        this.shopRepository = shopRepository;
        this.clothRepository = clothRepository;
    }

    public Page<ClothDTO> getMenClothes(Pageable pageable) {
        return clothRepository
                .findAll(pageable)
                .map(ShopService::mapAsClothDTO);
    }

    private static ClothDTO mapAsClothDTO(ClothEntity clothEntity) {
        return new ClothDTO(
                clothEntity.getName(),
                clothEntity.getImgUrl(),
                clothEntity.getDescription(),
                clothEntity.getPrice()
        );
    }

}


