package com.softuni.fitlaunch.web;


import com.softuni.fitlaunch.model.dto.user.ClientDTO;
import com.softuni.fitlaunch.model.dto.user.CoachDTO;
import com.softuni.fitlaunch.model.dto.view.UserCoachDetailsView;
import com.softuni.fitlaunch.model.dto.view.UserCoachView;
import com.softuni.fitlaunch.service.CoachService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/coaches")
public class CoachController {

    private final CoachService coachService;

    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    @GetMapping("/all")
    public String allCoaches(Model model) {
        List<UserCoachView> allCoaches = coachService.getAllCoaches();

        model.addAttribute("allCoaches", allCoaches);

        return "coaches";
    }

    @GetMapping("/{id}")
    public String coachDetails(@PathVariable("id") Long id, Model model) {
        UserCoachDetailsView coachDetailsView = coachService.getCoachDetailsById(id);

        model.addAttribute("coachDetails", coachDetailsView);

        return "coach-details";
    }

    @PostMapping("/{id}")
    public String coachDetails(@PathVariable("id") Long id, Model model, Principal principal) {
        ClientDTO client = coachService.getClientByUsername(principal.getName());

        coachService.addClient(id, client);


        return "redirect:/";
    }
}
