package com.softuni.fitlaunch.service;


import com.softuni.fitlaunch.model.dto.user.ClientDTO;
import com.softuni.fitlaunch.model.dto.user.CoachDTO;
import com.softuni.fitlaunch.model.dto.view.ScheduledWorkoutView;
import com.softuni.fitlaunch.model.entity.ClientEntity;
import com.softuni.fitlaunch.model.entity.CoachEntity;
import com.softuni.fitlaunch.model.entity.ScheduledWorkoutEntity;
import com.softuni.fitlaunch.repository.ClientRepository;
import com.softuni.fitlaunch.repository.CoachRepository;
import com.softuni.fitlaunch.repository.ScheduledWorkoutRepository;
import com.softuni.fitlaunch.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScheduleWorkoutService {

    private final ScheduledWorkoutRepository scheduledWorkoutRepository;

    private final CoachRepository coachRepository;
    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;

    public ScheduleWorkoutService(ScheduledWorkoutRepository scheduledWorkoutRepository, CoachRepository coachRepository, ClientRepository clientRepository, ModelMapper modelMapper) {
        this.scheduledWorkoutRepository = scheduledWorkoutRepository;
        this.coachRepository = coachRepository;
        this.clientRepository = clientRepository;
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

    @Transactional
    public void deleteScheduledWorkout(String username, Long eventId) {
        CoachEntity coachEntity = coachRepository.findByUsername(username).orElseThrow(() -> new ObjectNotFoundException("Coach not found"));


        ScheduledWorkoutEntity scheduledWorkoutEntity = coachEntity.getScheduledWorkouts()
                .stream()
                .filter(workout -> workout.getId().equals(eventId))
                .findFirst()
                .orElseThrow(() -> new ObjectNotFoundException("Scheduled workout not found"));

        ClientEntity clientEntity = clientRepository.findByUsername(scheduledWorkoutEntity.getClient().getUsername()).orElseThrow(() -> new ObjectNotFoundException("Client with " + username + " not found"));
        coachEntity.getScheduledWorkouts().remove(scheduledWorkoutEntity);
        clientEntity.getScheduledWorkouts().remove(scheduledWorkoutEntity);
        coachRepository.save(coachEntity);
        clientRepository.save(clientEntity);
        scheduledWorkoutRepository.deleteById(eventId);
    }
}
