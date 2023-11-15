package com.softuni.fitlaunch.service;

import com.softuni.fitlaunch.model.dto.shop.CartDTO;
import com.softuni.fitlaunch.model.dto.shop.ClothDTO;
import com.softuni.fitlaunch.model.dto.user.UserDTO;
import com.softuni.fitlaunch.model.entity.*;
import com.softuni.fitlaunch.repository.CartRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

//    public List<ClothDTO> getCartItems(Long id) {
//        CartDTO cart = getCartById(id);
//
//        return cart.getClothes();
//    }

    public Optional<CartEntity> getCartById(Long id) {
        return cartRepository.findById(id);
    }


//    public void addCloth(Long cartId, ClothDTO clothDTO) {
//        CartDTO cartDTO = getCartById(cartId);
//
//        cartDTO.getClothes().add(clothDTO);
//
//    }

    public void createCart() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = ((CustomUserDetails) authentication.getPrincipal()).getId();
        CartEntity cartEntity = new CartEntity();
        cartEntity.setId(userId);
        cartEntity.setClothes(new ArrayList<>());
        cartRepository.save(new CartEntity());
    }

    public Long updateCart(CartEntity cartEntity) {
        cartRepository.save(cartEntity);

        return cartEntity.getId();

    }

    private static CartEntity mapAsCartEntity(CartDTO cartDTO) {
        return new CartEntity()
                .setId(cartDTO.getId())
                .setUser(CartService.mapAsUserEntity(cartDTO.getUser()))
                .setClothes(cartDTO.getClothes().stream().map(CartService::mapClothAsEntity).collect(Collectors.toList()));
    }


    private static CartDTO mapAsDTO(CartEntity cartEntity) {

        List<ClothDTO> cloths = cartEntity.getClothes().stream().map(CartService::mapClothAsDTO).toList();


        return new CartDTO(
                cartEntity.getId(),
                cloths,
                CartService.mapAsUserDTO(cartEntity.getUser())
        );
    }


    private static ClothDTO mapClothAsDTO(ClothEntity clothEntity) {
        return new ClothDTO(
                clothEntity.getId(),
                clothEntity.getName(),
                clothEntity.getImgUrl(),
                clothEntity.getDescription(),
                clothEntity.getPrice()
        );
    }

    private static ClothEntity mapClothAsEntity(ClothDTO clothDTO) {
        return new ClothEntity()
                .setId(clothDTO.getId())
                .setName(clothDTO.getName())
                .setImgUrl(clothDTO.getImgUrl())
                .setPrice(clothDTO.getPrice())
                .setDescription(clothDTO.getDescription());

    }

    private static UserDTO mapAsUserDTO(UserEntity userEntity) {
        return new UserDTO(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.getRoles()
        );
    }

    private static UserEntity mapAsUserEntity(UserDTO userDTO) {
        return new UserEntity()
                .setId(userDTO.getId())
                .setUsername(userDTO.getUsername())
                .setEmail(userDTO.getEmail())
                .setRoles(userDTO.getRoles());
    }

}
