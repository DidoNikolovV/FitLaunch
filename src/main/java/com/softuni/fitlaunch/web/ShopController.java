package com.softuni.fitlaunch.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopController {

    @GetMapping("/shop")
    public String shop() {
        return "shop";
    }

    @GetMapping("/shop-details")
    public String shopDetails() {
        return "shop-details";
    }
}
