package com.softuni.fitlaunch.model.dto.user;

import com.softuni.fitlaunch.model.dto.CertificateDTO;
import com.softuni.fitlaunch.model.dto.program.ProgramDTO;
import com.softuni.fitlaunch.model.enums.UserRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CoachDTO {

    private String username;

    private String email;

    private String imgUrl;

    private Double rating;
    private String description;

    private UserRoleEnum role;

    private List<CertificateDTO> certificates;

    private List<ProgramDTO> programs;

    private List<ClientDTO> clients;
}
