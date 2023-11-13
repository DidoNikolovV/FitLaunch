package com.softuni.fitlaunch.web;


import com.softuni.fitlaunch.model.dto.shop.ClothDTO;
import com.softuni.fitlaunch.service.ShopService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/shop")
    public String shop() {
        return "shop";
    }

    @GetMapping("/clothes-men")
    public String shopDetails(Model model,
                              @PageableDefault(
                                      size = 6,
                                      sort = "id"
                              ) Pageable pageable) {

        Page<ClothDTO> menClothes = shopService.getMenClothes(pageable);

        model.addAttribute("menClothes", menClothes);

        return "clothes-men";
    }
}
