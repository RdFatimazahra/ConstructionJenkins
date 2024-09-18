package com.authenticationservice.Repository;

import com.authenticationservice.model.utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<utilisateur, Integer> {
    Optional<utilisateur> findByEmail(String email);
}
