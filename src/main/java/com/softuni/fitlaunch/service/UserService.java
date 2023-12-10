package com.softuni.fitlaunch.service;


import com.softuni.fitlaunch.model.dto.program.ProgramWeekWorkoutDTO;
import com.softuni.fitlaunch.model.dto.user.UserDTO;
import com.softuni.fitlaunch.model.dto.user.UserRegisterDTO;
import com.softuni.fitlaunch.model.dto.user.UserRoleDTO;
import com.softuni.fitlaunch.model.dto.workout.WorkoutDTO;
import com.softuni.fitlaunch.model.entity.*;
import com.softuni.fitlaunch.model.events.UserRegisteredEvent;
import com.softuni.fitlaunch.repository.*;
import com.softuni.fitlaunch.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    private final RoleRepository roleRepository;
    private final WorkoutRepository workoutRepository;

    private final ModelMapper modelMapper;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final UserActivationCodeRepository userActivationCodeRepository;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, WorkoutRepository workoutRepository, ModelMapper modelMapper, ApplicationEventPublisher applicationEventPublisher, UserActivationCodeRepository userActivationCodeRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.workoutRepository = workoutRepository;
        this.modelMapper = modelMapper;
        this.applicationEventPublisher = applicationEventPublisher;
        this.userActivationCodeRepository = userActivationCodeRepository;
    }

    public boolean register(UserRegisterDTO userRegisterDTO) {

        boolean isFirst = userRepository.count() == 0;

        if(!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            return false;
        }

        Optional<UserEntity> dbUser = userRepository.findByUsername(userRegisterDTO.getUsername());

        if(dbUser.isPresent()) {
            return false;
        }

        UserRoleEntity role = roleRepository.findById(isFirst ? 1L : 2L).orElse(null);


        UserEntity user = new UserEntity();
        user.setUsername(userRegisterDTO.getUsername());
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        user.setRoles(List.of(role));
        user.setMembership("Free");



        userRepository.save(user);


        applicationEventPublisher.publishEvent(new UserRegisteredEvent(
                "UserService", userRegisterDTO.getEmail(), userRegisterDTO.getUsername()
        ));

        return true;
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll().stream().toList();
    }

    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new ObjectNotFoundException("User with " + username + " doesn't exist"));
    }

    public void startProgramWorkout(String username, ProgramWeekWorkoutDTO programWeekWorkoutDTO) {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new ObjectNotFoundException("User with " + username + " doesn't exist"));
        ProgramWeekWorkoutEntity programWeekWorkoutEntity = modelMapper.map(programWeekWorkoutDTO, ProgramWeekWorkoutEntity.class);
        user.getWorkoutsStarted().add(programWeekWorkoutEntity);
        userRepository.save(user);
    }

    public boolean isWorkoutStarted(String username, ProgramWeekWorkoutDTO programWeekWorkoutDTO) {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new ObjectNotFoundException("User with " + username + " doesn't exist"));

        for (ProgramWeekWorkoutEntity weekWorkoutEntity : user.getWorkoutsStarted()) {
            if(weekWorkoutEntity.getId().equals(programWeekWorkoutDTO.getId())) {
                return true;
            }
        }

        return false;
    }

    public void completeProgramWorkout(String username, ProgramWeekWorkoutDTO programWeekWorkoutDTO) {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new ObjectNotFoundException("User with " + username + " doesn't exist"));
        ProgramWeekWorkoutEntity programWeekWorkoutEntity = modelMapper.map(programWeekWorkoutDTO, ProgramWeekWorkoutEntity.class);
        user.getWorkoutsCompleted().add(programWeekWorkoutEntity);
        userRepository.save(user);
    }

    public boolean isWorkoutCompleted(String username, ProgramWeekWorkoutDTO programWeekWorkoutDTO) {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new ObjectNotFoundException("User with " + username + " doesn't exist"));
        for (ProgramWeekWorkoutEntity weekWorkoutEntity : user.getWorkoutsCompleted()) {
            if(weekWorkoutEntity.getId().equals(programWeekWorkoutDTO.getId())) {
                return true;
            }
        }

        return false;
    }

    public void changeUserRole(Long userId, UserRoleEntity role) {
        Optional<UserEntity> optUser = userRepository.findById(userId);

        if(optUser.isPresent()) {
            UserEntity user = optUser.get();
            if(user.getRoles() == null) {
                user.setRoles(new ArrayList<>());
            }


            user.getRoles().clear();
            user.getRoles().add(role);
            userRepository.save(user);
        }
    }

    public List<WorkoutDTO> getCompletedWorkouts(String username) {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        List<WorkoutDTO> completedWorkouts = user.getWorkoutsCompleted().stream().map(workoutEntity -> modelMapper.map(workoutEntity, WorkoutDTO.class)).toList();

        return completedWorkouts;
    }


    public UserRoleDTO getUserRole(UserDTO userDTO) {
        Optional<UserEntity> optUser =
                userRepository.findByUsername(userDTO.getEmail());

        if(optUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        UserEntity user = optUser.get();

        UserRoleEntity userRoleEntity = user.getRoles().get(0);

        return new UserRoleDTO(
                userRoleEntity.getId(),
                userRoleEntity.getRole()
        );
    }

    public boolean activateUser(String activationCode) {

        UserActivationCodeEntity activationCodeEntity = userActivationCodeRepository.findByActivationCode(activationCode)
                .orElseThrow(() -> new ObjectNotFoundException("Activation code not found"));
        UserEntity user = activationCodeEntity.getUser();
        System.out.println();

        if (!user.isActivated() && !isActivationCodeExpired(activationCodeEntity.getCreated())) {
            user.setActivated(true);
            user.setActivationCode(null);
            user.setActivationCodeExpiration(null);

            userRepository.save(user);
            userActivationCodeRepository.delete(activationCodeEntity);

            return true;
        }

        return false;
    }

    private boolean isActivationCodeExpired(Instant expirationDateTime) {
        return expirationDateTime != null && expirationDateTime.isBefore(expirationDateTime);
    }


    public void changeMembership(UserEntity loggedUser, String membership) {
        loggedUser.setMembership(membership);
        userRepository.save(loggedUser);
    }



}
