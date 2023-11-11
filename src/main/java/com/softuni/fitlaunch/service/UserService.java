package com.softuni.fitlaunch.service;


import com.softuni.fitlaunch.model.dto.user.UserDTO;
import com.softuni.fitlaunch.model.dto.user.UserRegisterDTO;
import com.softuni.fitlaunch.model.dto.user.UserRoleDTO;
import com.softuni.fitlaunch.model.entity.UserEntity;
import com.softuni.fitlaunch.model.entity.UserRoleEntity;
import com.softuni.fitlaunch.repository.RoleRepository;
import com.softuni.fitlaunch.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
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

        userRepository.save(user);

        return true;
    }

    public List<UserDTO> getAllUsers() {

        return userRepository.findAll().stream().map(UserService::mapAsUserDTO).toList();
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

    private static UserDTO mapAsUserDTO(UserEntity userEntity) {
        return new UserDTO(
                    userEntity.getId(),
                    userEntity.getUsername(),
                    userEntity.getEmail(),
                    userEntity.getRoles()
                    );
    }

}
