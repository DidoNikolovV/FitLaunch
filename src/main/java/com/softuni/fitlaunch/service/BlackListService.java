package com.softuni.fitlaunch.service;


import com.softuni.fitlaunch.model.entity.BlacklistEntity;
import com.softuni.fitlaunch.repository.BlacklistRepository;
import org.springframework.stereotype.Service;

@Service
public class BlackListService {

    private final BlacklistRepository blacklistRepository;

    public BlackListService(BlacklistRepository blacklistRepository) {
        this.blacklistRepository = blacklistRepository;
    }

    public void banUser(String ipAddress) {
        BlacklistEntity blacklistEntity = new BlacklistEntity();
        blacklistEntity.setIpAddress(ipAddress);
        blacklistRepository.save(blacklistEntity);
    }

    public void unbanUser(String ipAddress) {
        blacklistRepository.findByIpAddress(ipAddress).ifPresent(blacklistRepository::delete);

    }


    public boolean isBanned(String ipAddress) {
        // TOOD: Please be more realistic
        // e.g create repository where the admin may manage black listed IP-s
        return blacklistRepository.findByIpAddress(ipAddress).isPresent();
    }
}
