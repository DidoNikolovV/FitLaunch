package com.softuni.fitlaunch.web;


import com.softuni.fitlaunch.model.entity.ProgramEntity;
import com.softuni.fitlaunch.service.ProgramService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/programs")
public class ProgramController {

    private final ProgramService programService;

    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping
    public String loadAllPrograms(Model model) {

        List<ProgramEntity> allPrograms = programService.loadAllPrograms();

        model.addAttribute("allPrograms", allPrograms);

        return "programs";
    }


}
