package com.softuni.fitlaunch.web;


import com.softuni.fitlaunch.model.entity.ProgramEntity;
import com.softuni.fitlaunch.model.entity.UserEntity;
import com.softuni.fitlaunch.model.entity.WeekEntity;
import com.softuni.fitlaunch.service.ProgramService;
import com.softuni.fitlaunch.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/programs")
public class ProgramController {

    private final ProgramService programService;

    private final UserService userService;

    public ProgramController(ProgramService programService, UserService userService) {
        this.programService = programService;
        this.userService = userService;
    }

    @GetMapping
    public String loadAllPrograms(Model model, Principal principal) {

        List<ProgramEntity> allPrograms = programService.loadAllPrograms();
        UserEntity loggedUser = userService.getUserByUsername(principal.getName());

        model.addAttribute("membership", loggedUser.getMembership());
        model.addAttribute("allPrograms", allPrograms);

        return "programs";
    }

    @GetMapping("/{programId}")
    public String loadProgramById(@PathVariable("programId") Long id, Model model) {
        ProgramEntity programEntity = programService.loadProgramById(id);

        model.addAttribute("program", programEntity);

        return "program-details";

    }


}
