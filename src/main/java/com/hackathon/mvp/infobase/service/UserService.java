package com.hackathon.mvp.infobase.service;

import com.hackathon.mvp.infobase.model.User;

import java.util.Optional;

public interface UserService {
    User ensureUserByEmail(String email);
    Optional<User> findByEmail(String email);
}