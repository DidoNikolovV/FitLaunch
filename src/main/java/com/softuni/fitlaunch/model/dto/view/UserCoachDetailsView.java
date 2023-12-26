package com.softuni.fitlaunch.model.dto.view;


import com.softuni.fitlaunch.model.dto.CertificateDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserCoachDetailsView {

    private Long id;
    private String imgUrl;
    private String username;
    private String email;
    private String description;

    private List<CertificateDTO> certificates;

    private Double rating;

}
