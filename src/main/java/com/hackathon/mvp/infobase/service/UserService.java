package com.hackathon.mvp.infobase.service;

import com.hackathon.mvp.infobase.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    User ensureUserByEmail(String email);
    Optional<User> findByEmail(String email);
}