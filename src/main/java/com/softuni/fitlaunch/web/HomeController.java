package com.softuni.fitlaunch.web;


import com.softuni.fitlaunch.repository.CartRepository;
import com.softuni.fitlaunch.service.CartService;
import com.softuni.fitlaunch.service.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index";
    }


}
