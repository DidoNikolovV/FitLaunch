package com.softuni.fitlaunch.service;

import com.softuni.fitlaunch.model.entity.UserEntity;
import com.softuni.fitlaunch.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class FitLaunchUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public FitLaunchUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(email)
                .map(FitLaunchUserDetailsService::map)
                .orElseThrow(() -> new UsernameNotFoundException("User " + email + " doesn't exists"));
    }

    private static UserDetails map(UserEntity userEntity) {
        UserDetails userDetails = User
                .withUsername(userEntity.getUsername())
                .password(userEntity.getPassword())
                .authorities(List.of()) // TODO - add roles
                .build();

        return userDetails;
    }
}
