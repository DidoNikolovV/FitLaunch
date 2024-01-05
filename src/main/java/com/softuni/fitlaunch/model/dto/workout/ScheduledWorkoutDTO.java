package com.softuni.fitlaunch.model.dto.workout;


import com.softuni.fitlaunch.model.dto.program.ProgramWeekWorkoutDTO;
import com.softuni.fitlaunch.model.dto.user.ClientDTO;
import com.softuni.fitlaunch.model.dto.user.CoachDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ScheduledWorkoutDTO {

    private Long id;

    private ClientDTO client;
    private CoachDTO coach;

    private ProgramWeekWorkoutDTO programWeekWorkoutDTO;

    private LocalDateTime scheduledDateTime;

}
