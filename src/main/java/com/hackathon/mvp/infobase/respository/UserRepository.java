package com.hackathon.mvp.infobase.respository;

import com.hackathon.mvp.infobase.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    //List<User> findByUsernameContainingIgnoreCase(String username);

    User findById(Long askedBy);

   // Long getReferenceById(Long userId);

    Optional<User> findByEmail(String email);

}