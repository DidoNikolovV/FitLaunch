package com.softuni.fitlaunch.service;


import com.softuni.fitlaunch.model.dto.shop.ClothDTO;
import com.softuni.fitlaunch.model.entity.ClothEntity;
import com.softuni.fitlaunch.model.enums.GenderEnum;
import com.softuni.fitlaunch.repository.ClothRepository;
import com.softuni.fitlaunch.repository.ShopRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShopService {
    private final ShopRepository shopRepository;
    private final ClothRepository clothRepository;



    public ShopService(ShopRepository shopRepository, ClothRepository clothRepository) {
        this.shopRepository = shopRepository;
        this.clothRepository = clothRepository;
    }

    public Page<ClothDTO> getMenClothes(Long shopId, Pageable pageable) {
        return clothRepository.findByShopId(shopId, pageable).map(ShopService::mapAsClothDTO);
    }

    public Page<ClothDTO> getWomenClothes(Long shopId, Pageable pageable) {
        return clothRepository.findByShopId(shopId, pageable).map(ShopService::mapAsClothDTO);
    }

    public Optional<ClothEntity> getClothById(Long id) {
        return clothRepository.findById(id);

    }

    private static ClothDTO mapAsClothDTO(ClothEntity clothEntity) {

        return new ClothDTO(
                clothEntity.getId(),
                clothEntity.getName(),
                clothEntity.getImgUrl(),
                clothEntity.getDescription(),
                clothEntity.getPrice()

        );
    }


}


