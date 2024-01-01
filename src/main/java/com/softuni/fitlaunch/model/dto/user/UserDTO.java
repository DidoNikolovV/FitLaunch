package com.softuni.fitlaunch.model.dto.user;

import com.softuni.fitlaunch.model.dto.comment.CommentCreationDTO;
import com.softuni.fitlaunch.model.dto.program.ProgramWeekWorkoutDTO;
import com.softuni.fitlaunch.model.dto.program.ProgramWorkoutExerciseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private Long id;
    private String username;

    private String email;

    private List<UserRoleDTO> roles;

    private String membership;


    private List<CommentCreationDTO> comments;

    private List<ProgramWeekWorkoutDTO> workoutsCompleted;

    private List<ProgramWeekWorkoutDTO> workoutStarted;

    private List<ProgramWeekWorkoutDTO> workoutsLiked;


    private List<ProgramWorkoutExerciseDTO> exercisesCompleted;

    private boolean activated = false;

    private String imgUrl;

}