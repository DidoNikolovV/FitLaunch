package com.softuni.fitlaunch.model.dto.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserProfileDTO {

    private MultipartFile imgUrl;
    private String username;
    private String email;
    private String membership;
}
