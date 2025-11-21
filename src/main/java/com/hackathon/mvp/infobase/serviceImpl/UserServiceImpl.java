package com.hackathon.mvp.infobase.serviceImpl;

import com.hackathon.mvp.infobase.model.User;
import com.hackathon.mvp.infobase.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User ensureUserByEmail(String email) {
        return null;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }
}
