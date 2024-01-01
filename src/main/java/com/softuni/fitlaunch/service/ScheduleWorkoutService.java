package com.softuni.fitlaunch.service;


import com.softuni.fitlaunch.model.dto.user.ClientDTO;
import com.softuni.fitlaunch.model.dto.user.CoachDTO;
import com.softuni.fitlaunch.model.dto.user.UserDTO;
import com.softuni.fitlaunch.model.dto.user.UserRoleDTO;
import com.softuni.fitlaunch.model.dto.view.ScheduledWorkoutView;
import com.softuni.fitlaunch.model.dto.workout.ScheduledWorkoutDTO;
import com.softuni.fitlaunch.model.entity.ClientEntity;
import com.softuni.fitlaunch.model.entity.CoachEntity;
import com.softuni.fitlaunch.model.entity.ScheduledWorkoutEntity;
import com.softuni.fitlaunch.model.enums.UserRoleEnum;
import com.softuni.fitlaunch.repository.ScheduledWorkoutRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScheduleWorkoutService {

    private final ScheduledWorkoutRepository scheduledWorkoutRepository;
    private final ModelMapper modelMapper;

    public ScheduleWorkoutService(ScheduledWorkoutRepository scheduledWorkoutRepository, ModelMapper modelMapper) {
        this.scheduledWorkoutRepository = scheduledWorkoutRepository;
        this.modelMapper = modelMapper;
    }

    public void scheduleWorkout(ClientDTO clientDTO, CoachDTO coachDTO, LocalDateTime scheduledTime) {
        ClientEntity clientEntity = modelMapper.map(clientDTO, ClientEntity.class);
        CoachEntity coachEntity = modelMapper.map(coachDTO, CoachEntity.class);

        ScheduledWorkoutEntity scheduledWorkoutEntity = new ScheduledWorkoutEntity();
        scheduledWorkoutEntity.setClient(clientEntity);
        scheduledWorkoutEntity.setCoach(coachEntity);
        scheduledWorkoutEntity.setScheduledDateTime(scheduledTime);

        scheduledWorkoutRepository.save(scheduledWorkoutEntity);

    }

    public List<ScheduledWorkoutView> getAllCoachScheduledWorkouts(CoachDTO coachDTO) {
        List<ScheduledWorkoutEntity> allByCoachId = scheduledWorkoutRepository.findAllByCoachId(coachDTO.getId());
        List<ScheduledWorkoutView> scheduledWorkoutsView = allByCoachId.stream().map(scheduledWorkoutEntity -> new ScheduledWorkoutView(scheduledWorkoutEntity.getId(), scheduledWorkoutEntity.getClient().getUsername(), scheduledWorkoutEntity.getScheduledDateTime().toString())).toList();

        return scheduledWorkoutsView;
    }
}
