package com.softuni.fitlaunch.web;


import com.softuni.fitlaunch.model.dto.shop.CartDTO;
import com.softuni.fitlaunch.model.dto.shop.ClothDTO;
import com.softuni.fitlaunch.model.entity.CartEntity;
import com.softuni.fitlaunch.model.entity.ClothEntity;
import com.softuni.fitlaunch.service.CartService;
import com.softuni.fitlaunch.service.CustomUserDetails;
import com.softuni.fitlaunch.service.ShopService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class ShopController {

    private final ShopService shopService;
    private final CartService cartService;

    public ShopController(ShopService shopService, CartService cartService) {
        this.shopService = shopService;
        this.cartService = cartService;
    }

    @GetMapping("/shop")
    public String shop() {
        return "shop";
    }

    @GetMapping("/clothes-men")
    public String clothesMen(Model model,
                             @PageableDefault(
                                      size = 3,
                                      sort = "id"
                              ) Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Long shopId = 1L;

        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            Long userId = ((CustomUserDetails) authentication.getPrincipal()).getId();
            model.addAttribute("userId", userId);
        }

        Page<ClothDTO> menClothes = shopService.getMenClothes(shopId, pageable);

        model.addAttribute("menClothes", menClothes);

        return "clothes-men";
    }

    @PostMapping("/clothes-men")
    public String addCloth(
            @RequestParam("id") Long id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Long userId = ((CustomUserDetails) authentication.getPrincipal()).getId();

        CartEntity cartEntity = cartService.getCartById(userId).orElse(new CartEntity());

        // Retrieve the selected cloth
        ClothEntity clothEntity = shopService.getClothById(id).orElse(null);

        // Check if the cloth exists and add it to the cart
        if (clothEntity != null) {
            List<ClothEntity> clothes = cartEntity.getClothes();
            clothes.add(clothEntity);
            cartEntity.setClothes(clothes);

            // Update the cart in the database
            cartService.updateCart(cartEntity);
        }

        return "redirect:/clothes-men";
    }

    @GetMapping("/clothes-women")
    public String clothesWomen(Model model,
                             @PageableDefault(
                                     size = 3,
                                     sort = "id"
                             ) Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Long shopId = 2L;

        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            Long userId = ((CustomUserDetails) authentication.getPrincipal()).getId();
            model.addAttribute("userId", userId);
        }

        Page<ClothDTO> womenClothes = shopService.getWomenClothes(shopId, pageable);

        model.addAttribute("womenClothes", womenClothes);

        return "clothes-women";
    }
}
