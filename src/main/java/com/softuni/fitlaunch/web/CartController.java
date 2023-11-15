package com.softuni.fitlaunch.web;

import com.softuni.fitlaunch.model.dto.shop.CartDTO;
import com.softuni.fitlaunch.model.dto.shop.ClothDTO;
import com.softuni.fitlaunch.model.entity.CartEntity;
import com.softuni.fitlaunch.service.CartService;
import com.softuni.fitlaunch.service.CustomUserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public String viewCart(Principal principal, Model model) {


//        if(principal instanceof CustomUserDetails) {
//            Long userId = ((CustomUserDetails) principal).getId();
//            List<ClothDTO> cartItems = cartService.getCartItems(userId);
//            model.addAttribute("cartItems", cartItems);
//            model.addAttribute("userId", userId);
//        }


        return "cart";
    }
}
