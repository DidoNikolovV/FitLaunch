package com.softuni.fitlaunch.web;


import com.softuni.fitlaunch.model.dto.user.ClientDTO;
import com.softuni.fitlaunch.model.dto.user.ClientDetailsDTO;
import com.softuni.fitlaunch.model.dto.user.CoachDTO;
import com.softuni.fitlaunch.model.dto.user.UserRegisterDTO;
import com.softuni.fitlaunch.model.dto.view.UserCoachDetailsView;
import com.softuni.fitlaunch.model.dto.view.UserCoachView;
import com.softuni.fitlaunch.service.CoachService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{coachId}/client/information")
    public String coachDetails(@PathVariable("coachId") Long coachId, Principal principal, Model model) {
        ClientDTO client = coachService.getClientByUsername(principal.getName());

        CoachDTO coachById = coachService.getCoachById(coachId);
        model.addAttribute("coach", coachById);
        model.addAttribute("client", client);
//        coachService.addClient(coachId, client);
        return "client-information-form";
    }

    @PostMapping("/{coachId}/client/information")
    public String clientDetails(@PathVariable("coachId") Long coachId,@ModelAttribute("clientDTO") @Valid ClientDetailsDTO clientDetailsDTO, Principal principal) {
        coachService.setClientDetails(principal.getName(), clientDetailsDTO);

        ClientDTO client = coachService.getClientByUsername(principal.getName());
        coachService.addClient(coachId, client);
        return "redirect:/";
    }

    @GetMapping("/clients/all")
    public String coachAllClients(Model model, Principal principal) {
        CoachDTO coach = coachService.getCoachByUsername(principal.getName());

        model.addAttribute("coach", coach);

        return "clients";
    }

    @GetMapping("/clients/{clientId}")
    public String coachClientDetails(@PathVariable("clientId") Long id, Principal principal, Model model) {
        CoachDTO coach = coachService.getCoachByUsername(principal.getName());
        ClientDTO coachClientById = coachService.getCoachClientById(coach, id);

        model.addAttribute("client", coachClientById);


        return "client-details";


    }
}
