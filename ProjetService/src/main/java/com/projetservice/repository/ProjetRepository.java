package com.projetservice.repository;

import com.projetservice.model.Projet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetRepository extends JpaRepository<Projet, Integer> {

}
